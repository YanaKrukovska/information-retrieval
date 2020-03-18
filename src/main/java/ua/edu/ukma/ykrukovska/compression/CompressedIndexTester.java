package ua.edu.ukma.ykrukovska.compression;

import java.io.*;
import java.util.BitSet;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class CompressedIndexTester {

    private void buildCompressedIndex() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(BOOK_PATH + "TestIndex.txt")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(RESULT_PATH + "index_compressed.txt")));
            String line;
            String[] input;
            int difference = 0;

            while ((line = bufferedReader.readLine()) != null) {
                input = line.split(",");
                for (String inputLine : input) {
                    difference = Integer.parseInt(inputLine) - difference;
                    assert (difference >= 0);
                    byte[] byteArr = getByteArray(getGammaCode(difference));
                    System.out.println(getGammaCode(difference));
                    for (int b : byteArr) {
                        bufferedWriter.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static byte[] getByteArray(String code) {
        BitSet bits = new BitSet(code.length());
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                bits.set(i, true);
            } else {
                bits.set(i, false);
            }
        }
        return bits.toByteArray();
    }


    private static String getGammaCode(int gap) {
        String binaryNumber = Integer.toBinaryString(gap);
        String offset = binaryNumber.substring(1);
        int length = offset.length();
        String unary = getUnaryCode(length);
        return unary + offset;
    }


    private static String getUnaryCode(int length) {
        StringBuilder unary = new StringBuilder();
        while (length > 0) {
            unary.append("1");
            length--;
        }
        unary.append("0");
        return unary.toString();
    }

    public static void main(String[] args) {
        CompressedIndexTester compressedIndexTester = new CompressedIndexTester();
        compressedIndexTester.buildCompressedIndex();
    }


}
