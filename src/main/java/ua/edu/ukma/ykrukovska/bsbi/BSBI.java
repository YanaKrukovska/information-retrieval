package ua.edu.ukma.ykrukovska.bsbi;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH_BSBI;


public class BSBI {

    private File[] documents;
    private Queue<TermPostings> mergeQueue = new PriorityQueue<>();
    private int amountOfFinishedBlocks;
    private int amountOfBlocks;
    private int documentsPerBlock;
    private BSBIIndex indexer;
    private MergeIndexWriter mergeIndexWriter;
    private HashMap<Integer, TermPostings> generalList = new HashMap<>();


    public BSBI(File[] documents) {
        this.documents = documents;
    }

    public void doBSBI() {

        int amountOfDocuments = documents.length;
        documentsPerBlock = Math.round(Math.round(amountOfDocuments / (Math.log(amountOfDocuments))));

        List<File[]> blocksList = parseBlocks(documents, amountOfDocuments, documentsPerBlock);
        HashMap<String, Integer> termList = new HashMap<>();
        HashMap<String, Integer> documentList = new HashMap<>();

        generateIndex(blocksList, termList, documentList);


        File invertedIndexFile = new File(RESULT_PATH_BSBI + "bsbi_index.txt");
        File directoryOfBlocks = new File(RESULT_PATH_BSBI);

        amountOfFinishedBlocks = 0;
        amountOfBlocks = 0;

        try {


            for (File block : Objects.requireNonNull(directoryOfBlocks.listFiles())) {
                if (!block.getName().equals(invertedIndexFile.getName())) {
                    BlockIndexReader blockIndexReader = new BlockIndexReader(this, mergeQueue, block);
                    blockIndexReader.readBlockIndex();

                    amountOfBlocks++;
                }
            }

             mergeIndexWriter = new MergeIndexWriter(this, mergeQueue, invertedIndexFile);
            mergeIndexWriter.mergeAndWrite();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateIndex(List<File[]> blocksList, HashMap<String, Integer> termList, HashMap<String, Integer> documentList) {
        int blockNumber = 0;

        for (File[] block : blocksList) {
            BlockingQueue<String[]> queue = new LinkedBlockingQueue<>();

            BlockReader reader = new BlockReader(block, queue);
             indexer = new BSBIIndex(this, queue, termList, documentList, documents);

            reader.readBlocks();
            indexer.createIndex(blockNumber++, documentsPerBlock);


        }
    }

    private List<File[]> parseBlocks(File[] documents, int amountOfDocuments, int documentsPerBlock) {
        List<File[]> listOfBlocks = new ArrayList<>();
        for (int i = 0; i < amountOfDocuments; i += documentsPerBlock) {
            int size = (i + documentsPerBlock) <= amountOfDocuments ? documentsPerBlock : amountOfDocuments - i - 1;

            System.out.println("amountOfDocuments:" + amountOfDocuments + ", documentsPerBlock:" + size);

            File[] block = new File[size];
            System.arraycopy(documents, i, block, 0, size);
            listOfBlocks.add(block);
        }
        return listOfBlocks;
    }


    public void incrementBlockFinishedCounter() {
        amountOfFinishedBlocks++;
    }

    public boolean getBlocksFinishedCounter() {
        return amountOfFinishedBlocks == amountOfBlocks;
    }

    public int getAmountOfBlocks() {
        return amountOfBlocks;
    }

    public BSBIIndex getIndexer() {
        return indexer;
    }


    public HashMap<Integer, TermPostings> getGeneralList() {
        return generalList;
    }

}