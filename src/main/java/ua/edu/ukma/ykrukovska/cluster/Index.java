package ua.edu.ukma.ykrukovska.cluster;

import java.util.List;


public interface Index {

    List<String> getVocabulary();
    List<Posting> getPostings(String term);
}



