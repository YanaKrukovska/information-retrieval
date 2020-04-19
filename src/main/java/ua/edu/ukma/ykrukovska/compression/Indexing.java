package ua.edu.ukma.ykrukovska.compression;

import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.util.BytesRef;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;


class Indexing {

    private static Map<String, Dictionary> invertedDictionary = new TreeMap<>();
    private static File file;

    public static void explainIDF(String stringTerm){
        BM25Similarity bm25Similarity = new BM25Similarity();
        TermStatistics termStatistics = new TermStatistics(new BytesRef(stringTerm.getBytes()), invertedDictionary.get(stringTerm).postings.size(), invertedDictionary.get(stringTerm).termFrequency);
        CollectionStatistics collectionStatistics = new CollectionStatistics(stringTerm, file.listFiles().length, invertedDictionary.get(stringTerm).postings.size(), invertedDictionary.get(stringTerm).termFrequency, invertedDictionary.get(stringTerm).postings.size());

        Explanation exp = bm25Similarity.idfExplain(collectionStatistics, termStatistics);
        System.out.println("Term: " + stringTerm + " - " + exp.getDescription());
        Explanation[] details = exp.getDetails();
        for (Explanation detail : details) {
            System.out.println(detail);
        }

        System.out.println();

    }


    public static void buildIndex(String directoryPath) throws Exception {
        file = new File(directoryPath);
        int docNumber = 0;
        for (File f : Objects.requireNonNull(file.listFiles())) {
            FileParser fileParser = new FileParser();
            fileParser.parseFile(f);
            addToIndex(docNumber, fileParser.dictionary, invertedDictionary, fileParser.documentLength);
            fileParser.documentLength = 0;
            docNumber++;
        }
    }

    private static void addToIndex(int docID, Map<String, Integer> termIndex, Map<String, Dictionary> dictionary, int documentLength) {

        int max_tf = 0;
        for (String term : termIndex.keySet()) {
            if (termIndex.get(term) > max_tf) {
                max_tf = termIndex.get(term);
            }

            addToDictionaryPosting(docID, term, termIndex.get(term), dictionary, (documentLength / 2), max_tf);
        }
    }

    private static void addToDictionaryPosting(int docID, String dictionary_term, int termFrequency, Map<String, Dictionary> DesiredDictionary, int totalWordOccurances, int max_tf) {
        Dictionary term = DesiredDictionary.get(dictionary_term);

        if (term == null) {
            term = new Dictionary(0, 0, new LinkedList<>());
            DesiredDictionary.put(dictionary_term, term);
        }
        term.postings.add(new PostingList(docID, termFrequency, max_tf, totalWordOccurances));
        term.termFrequency += termFrequency;
        term.docFrequency += 1;

    }

    public static void createUncompressedIndex(File uncompressedIndex, Map<String, Dictionary> desiredDictionary) throws FileNotFoundException {
        PrintWriter uncompressedDictionary = new PrintWriter(uncompressedIndex);

        Iterator<Entry<String, Dictionary>> iterator = desiredDictionary.entrySet().iterator();
        Entry<String, Dictionary> entry;
        while (iterator.hasNext()) {
            StringBuilder s = new StringBuilder(":");
            entry = iterator.next();
            for (PostingList term : entry.getValue().postings) {
                s.append(term.docID).append(",");
            }
            uncompressedDictionary.write(entry.getKey() + s + "\n");
        }
        uncompressedDictionary.flush();
        uncompressedDictionary.close();
    }

    public static Map<String, Dictionary> getInvertedDictionary() {
        return invertedDictionary;
    }
}
