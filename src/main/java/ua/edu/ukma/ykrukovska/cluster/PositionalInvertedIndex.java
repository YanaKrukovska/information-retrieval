package ua.edu.ukma.ykrukovska.cluster;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositionalInvertedIndex implements Index {

    private HashMap<String, List<Posting>> index;
    private List<String> words;

    public PositionalInvertedIndex() {
        index = new HashMap<>();
    }

    @Override
    public List<String> getVocabulary() {
        if (words == null) {
            words = new ArrayList<>(index.keySet());
        }
        return words;
    }

    public void addTerm(String term, int documentId, int position) {

        if (index.containsKey(term)) {

            List<Posting> mList = index.get(term);

            if ((mList.get(mList.size() - 1).getDocumentId() != documentId)) {

                List<Integer> positions = new ArrayList<>();
                positions.add(position);
                mList.add(new Posting(documentId, positions));
            } else {

                mList.get(mList.size() - 1).addPostingPosition(position);

            }
        } else {
            List<Posting> mList = new ArrayList<>();
            List<Integer> positions = new ArrayList<>();
            positions.add(position);
            mList.add(new Posting(documentId, positions));
            index.put(term, mList);

        }
    }

    @Override
    public List<Posting> getPostings(String term) {
        if (index.containsKey(term)){
            return index.get(term);
        }
        return new ArrayList<>();
    }


}