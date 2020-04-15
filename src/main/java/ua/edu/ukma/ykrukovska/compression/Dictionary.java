package ua.edu.ukma.ykrukovska.compression;

import java.util.LinkedList;
import java.util.List;

class Dictionary{
    int termFrequency;
    int docFrequency;
    List<PostingList> postings;

    public Dictionary(int termFrequency, int docFrequency, LinkedList<PostingList> postings) {
        this.docFrequency = docFrequency;
        this.termFrequency = termFrequency;
        this.postings = postings;
    }
}