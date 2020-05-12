package ua.edu.ukma.ykrukovska.cluster;


import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.similarities.BM25Similarity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class Cluster {

    private static final int amountOfLeadersForOneFollower = 1;
    private Map<Integer, List<Integer>> cluster;
    private Map<String, Integer> vectorSpace;
    private Map<Integer, DocumentVector> leaderVectors;
    private Map<Integer, DocumentVector> followers;
    private Map<Integer, Double> weights;
    private List<File> files;
    private Index index;

    public Cluster() {

        vectorSpace = new HashMap<>();
        followers = new HashMap<>();
        leaderVectors = new HashMap<>();
        weights = new HashMap<>();
        cluster = new HashMap<>();
    }

    public Cluster(File[] files, Index index) {
        this.index = index;
        this.files = Arrays.asList(files);
        weights = new HashMap<>();
        getNewLeaders(files, index);

        while (cluster.size() < (int) 0.7 * Math.sqrt(files.length))
            getNewLeaders(files, index);


    }


    private void getNewLeaders(File[] corpus, Index index) {
        int leaderCount = (int) Math.sqrt(corpus.length);

        Set<Integer> leaders = new HashSet<>();
        RandomLeaderGenerator generator = new RandomLeaderGenerator(corpus.length);

        while (leaders.size() < leaderCount) {
            int nextLeaderNumber = generator.getNextLeader();
            leaders.add(nextLeaderNumber);
        }
        List<String> vocabulary = index.getVocabulary();
        vectorSpace = new HashMap<>();
        int termID = 0;
        for (String term : vocabulary) {
            vectorSpace.put(term, termID++);
        }

        leaderVectors = new HashMap<>();
        followers = new HashMap<>();


        for (String term : vocabulary) {
            for (Posting posting : index.getPostings(term)) {

                double wdt = 1 + Math.log(posting.getPositionsInDoc().size());

                if (leaders.contains(posting.getDocumentId())) {
                    add(term, posting, wdt, leaderVectors);
                } else {
                    add(term, posting, wdt, followers);
                }

                if (weights.containsKey(posting.getDocumentId())) {
                    double weight = weights.get(posting.getDocumentId());
                    weight += wdt * wdt;
                    weights.put(posting.getDocumentId(), weight);
                } else {

                    double weight = (double) 0;
                    weight += wdt * wdt;
                    weights.put(posting.getDocumentId(), weight);
                }
            }

        }

        cluster = clusterFollowersToLeaders();
    }

    private void add(String term, Posting posting, double wdt, Map<Integer, DocumentVector> leaderVectors) {
        if (leaderVectors.containsKey(posting.getDocumentId())) {

            DocumentVector vector = leaderVectors.get(posting.getDocumentId());
            vector.addWdt(wdt, vectorSpace.get(term));
            leaderVectors.put(posting.getDocumentId(), vector);
        } else {

            DocumentVector vector = new DocumentVector(posting.getDocumentId());
            vector.addWdt(wdt, vectorSpace.get(term));
            leaderVectors.put(posting.getDocumentId(), vector);

        }
    }


    public void writeClusterToDisk(Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));

        for (Integer leader : cluster.keySet()) {
            writer.write("Leader: " + files.get(leader).getName() + ". Size: " + cluster.get(leader).size() + ": ");

            for (Integer fol : cluster.get(leader)) {
                writer.write(files.get(fol).getName() + ", ");
            }
            writer.write(System.lineSeparator());

        }
        writer.flush();
    }



    private Map<Integer, List<Integer>> clusterFollowersToLeaders() {
        Map<Integer, List<Integer>> leaderFollowerMatrix = new HashMap<>();
        for (DocumentVector follower : followers.values()) {

           Queue<QueryResult> pQ = new PriorityQueue<>(amountOfLeadersForOneFollower, Comparator.comparingDouble(d -> d.dist));
            int leaderID = -1;
            for (DocumentVector leader : leaderVectors.values()) {

                double sim = leader.findSimilarity(follower);
                pQ.offer(new QueryResult(leader.getDocumentID(), sim));
                if (pQ.size() > amountOfLeadersForOneFollower)
                    pQ.poll();


            }

            for (QueryResult q : pQ) {
                leaderID = q.documentID;

                if (leaderFollowerMatrix.containsKey(leaderID)) {

                    List<Integer> followers = leaderFollowerMatrix.get(leaderID);
                    followers.add(follower.getDocumentID());
                    leaderFollowerMatrix.put(leaderID, followers);
                } else {
                    List<Integer> followers = new ArrayList<>();
                    followers.add(follower.getDocumentID());
                    leaderFollowerMatrix.put(leaderID, followers);
                }

            }
        }
        return leaderFollowerMatrix;
    }


    public Queue<QueryResult> query(String[] terms) {




        DocumentVector queryVector = createQueryVector(terms);
        double maxDist = Double.MIN_VALUE;
        int leaderID = -1;
        for (Integer docID : leaderVectors.keySet()) {
            DocumentVector leader = leaderVectors.get(docID);

            /////
            BM25Similarity bm25Similarity = new BM25Similarity();
           // CollectionStatistics collectionStat = new CollectionStatistics(terms[0], files.size(),index.getIndex().get(terms[0])., index.getPostings(terms[0]).size());
            //bm25Similarity.idfExplain(collectionStat, termStat);

            ////
            if (!cluster.containsKey(leader.getDocumentID()))
                continue;

            double dist = leader.findSimilarity(queryVector);
            if (dist > maxDist) {
                maxDist = dist;
                leaderID = leader.getDocumentID();
            }

        }

        Queue<QueryResult> results = new PriorityQueue<>(50, Comparator.comparingDouble(q -> q.dist));

        for (Integer followerID : cluster.get(leaderID)) {

            DocumentVector follower = followers.get(followerID);
            double dist = follower.findSimilarity(queryVector);


            results.offer(new QueryResult(followerID, dist));
            if (results.size() > 50)
                results.poll();


        }


        return results;

    }


    private DocumentVector createQueryVector(String[] terms) {

        DocumentVector vector = new DocumentVector(-1);
        Map<String, Integer> termFreqmap = new HashMap<>();
        for (String term : terms) {

            if (termFreqmap.containsKey(term))
                termFreqmap.put(term, termFreqmap.get(term) + 1);
            else
                termFreqmap.put(term, 1);
        }


        for (String term : termFreqmap.keySet()) {

            int freq = termFreqmap.get(term);
            double wdt = 1 + Math.log(freq);
            vector.addWdt(wdt, vectorSpace.getOrDefault(term, -1));
        }
        return vector;
    }


}

