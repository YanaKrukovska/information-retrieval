package ua.edu.ukma.ykrukovska.compression;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.FILES_SH;

public class CompressedDictionaryTester {

    public static void main(String[] args) {
        CompressedDictionary compressedDictionary = CompressedDictionaryBuilder.createCompressedDictionary(FILES_SH);
        System.out.println(compressedDictionary.getDictionary());

        System.out.println();
        for (int i : compressedDictionary.getBlockIndexes()) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println(compressedDictionary.getTerm(23));

        System.out.println();
        for (int i = 0; i < compressedDictionary.getTerms().length; i += 1) {
            System.out.println(i + ": " + compressedDictionary.getTerm(i));
        }


    }
}
