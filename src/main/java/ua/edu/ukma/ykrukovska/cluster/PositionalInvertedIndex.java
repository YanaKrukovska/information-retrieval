package ua.edu.ukma.ykrukovska.cluster;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionalInvertedIndex implements Index {

    private Map<String, List<Posting>> index;
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

    public Map<String, List<Posting>> getIndex() {
        return index;
    }

    public void addTerm(String term, int documentId, int position, int amountOfWords) {

        if (index.containsKey(term)) {

            List<Posting> mList = index.get(term);

            if ((mList.get(mList.size() - 1).getDocumentId() != documentId)) {

                List<Integer> positions = new ArrayList<>();
                positions.add(position);
                mList.add(new Posting(documentId, (1 / amountOfWords), positions));
            } else {

                mList.get(mList.size() - 1).addPostingPosition(position);
                mList.get(mList.size() - 1).setTermFrequency(mList.get(mList.size() - 1).getPositionsInDoc().size() / amountOfWords);

            }
        } else {
            List<Posting> mList = new ArrayList<>();
            List<Integer> positions = new ArrayList<>();
            positions.add(position);
            mList.add(new Posting(documentId, (1 / amountOfWords), positions));
            index.put(term, mList);

        }
    }

    @Override
    public List<Posting> getPostings(String term) {
        if (index.containsKey(term)) {
            return index.get(term);
        }
        return new ArrayList<>();
    }


}