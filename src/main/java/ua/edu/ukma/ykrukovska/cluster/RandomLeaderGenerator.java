package ua.edu.ukma.ykrukovska.cluster;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomLeaderGenerator {
    private Random random;
    private Set<Integer> generatedLeaders;
    private int corpusSize;

    public RandomLeaderGenerator(int max) {
        corpusSize = max;
        random = new Random();
        generatedLeaders = new HashSet<>();
    }

    public Integer getNextLeader() {
        int nextLeader = random.nextInt(corpusSize);

        while (generatedLeaders.contains(nextLeader)) {
            nextLeader = random.nextInt(corpusSize);
        }

        generatedLeaders.add(nextLeader);

        return nextLeader;
    }
}