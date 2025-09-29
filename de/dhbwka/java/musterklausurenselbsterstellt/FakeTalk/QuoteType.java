import javax.swing.ImageIcon;

public enum QuoteType {
    UNKNOWN("Unknown", FakeTalkIcons.ICON_UNKNOWN, java.awt.Color.LIGHT_GRAY),
    HOT_SHIT("Hot shit", FakeTalkIcons.ICON_HOT_SHIT, java.awt.Color.GREEN),
    BULLSHIT("Bullshit", FakeTalkIcons.ICON_BULLSHIT, new java.awt.Color(74, 65, 42));
    private String label;
    private ImageIcon Icon;
    private java.awt.Color Farbe;
    QuoteType(String label, ImageIcon Icon, java.awt.Color Farbe) {
        this.label = label;
        this.Icon = Icon;
        this.Farbe = Farbe;
    }
    public java.awt.Color getFarbe() {
        return this.Farbe;
    }
    public ImageIcon getIcon() {
        return this.Icon;
    }
    public String getLabel() {
        return this.label;
    }
}
