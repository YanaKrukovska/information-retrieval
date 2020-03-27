package ua.edu.ukma.ykrukovska.cluster;

import java.io.File;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH_CLUSTER;

public class ClusterTester {

    public static void main(String[] args) {
        ClusterIndex index = new ClusterIndex(3);
        index.createIndex(new File(BOOK_PATH_CLUSTER));
    }
}
