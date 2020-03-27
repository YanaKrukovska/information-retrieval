package ua.edu.ukma.ykrukovska.cluster;

import java.util.Comparator;

public class CosineScoreSetComparator implements Comparator<CosineScoreElement> {

    public int compare(CosineScoreElement element1, CosineScoreElement element2) {
        return Double.compare(element2.getWeight(), element1.getWeight());
    }
}