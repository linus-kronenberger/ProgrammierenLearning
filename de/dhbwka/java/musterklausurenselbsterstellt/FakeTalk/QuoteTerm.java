import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuoteTerm extends JFrame implements FakeTalkClient{
    private String playerName;
    private Quote quote;
    private int points;
    private QuoteSelectionTerm quoteSelectionTerm;
    private QuoteDisplay quoteDisplay = new QuoteDisplay();
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JButton bullshitButton;
    private JButton hotButton;
    private FakeTalkClient currClient = this;
    private JPanel mainPanel = new JPanel();

    public QuoteTerm(String playerName, QuoteSelectionTerm quoteSelectionTerm) {

        this.playerName = playerName;
        this.quoteSelectionTerm = quoteSelectionTerm;
        this.setTitle(this.playerName);
        upperPanel.add(quoteDisplay);
        mainPanel.add(upperPanel);
        lowerPanel.setLayout(new GridLayout(1,2));
        hotButton = new JButton();
        hotButton.setIcon(QuoteType.HOT_SHIT.getIcon());
        bullshitButton = new JButton();
        
        bullshitButton.setIcon(QuoteType.BULLSHIT.getIcon());
        hotButton.setEnabled(false);
        bullshitButton.setEnabled(false);
        hotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quoteSelectionTerm.answerSelected(currClient, quote, quote.getType());
                quoteSelectionTerm.getThread1().interrupt();
            }
        });
        bullshitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quoteSelectionTerm.answerSelected(currClient, quote, quote.getType());
                quoteSelectionTerm.getThread1().interrupt();
            }
        });
        lowerPanel.add(hotButton);
        lowerPanel.add(bullshitButton);
        JLabel pointsLabel = new JLabel(this.points + " Points");
        bottomPanel.add(pointsLabel);
        mainPanel.add(lowerPanel);
        mainPanel.add(bottomPanel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public String getPlayerName() {
       return this.playerName;
    }
    @Override
    public void setQuote(Quote q) {
        this.quote = q;
        this.quoteDisplay.setText(q.getCitation());
        this.bullshitButton.setEnabled(true);
        this.hotButton.setEnabled(true);
    }
    @Override
    public void addPoints(int points) {
        this.points += points;
    }
    @Override
    public int getPoints() {
        return this.points;
    }
}
