import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuoteSelectionTerm extends JFrame implements Runnable{
    private int pointsToAdd = 10;
    private JLabel picklabel = new JLabel("Pick a quote: Muenchhausen");
    private JLabel downLabel = new JLabel("Points: 10");
    private JPanel upperPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel downPanel = new JPanel();
    private Boolean started = false;
    private List<FakeTalkClient> clientList = new ArrayList<>();
    private List<Quote> chosen;
    private List<QuoteButton> buttons = new ArrayList<>();
    private int currentClientIndex = 0;
    private long timerTime = -1;
    private Thread thread1;

    public QuoteSelectionTerm(List<Quote> quoteList, int zeilenAnzahl, int spaltenAnzahl) throws FakeNewsException {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        System.out.println(quoteList);
        int anzahlFelder = (int)(zeilenAnzahl * spaltenAnzahl);
        if( anzahlFelder > quoteList.size()) {
            throw new FakeNewsException("Provided quote catalog does not contain enough (hot|bull)shit!");
        }
        this.setTitle("FakeTalk");

        if(quoteList.size() >= anzahlFelder) {
            List<Quote> copy = new ArrayList<>(quoteList); // original unverändert lassen
            Collections.shuffle(copy);
            this.chosen = copy.subList(0, Math.min(anzahlFelder, copy.size()));
            for(int i=0; i<anzahlFelder; i++) {
                QuoteButton button = new QuoteButton();
                middlePanel.add(button);
                buttons.add(button);
                button.setQuote(chosen.get(i));
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        QuoteButton button = (QuoteButton)(e.getSource());
                        clientList.get(currentClientIndex).setQuote(button.getQuote());
                        button.setIcon(button.getType().getIcon());
                    }
                });
            }
        }
        mainPanel.add(upperPanel);
        upperPanel.add(picklabel);
        middlePanel.setLayout(new GridLayout(zeilenAnzahl, spaltenAnzahl));
        downPanel.add(downLabel);
        mainPanel.add(middlePanel);
        mainPanel.add(downPanel);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void register(FakeTalkClient client) throws FakeNewsException {
        if(this.clientList.contains(client)) {
            throw new FakeNewsException("The client is already registered.");
        } else {
            this.clientList.add(client);
        }
    }

    public void start() throws FakeNewsException {
        if(clientList.size() < 2) {
            throw new FakeNewsException("At least two clients must be registered to start the game.");
        }
        if(started == false) {
            started = true;
            this.pack();
            this.setVisible(true);
        }
    }

    public void answerSelected(FakeTalkClient client, Quote q, QuoteType selectedType) {
        JDialog dialog = new JDialog();
        JLabel firstLabel = new JLabel();
        firstLabel.setText("This quote is " + q.getType().getLabel() + "!");
        dialog.add(firstLabel);
        JLabel secondLabel = new JLabel("From: " + q.getCitation());
        dialog.add(secondLabel);
        if(selectedType == q.getType()) {
            client.addPoints(pointsToAdd);
        } else {
            client.addPoints(-10);
        }

        this.downLabel.setText("Points: " + client.getPoints());
        List<QuoteButton> buttonMatch = buttons.stream()
                .filter(button -> button.getQuote() == q)
                .collect(Collectors.toList());

        buttonMatch.get(0).setType(buttonMatch.get(0).getQuote().getType());
        List<QuoteButton> buttonsUnknown = buttons.stream()
            .filter(button -> button.getType() == QuoteType.UNKNOWN)
            .collect(Collectors.toList());
        if(buttonsUnknown.size() == 0) {
            JDialog endDialog = new JDialog();
            endDialog.setTitle("Game finished!");
            List<String> playerNames = clientList.stream()
                                                .map(clientItem -> clientItem.getPlayerName())
                                                .collect(Collectors.toList());
            List<Integer> playerPoints = clientList.stream()
                                                .map(clientItem -> clientItem.getPoints())
                                                .collect(Collectors.toList());
            String scoreText = "";           
            int index = 0;                             
            for(String playerName : playerNames) {
                scoreText += playerName += " (" + playerPoints.get(index) + " )";
                if(index < playerNames.size() - 1) {
                    scoreText += ", ";
                }
                index ++;
            }
            
            endDialog.add(new JLabel("Game finished. Score: " + scoreText));
            endDialog.pack();
            endDialog.setVisible(true);
            // Spiel beendet
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("fake-score.txt"))) {
                bw.write(scoreText);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if(currentClientIndex < clientList.size() - 1) {
                currentClientIndex ++;
            } else {
                currentClientIndex = 0;
            }
            clientList.get(currentClientIndex).setQuote(this.chosen.get(currentClientIndex));
            thread1 = new Thread(this);
            thread1.start();
        }
        buttons.stream()
            .filter(button -> button.getType() == QuoteType.UNKNOWN) // alle die im typ unknown sind
            .forEach(button -> button.setType(button.getQuote().getType()));
        timerTime = System.currentTimeMillis();
        dialog.pack();
        dialog.setVisible(true);
    }

    public Thread getThread1() {
        return thread1;
    }

    @Override
    public void run() {
        boolean runs = true;
        while (runs) {
            if(timerTime != -1) {
                if(System.currentTimeMillis() - timerTime > 2000 && pointsToAdd > 1) {
                    pointsToAdd -= 1;
                }
            }
            if(pointsToAdd == 1) {
                this.thread1.interrupt();
            }
        }
    }
}