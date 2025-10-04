package de.dhbwka.java.musterklausurenselbsterstellt.SpeedyQuiz;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Runnable{
    private List<Question> questions = new ArrayList<>();
    private int countQuestions = 10;
    private List<GameClient> clients = new ArrayList<>();
    private boolean gameStarted = false;
    private int questionIndex = 0;
    private long startTime = System.currentTimeMillis();
    private long timerTimer = startTime;
    private Thread thread = new Thread(this);
    private boolean answerGiven = false;
    private int countdown = 10;
    private boolean gameEnded = false;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public List<GameClient> getClients() {
        return clients;
    }

    public void setClients(List<GameClient> clients) {
        this.clients = clients;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
        // nebenläufigkeit starten
        //thread.start();
    }

    public Game(List<Question> questionPool, int countQuestions) {
        // Zufällige Auswahl Fragen
        Collections.shuffle(questionPool);
        if(questionPool.size() < countQuestions) {
            throw new GameException("Too few questions available");
        }
        for (int i = 0; i < countQuestions; i++) {
            questions.add(questionPool.get(i));
        }

        this.countQuestions = countQuestions;
    }

    public void registerClient(GameTerm client) {
        if(!gameStarted) {
            clients.add(client);
        }
    }

    public void answerSelected(GameClient client, int index) {
        answerGiven = true;
        // wenn richtig dann points ++
        // sonst points --
        GameTerm clientTerm = (GameTerm) client;
        if(questions.get(questionIndex).getCorrectIndex() == index) {
            System.out.println("Richtige Antwort ausgewählt");
            //TODO: Fragelogik überprüfen
            clientTerm.setAnswerState(questionIndex, Status.CORRECT);
        } else if(questions.get(questionIndex).getCorrectIndex() != index){
            System.out.println("Falsche Antwort ausgewählt");
            clientTerm.setAnswerState(questionIndex, Status.WRONG);
        }
        clients.stream()
                .filter(c-> c!= client)
                .forEach(cl -> {cl.setAnswerState(questionIndex, Status.NO_ANSWER);});
        // Nächste Frage
        nextQuestion();
    }

    public void nextQuestion() {
        countdown = 10;

        thread = new Thread(this);
        if(questions.size() - 1 == questionIndex) {
            clients.forEach(GameClient::gameIsOver);
            gameEnded = true;
            int timeSeconds = (int)((System.currentTimeMillis() - startTime) / 1000);
            String scoreText = "";

            for(GameClient cl : clients) {
                scoreText += cl.getPlayerName() + "(" + cl.getPoints() + ")";
                if(clients.get(clients.size() - 1) != cl) {
                    scoreText += ",";
                }
            }
            String optionMeldung = "Game finished after " + String.valueOf(timeSeconds) + ", score: " + scoreText;
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), optionMeldung);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("de/dhbwka/java/musterklausurenselbsterstellt/SpeedyQuiz/highscore.txt", true))) {
                bw.write(scoreText);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            questionIndex ++;
            if(gameEnded == false) {
                countdown = 10;
                thread.start();
            }
        }
        //TODO: überprüfen
        clients.forEach(cli->{cli.setQuestion(questionIndex, questions.get(questionIndex));});
    }

    public void startGame() {
        gameStarted = true;
        thread.start();
        // startet Spiel für alle clients
        clients.forEach(client->{client.setQuestion(questionIndex, questions.get(questionIndex));});
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    @Override
    public void run() {
        while(!gameEnded && countdown > -1) {
            if(System.currentTimeMillis() - timerTimer >= 1000) {
                timerTimer = System.currentTimeMillis();
                countdown --;
                if(countdown > -1) {
                    for (GameClient client : clients) {
                        client.setRemainingSeconds(countdown);
                    }
                }
            }
        }
        if(countdown == -1) {
            for (GameClient client : clients) {
                client.setRemainingSeconds(10);
            }
            clients.stream()
                    .forEach(cl -> {cl.setAnswerState(questionIndex, Status.NO_ANSWER);});
            clients.stream()
                    .map(cl->{return (GameTerm) cl;})
                            .forEach(gt->{gt.getLabelHashMap().get(questions.get(questionIndex)).setBackground(Status.NO_ANSWER.getColor());});

            nextQuestion();
            //TODO: anpassen
        }
    }
}
