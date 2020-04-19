package ua.edu.ukma.ykrukovska.compression;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Compression {
    public static Map<String, CompressedDictionary> dictionary = new TreeMap<>();


    public static void createCompressedDictionary(Map<String, Dictionary> sourceDictionary) throws IOException {
        CompressedDictionary dictionary;
        for (String term : sourceDictionary.keySet()) {
            dictionary = new CompressedDictionary(Compression.createPostingList(term, sourceDictionary.get(term).postings));
            Compression.dictionary.put(term, dictionary);
        }

    }

    public static void createCompressedIndex(File sourceIndex, File resultIndex) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(sourceIndex));
        BufferedWriter writerBuff = new BufferedWriter(new FileWriter(resultIndex));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] twoParts = line.split(":");
            String[] docs = twoParts[1].split(",");

            writerBuff.write(twoParts[0] + ":");

            int difference = 0;
            for (int i = 0; i < docs.length; i++) {

                if (i == 0) {
                    difference = Integer.parseInt(docs[i]) - difference;
                } else {
                    difference = Integer.parseInt(docs[i]) - Integer.parseInt(docs[i - 1]);

                }
                assert (difference >= 0);
                byte[] byteArr = getByteArray(getGammaCode(difference));
                for (int b : byteArr) {
                    writerBuff.write(b);
                }
            }

            writerBuff.write(System.lineSeparator());
            writerBuff.flush();
        }
    }


    public static void blockedCompression(Map<String, CompressedDictionary> sourceDictionary, File resultlFile) throws IOException {
        BufferedWriter blockCompressionWriter = new BufferedWriter(new FileWriter(resultlFile));
        int k = 0;
        String Blockterms[] = new String[8];
        for (String term : sourceDictionary.keySet()) {
            StringBuilder sb = new StringBuilder();
            if (k == 0) {
                Blockterms = new String[8];
            }
            if (k < 8) {
                if (sourceDictionary.get(term) == null)
                    break;
                Blockterms[k] = term.length() + term;
                k++;
            }
            if (k == 8) {
                int i = 0;
                while (i < 8) {
                    sb.append(Blockterms[i]);
                    i++;
                }
                blockCompressionWriter.write(sb.toString() + "\n");
                k = 0;
            }
        }
        blockCompressionWriter.flush();
        blockCompressionWriter.close();
        Iterator<Entry<String, CompressedDictionary>> itr = dictionary.entrySet().iterator();
    }


    private static List<CompressedPostingList> createPostingList(String term, List<PostingList> postingList) {
        List<CompressedPostingList> createCompressedPostingList = new LinkedList<>();
        int previousDocID = 0;
        for (PostingList postings : postingList) {
            int gap = postings.docID - previousDocID;
            byte[] gapByte;
            byte[] termFrequency;
            byte[] maxdocLengthByte;
            byte[] maxtfByte;
            gapByte = Compression.gammaEncoding(gap);
            termFrequency = Compression.gammaEncoding(postings.termFrequency);
            maxdocLengthByte = Compression.gammaEncoding(postings.documentLength);
            maxtfByte = Compression.gammaEncoding(postings.max_tf);

            CompressedPostingList CPL = new CompressedPostingList(gapByte, termFrequency, maxdocLengthByte, maxtfByte);
            createCompressedPostingList.add(CPL);
        }

        return createCompressedPostingList;
    }

    public static void frontCoding(Map<String, CompressedDictionary> invertedStemDictionary, File resultFile) throws IOException {
        BufferedWriter frontCodingWriter = new BufferedWriter(new FileWriter(resultFile));
        int k = 0;
        String Blockterms[] = new String[8];
        String firstTerm = "";
        for (String term : invertedStemDictionary.keySet()) {
            StringBuilder sb = new StringBuilder();
            if (k == 0) {
                Blockterms = new String[8];
                firstTerm = term;
            }
            if (k < 8) {
                if (invertedStemDictionary.get(term) == null)
                    break;
                Blockterms[k] = term;
                k++;
            }
            if (k == 8) {
                int m = 0;
                boolean flag = true;
                int block_length = 0;
                for (int j = 0; j < 8; j++) {
                    if (firstTerm.charAt(0) == Blockterms[j].charAt(0))
                        block_length += 1;
                }
                for (int i = 0; i < firstTerm.length(); i++) {
                    for (int j = 0; j < block_length; j++) {
                        if (firstTerm.charAt(i) != Blockterms[j].charAt(i) || Blockterms[j].length() < i) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        m++;
                    else break;
                }

                sb.append(firstTerm.length());
                sb.append(firstTerm, 0, m);
                sb.append("#");
                sb.append(firstTerm.substring(m));

                int i = 0;
                while (i < block_length) {
                    sb.append(Blockterms[i].substring(m).length());
                    sb.append("~");
                    sb.append(Blockterms[i].substring(m));
                    i++;
                }
                int j = block_length;
                if (block_length < 8) {
                    while (j < 8) {
                        sb.append(Blockterms[j].length());
                        sb.append(Blockterms[j]);
                        j++;
                    }
                }
                frontCodingWriter.write(sb.toString() + "\n");
                frontCodingWriter.flush();


                k = 0;
            }
        }
        frontCodingWriter.flush();
        frontCodingWriter.close();

        dictionary.entrySet().iterator();


    }

    private static byte[] convertStringToByteArray(String s) {
        BitSet bitset = new BitSet(s.length());
        for (int i = 0; i < s.length(); i++) {
            boolean value = s.charAt(i) == '1';
            bitset.set(i, value);
        }
        return bitset.toByteArray();
    }

    private static byte[] gammaEncoding(int number) {
        String gamma = "";
        String l = Integer.toBinaryString(number);
        int length_of_l = l.length();
        StringBuilder length;
        String offset;
        if (length_of_l == 1) {
            length = new StringBuilder("0");
            offset = "";
        } else {
            length = new StringBuilder();
            while (length_of_l > 1) {
                length.append("1");
                length_of_l--;
            }
            length.append("0");
            offset = l.substring(1);
        }
        gamma += length + offset;

        return convertStringToByteArray(gamma);

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

}







