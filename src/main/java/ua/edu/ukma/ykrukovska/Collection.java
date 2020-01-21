package ua.edu.ukma.ykrukovska;

import java.util.LinkedList;
import java.util.List;

public class Collection {

    private List<CollectionItem> items = new LinkedList<>();
    private Dictionary dictionary = new DictionaryArray();
 //   private Dictionary dictionary = new DictionarySet();


    public long getTotalSizeKB() {
        return items.stream().mapToLong(CollectionItem::getFileSize).sum() /  1024 ;
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
