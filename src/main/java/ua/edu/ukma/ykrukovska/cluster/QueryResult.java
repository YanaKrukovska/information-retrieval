package ua.edu.ukma.ykrukovska.cluster;

class QueryResult {
    int documentID;
    double dist;

    public QueryResult(int docID, double distance) {

        documentID = docID;
        dist = distance;

    }
}