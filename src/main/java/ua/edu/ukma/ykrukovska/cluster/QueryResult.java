package ua.edu.ukma.ykrukovska.cluster;

class QueryResult {
    int documentID;
    double dist;

    public QueryResult(int docID, double dst) {

        documentID = docID;
        dist = dst;

    }
}