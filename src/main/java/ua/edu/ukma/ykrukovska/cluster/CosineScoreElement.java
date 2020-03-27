package ua.edu.ukma.ykrukovska.cluster;

public interface CosineScoreElement {

    double getWeight();

    void setWeight(double w);

    CosineScoreElement create(CosineScoreElement toCopy, double weight);

    Document getDocumentLeader();

    boolean contains(Object o);

}