package de.dhbwka.java.musterklausurenselbsterstellt.ChatterGPT;

public enum ClassificationResultType {
    CORRECT("Correct", 10),
    INCORRECT("Incorrect", -10),
    NOT_CLASSIFIED("Not classified", 0);

    private String label;
    private int score;

    private ClassificationResultType(String label, int score) {
        this.label = label;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public String getLabel() {
        return this.label;
    }
}