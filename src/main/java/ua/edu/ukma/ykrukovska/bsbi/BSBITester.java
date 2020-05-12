package ua.edu.ukma.ykrukovska.bsbi;

import java.io.File;

public class BSBITester {

  public static final String GUTENBERG_PATH = "D://Studying//InformationRetrieval//gutenberg//gutenberg_txt//gutenberg//1";
  // public static final String GUTENBERG_PATH = "D://Studying//InformationRetrieval/fb2//bsbi_2";

    public static void main(String[] args) {
      File dir =  new File(GUTENBERG_PATH);
       BSBI bsbi = new BSBI( dir.listFiles());

        long start = System.nanoTime();
        bsbi.doBSBI();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }
}
