package ua.edu.ukma.ykrukovska.compression;

import java.util.List;

class CompressedDictionary {
    private List<CompressedPostingList> compressedPostingList;

    public CompressedDictionary(List<CompressedPostingList> compressedPostingList) {
        this.compressedPostingList = compressedPostingList;
    }

}