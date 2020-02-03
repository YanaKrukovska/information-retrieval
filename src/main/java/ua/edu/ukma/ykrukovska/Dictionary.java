package ua.edu.ukma.ykrukovska;

public interface Dictionary {

     void add(String word);
     boolean checkIfWordExists(String word);
     int size();


    int getWordsCount();

    Object[] getDictionaryWords();
}
