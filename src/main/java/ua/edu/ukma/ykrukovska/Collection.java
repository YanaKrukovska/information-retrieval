package ua.edu.ukma.ykrukovska;

import java.util.LinkedList;
import java.util.List;

public class Collection {

    private List<CollectionItem> items = new LinkedList<>();
    private Dictionary dictionary = new DictionaryArray();


    public long getTotalSize() {
        return items.stream().mapToLong(CollectionItem::getFileSize).sum();
    }

    public long getTotalWordCount() {
        return items.stream().mapToLong(CollectionItem::getWordsCount).sum();
    }

    public long getDictionaryWordCount() {
        return dictionary.getWordsCount();
    }

    public List<CollectionItem> getItems() {
        return items;
    }

    public void setItems(List<CollectionItem> items) {
        this.items = items;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
