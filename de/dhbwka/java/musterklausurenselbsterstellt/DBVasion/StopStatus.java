package de.dhbwka.java.musterklausurenselbsterstellt.DBVasion;

import java.awt.*;

public enum StopStatus {
    ON_SCHEDULE(Color.black, new Color(0, 150, 0)),
    REASONABLE(Color.black, new Color(150, 150, 255)),
    DELAYED(Color.white, Color.red);
    // HALTE EINER FAHRT
    private Color textColor;
    private Color backgroundColor;

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private StopStatus(Color textColor, Color backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }
    public static StopStatus get(int delay) {
        if(delay <= 0) {
            return StopStatus.ON_SCHEDULE;
        } else if(delay < 6) {
            return StopStatus.REASONABLE;
        } else {
            return StopStatus.DELAYED;
        }
    }
}
