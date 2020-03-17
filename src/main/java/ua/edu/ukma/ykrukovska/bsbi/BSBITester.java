package ua.edu.ukma.ykrukovska.bsbi;

import java.io.File;

import static ua.edu.ukma.ykrukovska.PathValues.FILES_AS_FILES;
import static ua.edu.ukma.ykrukovska.PathValues.FILES_AS_FILES_SHORT;
import static ua.edu.ukma.ykrukovska.PathValues.FILES_AS_FILES_SHORT_2;

public class BSBITester {

    public static void main(String[] args) {
        BSBI bsbi = new BSBI((File[]) FILES_AS_FILES_SHORT_2.toArray());
        bsbi.doBSBI();
    }
}
