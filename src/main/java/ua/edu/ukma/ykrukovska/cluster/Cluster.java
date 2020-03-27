package ua.edu.ukma.ykrukovska.cluster;

import java.util.HashSet;

public class Cluster extends HashSet<CosineScoreElement> implements CosineScoreElement {

    private Document clusterLeader;
    private double weight;

    public Cluster(Document doc) {
        setClusterLeader(doc);
        setWeight(0);
    }

    private Cluster(Cluster cluster) {
        this.addAll(cluster);
        setWeight(cluster.getWeight());
        this.setClusterLeader(cluster.getClusterLeader());
    }


    public CosineScoreElement create(CosineScoreElement toCopy, double weight) {
        CosineScoreElement weightedCopy = new Cluster((Cluster) toCopy);
        weightedCopy.setWeight(weight);
        return weightedCopy;
    }

    public Document getDocumentLeader() {
        return this.getClusterLeader();
    }

    private Document getClusterLeader() {
        return clusterLeader;
    }

    private void setClusterLeader(Document clusterLeader) {
        this.clusterLeader = clusterLeader;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cluster) {
            return ((Cluster) obj).getClusterLeader().equals(this.getClusterLeader());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cluster: " + "clusterLeader = " + clusterLeader.getName() + ';';
    }
}