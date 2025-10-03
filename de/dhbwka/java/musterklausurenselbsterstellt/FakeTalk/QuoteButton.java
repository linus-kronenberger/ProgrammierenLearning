package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;

import javax.swing.*;

public class QuoteButton extends JButton {
    private QuoteType type = QuoteType.UNKNOWN;
    private Quote q;
    public QuoteButton() {
        this.setBackground(type.getColor());
        this.setIcon(type.getIcon());
    }
    public boolean isUnknown() {
        return type == QuoteType.UNKNOWN;
    }

    public QuoteType getType() {
        return type;
    }

    public void setType(QuoteType type) {
        this.type = type;
        this.setBackground(type.getColor());
        this.setIcon(type.getIcon());
    }

    public Quote getQ() {
        return q;
    }

    public void setQ(Quote q) {
        this.q = q;
    }
}
