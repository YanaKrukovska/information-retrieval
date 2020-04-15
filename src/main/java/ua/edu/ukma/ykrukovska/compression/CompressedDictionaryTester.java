package ua.edu.ukma.ykrukovska.compression;

import java.io.File;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH_BSBI_2;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class CompressedDictionaryTester {


    public static void main(String args[]) throws Exception {


        Indexing.buildIndex(BOOK_PATH_BSBI_2);

        File uncompressedIndex = new File("D://Studying//InformationRetrieval//results//UncompressedIndex.txt");
        File compressedBlockRes = new File("D://Studying//InformationRetrieval//results//CompressedDict_Block.txt");
        File compressedFrontRes = new File("D://Studying//InformationRetrieval//results//CompressedDict_Front.txt");

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        Indexing.createUncompressedIndex(uncompressedIndex, Indexing.getInvertedDictionary());
        endTime = System.currentTimeMillis();
        System.out.println("Time for uncompressed index: " + (endTime - startTime) + " milliseconds");

        Compression.createCompressedDictionary(Indexing.getInvertedDictionary());

        startTime = System.currentTimeMillis();
        Compression.blockedCompression(Compression.dictionary, compressedBlockRes);
        endTime = System.currentTimeMillis();
        System.out.println("Time for dictionary block compression: " + (endTime - startTime) + " milliseconds");

        Compression.createCompressedDictionary(Indexing.getInvertedDictionary());

        startTime = System.currentTimeMillis();
        Compression.frontCoding(Compression.dictionary, compressedFrontRes);
        endTime = System.currentTimeMillis();
        System.out.println("Time for dictionary front compression: " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        Compression.createCompressedIndex(uncompressedIndex, new File(RESULT_PATH + "CompressedIndex.txt"));
        endTime = System.currentTimeMillis();
        System.out.println("Time for compressed index: " + (endTime - startTime) + " milliseconds");

        System.out.println();

    }

}
