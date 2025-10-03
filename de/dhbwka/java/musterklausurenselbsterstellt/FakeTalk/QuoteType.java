package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;
import javax.swing.ImageIcon;
import java.awt.Color;

public enum QuoteType {
    UNKNOWN("Unknown", FakeTalkIcons.ICON_UNKNOWN, Color.lightGray),
    HOT_SHIT("Hot shit", FakeTalkIcons.ICON_HOT_SHIT, Color.green),
    BULLSHIT("Bullshit", FakeTalkIcons.ICON_BULLSHIT, new Color(74, 65, 42));

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private QuoteType(String label, ImageIcon icon, Color color) {
        this.label = label;
        this.icon = icon;
        this.color = color;
    }
    private String label;
    private ImageIcon icon;
    private Color color;
}
