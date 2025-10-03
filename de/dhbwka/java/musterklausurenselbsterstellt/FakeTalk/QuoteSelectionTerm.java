package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuoteSelectionTerm extends JFrame implements Runnable {
    private JLabel aufforderungLabel;
    private JLabel pointsToAddLabel;
    private List<QuoteButton> buttons = new ArrayList<>();
    private HashMap<Quote, QuoteButton> buttonMap = new HashMap<>();
    private FakeTalkClient currentClient;
    private List<Quote> quotes;
    private int rows;
    private int cols;
    private int pointsToAdd = 10;
    private JPanel gridPanel = new JPanel();
    private List<FakeTalkClient> clients = new ArrayList<>();
    private boolean started = false;
    private boolean ended = false;
    private int clientIndex = 0;
    private long currentTime = System.currentTimeMillis();
    private Thread thread = new Thread(this);
    private Quote currentQuote;

    @Override
    public void run() {
        while(pointsToAdd > 1) {
            if ((System.currentTimeMillis() - currentTime) > 2000) {
                pointsToAdd--;
                currentTime = System.currentTimeMillis();
                pointsToAddLabel.setText(pointsToAdd + " Points");
                pointsToAddLabel.repaint();
            }
        }
    }

    public Quote getCurrentQuote() {
        return this.currentQuote;
    }

    public JLabel getAufforderungLabel() {
        return aufforderungLabel;
    }

    public void setAufforderungLabel(JLabel aufforderungLabel) {
        this.aufforderungLabel = aufforderungLabel;
    }

    public JLabel getPointsToAddLabel() {
        return pointsToAddLabel;
    }

    public void setPointsToAddLabel(JLabel pointsToAddLabel) {
        this.pointsToAddLabel = pointsToAddLabel;
    }

    public List<QuoteButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<QuoteButton> buttons) {
        this.buttons = buttons;
    }

    public HashMap<Quote, QuoteButton> getButtonMap() {
        return buttonMap;
    }

    public void setButtonMap(HashMap<Quote, QuoteButton> buttonMap) {
        this.buttonMap = buttonMap;
    }

    public FakeTalkClient getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(FakeTalkClient currentClient) {
        this.currentClient = currentClient;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getPointsToAdd() {
        return pointsToAdd;
    }

    public void setPointsToAdd(int pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public List<FakeTalkClient> getClients() {
        return clients;
    }

    public void setClients(List<FakeTalkClient> clients) {
        this.clients = clients;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public int getClientIndex() {
        return clientIndex;
    }

    public void setClientIndex(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public QuoteSelectionTerm(List<Quote> quotes, int rows, int cols) throws  FakeNewsException {
        this.quotes = quotes;
        this.rows = rows;
        this.cols = cols;
        this.setTitle("FakeTalk");
        aufforderungLabel = new JLabel("Pick a quote: ");
        this.add(aufforderungLabel, BorderLayout.NORTH);
        pointsToAddLabel = new JLabel(pointsToAdd + " Points");
        this.add(pointsToAddLabel, BorderLayout.SOUTH);

        if(quotes.size() < rows * cols) {
            throw new FakeNewsException("Provided quote catalog does not contain enough (hot|bull)shit!");
        }

        gridPanel.setLayout(new GridLayout(rows, cols));

        Collections.shuffle(quotes);
        for (int i = 0; i < rows*cols; i++) {
            QuoteButton button = new QuoteButton();
            button.setQ(quotes.get(i));
            buttons.add(button);
            buttonMap.put(quotes.get(i), button);
            gridPanel.add(button);
            button.addActionListener(e-> {
                QuoteButton source = (QuoteButton) e.getSource();
                currentClient.setQuote(source.getQ());
                buttons.forEach(b->{b.setEnabled(false);});
            });
        }

        this.add(gridPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        thread.start();
    }

    public void register(FakeTalkClient client) throws FakeNewsException{
        clients.add(client);
        if(started == true) {
            throw new FakeNewsException("Game was already started. CLient can not be registered");
        }
    }

    public void answerSelected(FakeTalkClient client, Quote q, QuoteType selectedType) {
        if(selectedType == q.getType()) {
            client.addPoints(pointsToAdd);
        } else {
            client.addPoints(pointsToAdd * -1);
        }
        buttonMap.get(q).setType(q.getType());
        List<QuoteButton> buttonsUnknown = buttonMap.values()
                .stream()
                .filter(b->{return b.isUnknown();})
                .toList();
        buttonsUnknown.forEach(b->{b.setEnabled(true);});
        if(buttonsUnknown.isEmpty()) {
            String scoreSummary = "";
            for(FakeTalkClient c : clients) {
                if(c != clients.get(clients.size() - 1)) {
                    scoreSummary += c.getPlayerName() + "(" + c.getPoints() + ")" + ",";
                } else {
                    scoreSummary += c.getPlayerName() + "(" + c.getPoints() + ")";
                }
            }
            JOptionPane.showMessageDialog(this, "Game finished. Score: " + scoreSummary);
            this.ended = true;
            // im Dialog angezeigt Nachricht jetzt auch in fake-score.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("de/dhbwka/java/musterklausurenselbsterstellt/FakeTalk/fake-score.txt", true))) {
                bw.write("Game finished. Score: " + scoreSummary);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.started = false;
        } else {
            // nächsten Spieler auswählen
            if(clientIndex == clients.size() - 1) {
                QuoteTerm c = (QuoteTerm) client;
                c.getHotButton().setEnabled(false);
                c.getBullshitButton().setEnabled(false);

                clientIndex = 0;
                currentClient = clients.get(clientIndex);
                aufforderungLabel.setText(aufforderungLabel.getText() + currentClient.getPlayerName());

            } else {
                QuoteTerm c = (QuoteTerm) client;
                c.getHotButton().setEnabled(false);
                c.getBullshitButton().setEnabled(false);
                clientIndex ++;
                currentClient = clients.get(clientIndex);
                aufforderungLabel.setText(aufforderungLabel.getText() + currentClient.getPlayerName());
            }

        }
    }

    public void start() throws FakeNewsException {
        if(clients.size() < 2) {
            throw new FakeNewsException("More clients would be nice!");
        }
        currentClient = clients.get(this.clientIndex);
        aufforderungLabel.setText(aufforderungLabel.getText() + currentClient.getPlayerName());
        started = true;
    }
}
