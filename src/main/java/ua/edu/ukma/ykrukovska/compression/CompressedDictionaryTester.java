package ua.edu.ukma.ykrukovska.compression;

import com.sun.org.apache.regexp.internal.RE;
import ua.edu.ukma.ykrukovska.dictionary.Collection;
import ua.edu.ukma.ykrukovska.dictionary.CollectionBuilder;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.FILES_SH;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class CompressedDictionaryTester {

    public static void main(String[] args)  {
        long start = System.nanoTime();
        CompressedDictionary compressedDictionary = CompressedDictionaryBuilder.createCompressedDictionary(FILES_SH);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_PATH + "CompressedDictionaryResult.txt"))) {
                writer.write(compressedDictionary.getDictionary());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
