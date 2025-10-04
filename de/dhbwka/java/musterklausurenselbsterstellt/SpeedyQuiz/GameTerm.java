package de.dhbwka.java.musterklausurenselbsterstellt.SpeedyQuiz;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameTerm extends JFrame implements GameClient {
    private String playerName;
    private int points;
    private Game game;
    private int remainingSeconds = 10;
    private boolean gameEnded = false;
    private Question selectedQuestion;
    private List<JButton> buttons = new ArrayList<>();
    private List<QuestionNumberLabel> labels = new ArrayList<>();
    private JPanel mainPanel = new JPanel(new GridLayout(4, 1));
    private JPanel labelsPanel = new JPanel();
    private JPanel questionPanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JLabel questionLabel = new JLabel();
    private JLabel timeLabel = new JLabel();
    private GameClient client = this;
    private LinkedHashMap<Question, QuestionNumberLabel> labelHashMap = new LinkedHashMap<>();

    public List<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<JButton> buttons) {
        this.buttons = buttons;
    }

    public List<QuestionNumberLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<QuestionNumberLabel> labels) {
        this.labels = labels;
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

    public JPanel getQuestionPanel() {
        return questionPanel;
    }

    public void setQuestionPanel(JPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public JPanel getTimePanel() {
        return timePanel;
    }

    public void setTimePanel(JPanel timePanel) {
        this.timePanel = timePanel;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public JLabel getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(JLabel questionLabel) {
        this.questionLabel = questionLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }

    public GameClient getClient() {
        return client;
    }

    public void setClient(GameClient client) {
        this.client = client;
    }

    public LinkedHashMap<Question, QuestionNumberLabel> getLabelHashMap() {
        return labelHashMap;
    }

    public void setLabelHashMap(LinkedHashMap<Question, QuestionNumberLabel> labelHashMap) {
        this.labelHashMap = labelHashMap;
    }

    public GameTerm(String playerName, Game game) {
        this.playerName = playerName;
        this.game = game;
        labelsPanel.setLayout(new GridLayout(1, game.getCountQuestions()));
        for (int i = 1; i <= game.getCountQuestions(); i++) {
            QuestionNumberLabel label = new QuestionNumberLabel();
            label.setText(String.valueOf(i));
            labels.add(label);
            labelsPanel.add(label);
            labelHashMap.put(game.getQuestions().get(i - 1), label);
        }
        mainPanel.add(labelsPanel);
        questionLabel.setText(game.getQuestions().get(game.getQuestionIndex()).getQuestionText());
        questionPanel.add(questionLabel);
        mainPanel.add(questionPanel);
        timeLabel.setText(String.valueOf(remainingSeconds));
        timePanel.add(timeLabel);
        mainPanel.add(timePanel);
        buttonsPanel.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton(game.getQuestions().get(game.getQuestionIndex()).getAnswers()[i]);
            button.putClientProperty("index", i);
            buttonsPanel.add(button);
            buttons.add(button);
            button.addActionListener(e-> {
                int index = (int) button.getClientProperty("index");
                System.out.println("index: " + index);
                game.answerSelected(client, index);
            });
        }
        mainPanel.add(buttonsPanel);

        this.setSize(500, 500);
        this.setTitle(playerName);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    @Override
    public int getPoints() {
        if(points < 0) {
            return 0;
        } else {
            return points;
        }
    }

    @Override
    public void setQuestion(int questionIndex, Question q) {
        // TODO: UI updaten
        // TODO: fixxen
        q.setQuestionStatus(Status.ACTIVE);
        questionLabel.setText(q.getQuestionText());
        for (int i = 0; i < buttons.size() ; i++) {
            System.out.println(Arrays.toString(q.getAnswers()));
            buttons.get(i).setText(q.getAnswers()[i]);
            buttons.get(i).repaint();
        }
    }

    @Override
    public void setRemainingSeconds(int seconds) {
        this.timeLabel.setText(String.valueOf(remainingSeconds));
        timeLabel.repaint();
        this.remainingSeconds = seconds;
    }

    @Override
    public void gameIsOver() {
        buttons.forEach(button->{button.setEnabled(false);});
        gameEnded = true;
    }

    @Override
    public void setAnswerState(int questionIndex, Status status) {
        // UI updaten
        if(status == Status.CORRECT) {
           points ++;
        } else if(status == Status.WRONG) {
            points --;
        }
        game.getQuestions().get(questionIndex).setQuestionStatus(status);
        QuestionNumberLabel label = labelHashMap.get(game.getQuestions().get(questionIndex));
        label.setBackground(game.getQuestions().get(questionIndex).getQuestionStatus().getColor());

    }
}
