package de.dhbw.ka.soedermemory;
import java.awt.Color;

public enum PlayerStatus {
    ACTIVE(Color.orange),
    WAITING(Color.BLACK),
    FINISHED(Color.GRAY);
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private PlayerStatus(Color color) {
        this.color = color;
    }

}
