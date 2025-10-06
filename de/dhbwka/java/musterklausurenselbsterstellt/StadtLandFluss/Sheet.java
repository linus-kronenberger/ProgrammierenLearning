package de.dhbwka.java.musterklausurenselbsterstellt.StadtLandFluss;

import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sheet extends JFrame {
    private Player player;
    private Game game;
    private JPanel mainPanel = new JPanel();
    private JPanel labelsPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JLabel pointsLabel = new JLabel("Punkte");
    private JLabel remainingSecondsLabel = new JLabel("Verbleibende Sekunden");
    private JLabel startingLetterLabel = new JLabel("Anfangsbuchstabe");
    private List<JLabel> columnLabels = new ArrayList<>();
    private List<JTextField> textFields = new ArrayList<>();
    private HashMap<ColumnType, JTextField> mapTextFields = new HashMap<>();
    private List<JLabel> pointsForTerms = new ArrayList<>();
    private  HashMap<ColumnType, JLabel> mapPointsForTerms = new HashMap<>();

    public void clear() {
        columnLabels.clear();
        textFields.clear();
        mapTextFields.clear();
        pointsForTerms.clear();
        mapPointsForTerms.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getLabelsPanel() {
        return labelsPanel;
    }

    public void setLabelsPanel(JPanel labelsPanel) {
        this.labelsPanel = labelsPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public JLabel getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(JLabel pointsLabel) {
        this.pointsLabel = pointsLabel;
    }

    public JLabel getRemainingSecondsLabel() {
        return remainingSecondsLabel;
    }

    public void setRemainingSecondsLabel(JLabel remainingSecondsLabel) {
        this.remainingSecondsLabel = remainingSecondsLabel;
    }

    public JLabel getStartingLetterLabel() {
        return startingLetterLabel;
    }

    public void setStartingLetterLabel(JLabel startingLetterLabel) {
        this.startingLetterLabel = startingLetterLabel;
    }

    public JLabel getActiveGamesLabel() {
        return activeGamesLabel;
    }

    public void setActiveGamesLabel(JLabel activeGamesLabel) {
        this.activeGamesLabel = activeGamesLabel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JLabel getExplicitPointsLabel() {
        return explicitPointsLabel;
    }

    public void setExplicitPointsLabel(JLabel explicitPointsLabel) {
        this.explicitPointsLabel = explicitPointsLabel;
    }

    public JLabel getExplicitRemSecondsLabel() {
        return explicitRemSecondsLabel;
    }

    public void setExplicitRemSecondsLabel(JLabel explicitRemSecondsLabel) {
        this.explicitRemSecondsLabel = explicitRemSecondsLabel;
    }

    public JLabel getExplicitStartingLetterLabel() {
        return explicitStartingLetterLabel;
    }

    public void setExplicitStartingLetterLabel(JLabel explicitStartingLetterLabel) {
        this.explicitStartingLetterLabel = explicitStartingLetterLabel;
    }

    private  JLabel activeGamesLabel = new JLabel("Kein Spiel aktiv.");
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JLabel explicitPointsLabel = new JLabel("0");
    private JLabel explicitRemSecondsLabel = new JLabel("15");
    private JLabel explicitStartingLetterLabel = new JLabel("");

    public List<JLabel> getColumnLabels() {
        return columnLabels;
    }

    public void setColumnLabels(List<JLabel> columnLabels) {
        this.columnLabels = columnLabels;
    }

    public List<JTextField> getTextFields() {
        return textFields;
    }

    public void setTextFields(List<JTextField> textFields) {
        this.textFields = textFields;
    }

    public HashMap<ColumnType, JTextField> getMapTextFields() {
        return mapTextFields;
    }

    public void setMapTextFields(HashMap<ColumnType, JTextField> mapTextFields) {
        this.mapTextFields = mapTextFields;
    }

    public List<JLabel> getPointsForTerms() {
        return pointsForTerms;
    }

    public void setPointsForTerms(List<JLabel> pointsForTerms) {
        this.pointsForTerms = pointsForTerms;
    }

    public HashMap<ColumnType, JLabel> getMapPointsForTerms() {
        return mapPointsForTerms;
    }

    public void setMapPointsForTerms(HashMap<ColumnType, JLabel> mapPointsForTerms) {
        this.mapPointsForTerms = mapPointsForTerms;
    }

    public Sheet(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.setTitle(player.getName());
        stopButton.setEnabled(false);

        mainPanel.setLayout(new BorderLayout());

        labelsPanel.setLayout(new GridLayout(3, 2));
        labelsPanel.add(pointsLabel);
        labelsPanel.add(explicitPointsLabel);
        labelsPanel.add(remainingSecondsLabel);
        labelsPanel.add(explicitRemSecondsLabel);
        labelsPanel.add(startingLetterLabel);
        labelsPanel.add(explicitStartingLetterLabel);

        centerPanel.add(activeGamesLabel);
        //centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(startButton);
        bottomPanel.add(stopButton);

        mainPanel.add(labelsPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);

        startButton.addActionListener(e-> {
            game.startGame();
        });
        stopButton.addActionListener(e->{
            game.stopGame();
        });
    }
}
