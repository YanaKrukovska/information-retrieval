package ua.edu.ukma.ykrukovska.cluster;

import java.util.*;

public class Document implements CosineScoreElement {

    private List<Term> termList;
    private String name;
    private int length;
    private double weight;

    public Document(String name, int length) {
        this.setName(name);
        this.termList = new LinkedList<>();
        this.length = length;
    }

    public Document(Document toCopy) {
        this.setName(toCopy.getName());
        termList = toCopy.getTermList();
        this.length = toCopy.length;
        this.weight = toCopy.weight;
    }

    public String getName() {
        return name;
    }

    public List<Term> getTermList() {
        return termList;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean equals(Object o) {
        if (o instanceof Document)
            return this.getName().equals(((Document) o).getName());
        return false;
    }

    public boolean add(Term term) {
        return termList.add(term);
    }

    public boolean contains(Object o) {
        return termList.contains(o);
    }

    public int size() {
        return termList.size();
    }

    public int length() {
        return length;
    }


    public int countAmount(Object element) {
        int amount = 0;
        for (Term term : termList) {
            if (term.equals(element)) {
                amount++;
            }
        }
        return amount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CosineScoreElement create(CosineScoreElement toCopy, double weight) {
        CosineScoreElement weightedCopy = null;
        if (toCopy instanceof Document) {
            weightedCopy = new Document((Document) toCopy);
            weightedCopy.setWeight(weight);
        }
        return weightedCopy;
    }

    public Document getDocumentLeader() {
        return this;
    }

}