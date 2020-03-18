package ua.edu.ukma.ykrukovska.compression;

import java.util.Objects;

public class CompressedDictionary {

    private String dictionary;
    private int[] blockIndexes;
    private int amountOfTerms;
    private String[] terms;

    private static final int BLOCK_SIZE = 4;
    private static final char FRONT_CODING_MARK = '#';
    private static final int DICTIONARY_ARRAY_SIZE = 10000000;

    public CompressedDictionary(String[] terms) {
        this.terms = terms;
        this.amountOfTerms = terms.length;
        int amountOfBlocks = (int) Math.ceil((double) amountOfTerms / (double) BLOCK_SIZE);
        this.blockIndexes = new int[amountOfBlocks];
        this.dictionary = new String(toDictionaryCharArray(terms, amountOfBlocks));
    }


    private char[] toDictionaryCharArray(String[] terms, int amountOfBlocks) {
        char[] dictionary = new char[DICTIONARY_ARRAY_SIZE];
        int dictionaryIndex = 0;

        for (int i = 0; i < amountOfBlocks; i++) {
            // the last incomplete block
            String[] blockTerms = new String[(i * BLOCK_SIZE + BLOCK_SIZE) <= amountOfTerms ? BLOCK_SIZE :
                    amountOfTerms % BLOCK_SIZE];
            if (blockTerms.length >= 0) {
                System.arraycopy(terms, i * 4, blockTerms, 0, blockTerms.length);
            }
            this.blockIndexes[i] = dictionaryIndex;
            char[] prefix = findCommonPrefix(blockTerms);
            if (prefix.length > 0) {
                dictionaryIndex = performFrontCoding(blockTerms, dictionary, dictionaryIndex, prefix);
            } else {
                dictionaryIndex = performBlockCompression(blockTerms, dictionary, dictionaryIndex);
            }
        }
        return copyResult(dictionary, dictionaryIndex);
    }

    private int performFrontCoding(String[] blockTerms, char[] dictionary, int dictionaryIndex, char[] prefix) {
        dictionary[dictionaryIndex++] = FRONT_CODING_MARK;
        int prefixLength = prefix.length;
        dictionaryIndex = insertNumber(dictionary, prefixLength, dictionaryIndex);
        for (char prefixChar : prefix) {
            dictionary[dictionaryIndex++] = prefixChar;
        }

        for (String blockTerm : blockTerms) {
            int termLength = blockTerm.length();
            dictionaryIndex = insertNumber(dictionary, termLength - prefixLength, dictionaryIndex);
            for (int i = prefixLength; i < termLength; i++) {
                dictionary[dictionaryIndex++] = blockTerm.charAt(i);
            }
        }
        return dictionaryIndex;
    }


    private int performBlockCompression(String[] blockTerms, char[] dictionary, int dictionaryIndex) {
        for (String blockTerm : blockTerms) {
            int termLength = blockTerm.length();
            dictionaryIndex = insertNumber(dictionary, termLength, dictionaryIndex);
            for (int i = 0; i < termLength; i++) {
                dictionary[dictionaryIndex++] = blockTerm.charAt(i);
            }
        }
        return dictionaryIndex;
    }


    private int insertNumber(char[] charArray, int number, int charArrayIndex) {
        char[] numberToWrite = toCharArray(number);
        for (char numberChar : numberToWrite) {
            charArray[charArrayIndex++] = numberChar;
        }
        return charArrayIndex;
    }


    private char[] findCommonPrefix(String[] strings) {
        int length = strings.length;
        if (length <= 1) {
            return new char[0];
        }
        int minLength = findMinStringLength(strings);
        char[] prefix = new char[minLength];
        int prefixLength = 0;
        for (int i = 0; i < minLength; i++) {
            boolean isSame = true;
            char currentChar = strings[0].charAt(i);
            for (int j = 1; j < length; j++) {
                if (strings[j].charAt(i) != currentChar) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                prefix[i] = currentChar;
                prefixLength = i + 1;
            } else {
                break;
            }
        }
        return copyResult(prefix, prefixLength);
    }


    private int findMinStringLength(String[] strings) {
        int min = Integer.MAX_VALUE;
        for (String string : strings) {
            if (string.length() < min) {
                min = string.length();
            }
        }
        return min;
    }

    private char[] toCharArray(int number) {
        return Integer.toString(number).toCharArray();
    }

    public String getTerm(int id) {
        if (id >= this.amountOfTerms) {
            return null;
        }

        int currentPosition = blockIndexes[id / BLOCK_SIZE];
        String prefix;
        if (dictionary.charAt(currentPosition) == FRONT_CODING_MARK) {
            currentPosition++;
            int prefixLength = getNumberPosition(currentPosition);
            currentPosition += getNumberLength(prefixLength);
            prefix = dictionary.substring(currentPosition, currentPosition + prefixLength);
            currentPosition += prefixLength;
        } else {
            prefix = "";
        }
        for (int i = 0, termPosInBlock = id % BLOCK_SIZE; i < termPosInBlock; i++) {
            int currentTermLength = getNumberPosition(currentPosition);
            currentPosition += getNumberLength(currentTermLength) + currentTermLength;
        }
        int postfixLength = getNumberPosition(currentPosition);
        currentPosition += getNumberLength(postfixLength);
        return prefix + dictionary.substring(currentPosition, currentPosition + postfixLength);
    }

    private int getNumberPosition(int position) {
        StringBuilder number = new StringBuilder();
        char character = dictionary.charAt(position++);
        if (character == '0') {
            return 0;
        }
        while (Character.isDigit(character)) {
            number.append(character);
            character = dictionary.charAt(position++);
        }
        return Integer.parseInt(number.toString());
    }


    public int getTermId(String term) {
        int lowest = 0;
        int highest = amountOfTerms - 1;
        int middle = (highest - lowest) / 2 + lowest;

        while (lowest <= highest) {
            int cmp = Objects.requireNonNull(getTerm(middle)).compareTo(term);
            if (cmp < 0) {
                lowest = middle + 1;
            } else if (cmp > 0) {
                highest = middle - 1;
            } else {
                return middle;
            }
            middle = (highest - lowest) / 2 + lowest;
        }
        return -1;
    }

    private int getNumberLength(int number) {
        return String.valueOf(number).length();
    }

    public String getDictionary() {
        return dictionary;
    }

    public int[] getBlockIndexes() {
        return blockIndexes;
    }

    public String[] getTerms() { return terms; }

    private char[] copyResult(char[] source, int length) {
        char[] result = new char[length];
        System.arraycopy(source, 0, result, 0, length);
        return result;
    }

}