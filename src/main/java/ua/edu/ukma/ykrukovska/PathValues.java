package ua.edu.ukma.ykrukovska;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class PathValues {
    public static final String BOOK_PATH = "D://Studying//InformationRetrieval//fb2//";
    public static final String BOOK_PATH_BSBI = "D://Studying//InformationRetrieval//fb2//BSBI//";
    public static final String RESULT_PATH = "D://Studying//InformationRetrieval//results//";
    public static final String RESULT_PATH_BSBI = "D://Studying//InformationRetrieval//results//BSBI//";
    public static final List<String> FILES = Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "PrideAndPrejudice.fb2",
            BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
            BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
            BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2");

    public static final List<File> FILES_AS_FILES = Arrays.asList(new File(BOOK_PATH + "harrypotter1.fb2"), new File(BOOK_PATH + "PrideAndPrejudice.fb2"),
            new File(BOOK_PATH + "MonteCristo.fb2"), new File(BOOK_PATH + "DorianGray.fb2"), new File(BOOK_PATH + "JourneyEarth.fb2"),
            new File(BOOK_PATH + "TreasureIsland.fb2"), new File(BOOK_PATH + "AliceWonderland.fb2"),
            new File(BOOK_PATH + "GulliversTravels.fb2"), new File(BOOK_PATH + "CallOfWild.fb2"), new File(BOOK_PATH + "GrimmsFairyTales.fb2"));

    public static final List<File> FILES_AS_FILES_SHORT = Arrays.asList(new File(BOOK_PATH_BSBI + "test3.fb2"), new File(BOOK_PATH_BSBI + "test1.fb2"), new File(BOOK_PATH_BSBI + "test2.fb2")
            , new File(BOOK_PATH_BSBI + "test4.fb2"), new File(BOOK_PATH_BSBI + "test1.fb2"), new File(BOOK_PATH_BSBI + "test2.fb2"));


    public static final List<File> FILES_AS_FILES_SHORT_2 = Arrays.asList(new File(BOOK_PATH + "harrypotter1.fb2"), new File(BOOK_PATH + "jack.fb2"),
            new File(BOOK_PATH + "MonteCristo.fb2"), new File(BOOK_PATH + "DorianGray.fb2"), new File(BOOK_PATH + "JourneyEarth.fb2"), new File(BOOK_PATH + "AliceWonderland.fb2")
    );

    public static final List<String> FILES_SH = Arrays.asList(BOOK_PATH + "test1.fb2", BOOK_PATH + "test2.fb2", BOOK_PATH + "test3.fb2", BOOK_PATH + "test4.fb2");
}
