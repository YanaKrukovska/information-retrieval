package ua.edu.ukma.ykrukovska.cluster;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomLeaderGenerator {
    private Random random;
    private Set<Integer> generatedLeaders;
    private int amountOfDocuments;

    public RandomLeaderGenerator(int max) {
        amountOfDocuments = max;
        random = new Random();
        generatedLeaders = new HashSet<>();
    }

    public int getNextLeader() {
        int nextLeader = random.nextInt(amountOfDocuments);

        while (generatedLeaders.contains(nextLeader)) {
            nextLeader = random.nextInt(amountOfDocuments);
        }

        generatedLeaders.add(nextLeader);

        return nextLeader;
    }
}