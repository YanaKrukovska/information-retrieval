package ua.edu.ukma.ykrukovska.compression;

class PostingList{
    Integer docID;
    Integer termFrequency;
    Integer max_tf;
    Integer documentLength;

    public PostingList(Integer docID, Integer termFrequency, Integer max_tf, Integer documentLength) {
        this.docID = docID;
        this.termFrequency = termFrequency;
        this.max_tf = max_tf;
        this.documentLength = documentLength;

    }
}
