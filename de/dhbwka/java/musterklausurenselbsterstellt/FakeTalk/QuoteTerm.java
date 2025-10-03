package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;

import javax.swing.*;
import java.awt.*;

public class QuoteTerm extends JFrame implements FakeTalkClient {
    private String playerName;
    private QuoteSelectionTerm quoteSelectionTerm;
    private int points = 0;
    private Quote quote;
    private QuoteDisplay quoteDisplay = new QuoteDisplay();
    private JButton hotButton = new JButton();
    private JButton bullshitButton = new JButton();
    private JPanel buttonsPanel = new JPanel();
    private JLabel pointsLabel = new JLabel();
    public QuoteTerm(String playerName, QuoteSelectionTerm selectionTerm) {
        hotButton.setIcon(QuoteType.HOT_SHIT.getIcon());
        bullshitButton.setIcon(QuoteType.BULLSHIT.getIcon());
        this.playerName = playerName;
        this.quoteSelectionTerm = selectionTerm;
        this.add(quoteDisplay, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.add(hotButton);
        buttonsPanel.add(bullshitButton);
        hotButton.setEnabled(false);
        bullshitButton.setEnabled(false);
        pointsLabel.setText(points + " Points");
        this.add(pointsLabel, BorderLayout.SOUTH);
        this.setTitle(this.playerName);
        this.setSize(500, 500);
        this.setVisible(true);
        hotButton.addActionListener(e->{
            JOptionPane.showMessageDialog(this, quote.getCitation());
            quoteSelectionTerm.answerSelected(quoteSelectionTerm.getCurrentClient(), quote, quote.getType());
        });
        bullshitButton.addActionListener(e->{
            JOptionPane.showMessageDialog(this, quote.getCitation());
            quoteSelectionTerm.answerSelected(quoteSelectionTerm.getCurrentClient(), quote, quote.getType());
        });
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public QuoteSelectionTerm getQuoteSelectionTerm() {
        return quoteSelectionTerm;
    }

    public void setQuoteSelectionTerm(QuoteSelectionTerm quoteSelectionTerm) {
        this.quoteSelectionTerm = quoteSelectionTerm;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    @Override
    public Quote getQuote() {
        return quote;
    }

    public QuoteDisplay getQuoteDisplay() {
        return quoteDisplay;
    }

    public void setQuoteDisplay(QuoteDisplay quoteDisplay) {
        this.quoteDisplay = quoteDisplay;
    }

    public JButton getHotButton() {
        return hotButton;
    }

    public void setHotButton(JButton hotButton) {
        this.hotButton = hotButton;
    }

    public JButton getBullshitButton() {
        return bullshitButton;
    }

    public void setBullshitButton(JButton bullshitButton) {
        this.bullshitButton = bullshitButton;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(JLabel pointsLabel) {
        this.pointsLabel = pointsLabel;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setQuote(Quote q) {
        // im UI implementieren
        this.quote = q;
        quoteDisplay.setText(q.getText());
        hotButton.setEnabled(true);
        bullshitButton.setEnabled(true);
    }

    @Override
    public void addPoints(int points) {
        // im UI implementieren
        this.points += points;
        this.pointsLabel.setText(this.points + " Points");
    }

    @Override
    public int getPoints() {
        return points;
    }
}
