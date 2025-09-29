import javax.swing.JButton;

public class QuoteButton extends JButton{
    private QuoteType type;
    private Quote quote;
    public QuoteButton() {
        this.type = QuoteType.UNKNOWN;
        this.setBackground(type.getFarbe());
        this.setIcon(type.getIcon());
    }
    public boolean isUnknown() {
        if(this.type == QuoteType.UNKNOWN) {
            return true;
        } else {
            return false;
        }
    }
    public void setQuote(Quote q) {
        this.quote = q;
    }
    public Quote getQuote() {
        return this.quote;
    }
    public void setType(QuoteType t) {
        this.type = t;
    }

    public QuoteType getType() {
        return this.type;
    }
}