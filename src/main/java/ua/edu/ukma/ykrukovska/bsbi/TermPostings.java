package ua.edu.ukma.ykrukovska.bsbi;

import java.util.Collection;
import java.util.TreeSet;


public class TermPostings implements Comparable<TermPostings> {

    private int termId;
    private TreeSet<Integer> postingsList;

    public TermPostings(int termId, Collection<Integer> postingsCollection) {
        this.termId = termId;
        this.postingsList = new TreeSet<>();
        this.postingsList.addAll(postingsCollection);
    }

    public void addPostingsList(int posting) {
        postingsList.add(posting);
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public Collection<Integer> getPostingsList() {
        return postingsList;
    }

    public void setPostingsList(Collection<Integer> postingsList) {
        this.postingsList.addAll(postingsList);
    }

    @Override
    public int compareTo(TermPostings o) {
        return Integer.compare(termId, o.termId);
    }
}