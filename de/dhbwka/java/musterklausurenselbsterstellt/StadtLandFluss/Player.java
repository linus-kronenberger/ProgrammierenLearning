package de.dhbwka.java.musterklausurenselbsterstellt.StadtLandFluss;

public class Player {
    private String name;
    private int points = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player(String name) {
        this.name = name;
        this.points = points;
    }
}
