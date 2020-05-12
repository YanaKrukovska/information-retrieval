package ua.edu.ukma.ykrukovska.cluster;


import java.util.HashMap;
import java.util.Map;

public class DocumentVector {


    private Map<Integer, Double> docVector;
    private int documentID;

    public DocumentVector(int documentID) {
        docVector = new HashMap<>();
        this.documentID = documentID;
    }

    public void addWdt(double wdt, int termID) {
        if (termID == -1)
            return;

        if (docVector.containsKey(termID)) {
            double w = docVector.get(termID);
            w += wdt;

        } else
            docVector.put(termID, wdt);
    }

    public Map<Integer, Double> getVector() {
        return docVector;
    }

    public void addVector(DocumentVector vector, int numDocuments, double ld) {
        double normalize = (ld * numDocuments);
        for (Integer termID : vector.getVector().keySet()) {

            if (docVector.containsKey(termID)) {
                docVector.put(termID, (vector.getVector().get(termID)) / normalize + docVector.get(termID));
            } else {
                docVector.put(termID, vector.getVector().get(termID) / normalize);
            }
        }
    }


    public void normalize(Double d) {
        for (Integer termID : docVector.keySet()) {

            double vec1 = docVector.get(termID);
            docVector.put(termID, vec1 / d);
        }
    }


    public double findSimilarity(DocumentVector v) {


        double dot = 0;
        for (Integer termID : docVector.keySet()) {

            double vi = docVector.get(termID);
            double vj = v.getVector().containsKey(termID) ? v.getVector().get(termID) : 0;

            dot += vi * vj;


        }

        return dot / (this.findMagnitude() * v.findMagnitude());
    }

    public double findMagnitude() {
        double mag = 0;
        for (Integer termID : docVector.keySet()) {

            mag += docVector.get(termID) * docVector.get(termID);
        }
        return Math.sqrt(mag);
    }


    public double calculateDistance(DocumentVector v2) {

        double distance = 0;
        for (Integer termID : docVector.keySet()) {
            //  distance+= Math.pow(docVector.get(termID) - (v2.getVector().containsKey(termID)?v2.getVector().get(termID):0),2);

            double vec1 = docVector.get(termID);
            double vec2 = v2.getVector().containsKey(termID) ? v2.getVector().get(termID) : 0;

            double diff = vec1 - vec2;

            distance += diff * diff;
            //distance+=( docVector.get(termID) - (v2.getVector().containsKey(termID)?v2.getVector().get(termID):0)) * (docVector.get(termID) - (v2.getVector().containsKey(termID)?v2.getVector().get(termID):0));
        }
        return Math.sqrt(distance);
    }

    public int getDocumentID() {
        return documentID;
    }

}