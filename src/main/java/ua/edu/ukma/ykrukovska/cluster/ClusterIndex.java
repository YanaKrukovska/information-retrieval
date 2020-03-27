package ua.edu.ukma.ykrukovska.cluster;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ClusterIndex extends HashSet<Term> {

    private Map<Term, Integer> dft;
    private Map<Term, Set<CosineScoreElement>> cluster_postings;
    private Set<CosineScoreElement> clusters;
    private Set<Document> documents;
    private int b1;

    public ClusterIndex(int b1) {
        this.b1 = b1;
        this.clusters = new HashSet<>();
        this.documents = new HashSet<>();
        this.dft = new HashMap<>();
        this.cluster_postings = new HashMap<>();
    }


    public void createIndex(File fileBlock) {
        readBlock(fileBlock);
        buildCluster();
        for (CosineScoreElement cluster : clusters){
            System.out.println("Cluster " + cluster);

        }
    }


    private void buildCluster() {
        assignDf_t();
        generateClusterPostingList();
        for (Document document : documents) {
            findClosestClusterLeaders(document);
        }
    }

    private void findClosestClusterLeaders(Document document) {

        Set<CosineScoreElement> nearestClusters = new HashSet<>(cosineScore(document, b1, new HashSet<>(clusters)));
        for (CosineScoreElement cluster : clusters) {
            if (nearestClusters.contains(cluster)) {
                ((Cluster) cluster).add(document);
            }
        }
    }


    private double calcW_td(Term term, Document document) {
        double df_td = getDf_t(term);
        return ((1 + Math.log10(document.countAmount(term))) * Math.log((double) documents.size() / df_td));
    }

    private int getDf_t(Term term) {
        return dft.get(term);
    }

    private void assignDf_t() {
        for (Term term : this) {
            dft.put(term, calculateDf_t(term));
        }
    }
    private int calculateDf_t(Term term) {
        int df_t = 0;
        for (Document document : documents) {
            if (document.contains(term)) {
                df_t++;
            }
        }
        return df_t;
    }


    private void generateClusterPostingList() {
        for (Term term : this) {
            Set<CosineScoreElement> postings = new HashSet<>();
            for (CosineScoreElement cluster : clusters) {
                if (cluster.getDocumentLeader().contains(term)) {
                    postings.add(cluster);
                }
            }
            cluster_postings.put(term, postings);
        }

    }


    private Set<CosineScoreElement> getClusterPostingList(Term term) {
        return cluster_postings.get(term);
    }


    private Set<CosineScoreElement> getPostingList(Term term, Set<CosineScoreElement> clusters) {
        if (isClusterPresent(clusters)) {
            return getClusterPostingList(term);
        }

        Set<CosineScoreElement> postings = new HashSet<>();
        for (CosineScoreElement cluster : clusters)
            if (cluster.contains(term)) {
                postings.add(cluster);
            }

        return postings;
    }


    private boolean isClusterPresent(Set<CosineScoreElement> clusters) {
        for (CosineScoreElement cluster : clusters){
            if (cluster instanceof Cluster)
                return true;
        }

        return false;
    }

    private List<CosineScoreElement> cosineScore(Document docQuery, int k, Set<CosineScoreElement> clusters) {
        Map<CosineScoreElement, Double> scores = new HashMap<>();
        List<CosineScoreElement> result = new LinkedList<>();
        Queue<CosineScoreElement> queue = new PriorityQueue<>(new CosineScoreSetComparator());

        for (CosineScoreElement cluster : clusters) {
            scores.put(cluster, 0.0);
        }

        for (Term term : docQuery.getTermList()) {
            double w_tq = calcW_td(term, docQuery);

            for (CosineScoreElement d : getPostingList(term, clusters)) {
                double w_td = calcW_td(term, d.getDocumentLeader());
                double n_val = scores.get(d) + (w_tq * w_td);
                scores.put(d, n_val);
            }
        }

        for (CosineScoreElement score : scores.keySet()) {
            queue.add(score.create(score, (scores.get(score) / score.getDocumentLeader().length())));
        }

        for (int i = 0; i < k; i++) {
            if (queue.peek() == null) {
                break;
            }
            result.add(queue.poll());
        }

        return result;
    }


    private Set<Integer> pickRandomClusterLeader(int size) {
        double sqrt_n = Math.sqrt(size);
        Set<Integer> index = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < sqrt_n; i++) {
            index.add(random.nextInt(size));
        }
        return index;
    }

    private void readBlock(File fileBlock) {
        Set<Integer> clusterLeaderIndices = pickRandomClusterLeader(Objects.requireNonNull(fileBlock.listFiles()).length);
        int counter = 0;

        for (File file : Objects.requireNonNull(fileBlock.listFiles())) {
            Document document = null;
            try {
                document = getTerms(file);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
            if (document != null) {
                addAll(document);
                if (clusterLeaderIndices.contains(counter))
                    clusters.add(new Cluster(document));
            }
            counter++;
        }
    }

    private void addAll(Document document) {
        for (Term term : document.getTermList()) {
            add(term);
        }
    }

    private Document getTerms(File filePath) throws IOException, ParserConfigurationException, SAXException {
        FictionBookParser fictionBookParser = new FictionBookParser(filePath.getPath());

        List<String> words = fictionBookParser.getWordList();
        Document document = new Document(filePath.getName(), words.size());

        for (String word : words) {
            document.add(new Term(word));
        }

        documents.add(document);

        return document;
    }


}