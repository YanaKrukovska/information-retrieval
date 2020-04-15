package ua.edu.ukma.ykrukovska.compression;

class CompressedPostingList{
    byte[] docID;
    byte[] termFrequency;
    byte[] max_docLength;
    byte[] max_tf;

    public CompressedPostingList(byte[] docID, byte[] termFrequency, byte[] max_docLength, byte[] max_tf) {
        this.docID = docID;
        this.termFrequency = termFrequency;
        this.max_docLength = max_docLength;
        this.max_tf = max_tf;
    }
}