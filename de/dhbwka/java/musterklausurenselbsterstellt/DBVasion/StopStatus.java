import java.awt.Color;

public enum StopStatus {
    //Verschiedene Halte
    ON_SCHEDULE(Color.BLACK, new Color(0, 150, 0) ),
    REASONABLE(Color.BLACK, new Color(150, 150, 225)),
    DELAYED(Color.WHITE, Color.RED);
    private Color textColor;
    private Color backgroundColor;
    private StopStatus(Color textColor, Color backgroundColor){
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }
    public static StopStatus get(int delay) {
        if(delay <= 0) {
            return ON_SCHEDULE;
        } else if(delay < 6){
            return REASONABLE;
        }
        else {
            return DELAYED;
        }
    }
    public Color getTextColor() {
        return this.textColor;
    }
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }
}
