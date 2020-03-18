package ua.edu.ukma.ykrukovska.weight;


import java.util.Arrays;

public class PostingWeightZone implements Comparable<PostingWeightZone> {

    public enum ZoneScores {
        TITLE(0.3),
        AUTHOR(0.2),
        CONTENT(0.5);

        private final double weight;

        ZoneScores(double weight) {
            this.weight = weight;
        }

        public double getWeight() {
            return this.weight;
        }
    }

    @Override
    public int compareTo(PostingWeightZone that) {
        return Double.compare(this.getWeight(), that.getWeight());
    }

    public double getWeight() {
        double weight = 0;
        for (ZoneScores zoneScore : zoneScores) {
            weight += zoneScore.getWeight();
        }
        return weight;
    }

    private int id;
    private ZoneScores[] zoneScores;

    public int getId() {
        return id;
    }

    public PostingWeightZone(int id, ZoneScores zone) {
        this.id = id;
        this.zoneScores = new ZoneScores[1];
        this.zoneScores[0] = zone;
    }


    public void addZone(ZoneScores zoneScores) {
        for (ZoneScores zone : this.zoneScores) {
            if (zone == zoneScores) {
                return;
            }
        }
        ZoneScores[] temporaryScores = new ZoneScores[this.zoneScores.length + 1];
        System.arraycopy(this.zoneScores, 0, temporaryScores, 0, this.zoneScores.length);
        this.zoneScores = temporaryScores;
        this.zoneScores[this.zoneScores.length - 1] = zoneScores;
        Arrays.sort(this.zoneScores);
    }


    public String toString() {
        StringBuilder result = new StringBuilder(id + ":");
        for (ZoneScores zoneScore : zoneScores) {
            result.append(zoneScore.ordinal()).append(",");
        }
        return result.toString();
    }


}