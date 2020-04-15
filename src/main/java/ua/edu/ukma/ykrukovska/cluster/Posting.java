package ua.edu.ukma.ykrukovska.cluster;


import java.util.ArrayList;
import java.util.List;


public class Posting {
    private int documentID;
    private List<Integer> mPositionIds = new ArrayList<>();
    private int mTermFrequency;
    private double wdtDefaultValue;
    private double wdtTfIdfValue;
    private double wdtOkapiValue;
    private double wdtWackyValue;

    public double getWdtDefaultValue() {
        return wdtDefaultValue;
    }

    public double getWdtTfIdfValue() {
        return wdtTfIdfValue;
    }

    public double getWdtOkapiValue() {
        return wdtOkapiValue;
    }

    public double getWdtWackyValue() {
        return wdtWackyValue;
    }

    public void setWdtDefaultValue(double wdtDefaultValue) {
        this.wdtDefaultValue = wdtDefaultValue;
    }

    public void setWdtTfIdfValue(double wdtTfIdfValue) {
        this.wdtTfIdfValue = wdtTfIdfValue;
    }

    public void setWdtOkapiValue(double wdtOkapiValue) {
        this.wdtOkapiValue = wdtOkapiValue;
    }

    public void setWdtWackyValue(double wdtWackyValue) {
        this.wdtWackyValue = wdtWackyValue;
    }

    public int getTermFrequency() {
        return mTermFrequency;
    }

    public Posting(int documentId, List<Integer> positions) {
        documentID = documentId;
        mPositionIds = positions;
    }

    public Posting(int documentId) {
        documentID = documentId;
    }

    public Posting(int documentId, int termFrequency) {
        this.documentID = documentId;
        this.mTermFrequency = termFrequency;
    }
    public void addPostingPosition(int position) {
        mPositionIds.add(position);

    }
    public int getDocumentId() {
        return documentID;
    }

    public List<Integer> getPositionsInDoc() {
        return mPositionIds;
    }
}