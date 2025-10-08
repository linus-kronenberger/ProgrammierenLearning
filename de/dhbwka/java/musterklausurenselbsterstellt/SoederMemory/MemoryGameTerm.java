package de.dhbw.ka.soedermemory;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryGameTerm extends JFrame {
    private MemoryGame memoryGame;
    private JPanel mainPanel = new JPanel();
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private List<JLabel> nameLabels = new ArrayList<>();
    private HashMap<Player, JLabel> labelMap= new HashMap<>();
    private List<JToggleButton> toggleButtons = new ArrayList<>();
    private LinkedHashMap<JToggleButton, ImageIcon> imageMap = new LinkedHashMap<>();
    private int opened = 0;
    private int rounds = 0;
    private int openedAtAll = 0;
    private List<JToggleButton> openedButtons = new ArrayList<>();
    public MemoryGameTerm(MemoryGame memoryGame) {
        this.memoryGame = memoryGame;
        memoryGame.setCurrentPlayer(memoryGame.getPlayers().get(0));

        mainPanel.setLayout(new BorderLayout());
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        for (Player player : memoryGame.getPlayers()) {
            JLabel label = new JLabel(player.getName() + " (0)");
            label.setBackground(player.getStatus().getColor());
            upperPanel.add(label);
            nameLabels.add(label);
            labelMap.put(player, label);
        }

        mainPanel.add(upperPanel, BorderLayout.NORTH);
        lowerPanel.setLayout(new GridLayout(memoryGame.getRows(), memoryGame.getCols()));
        int count = memoryGame.getRows() * memoryGame.getCols();
        if(memoryGame.isBlancRequired() == true) {
            count --;
        }

        for (int i = 0; i < count; i++) {
            JToggleButton toggleButton = new JToggleButton();
            imageMap.put(toggleButton, memoryGame.getImages().get(i).getImage());
            toggleButton.setIcon(MemoryImages.getBackside());
            toggleButton.addActionListener(e->{

                 JToggleButton button = (JToggleButton) e.getSource();
                 if(opened == 0) {
                    opened ++;
                    button.setIcon(imageMap.get(button));
                    openedButtons.add(button);
                } else {
                     button.setIcon(imageMap.get(button));
                    opened = 0;
                    rounds ++;
                    openedButtons.add(button);
                    if(openedButtons.get(0).getIcon().equals(openedButtons.get(1).getIcon())) {
                        openedAtAll+=2;
                        if(openedAtAll == memoryGame.getCols() * memoryGame.getRows()) {
                            memoryGame.setRuns(false);
                        }
                        memoryGame.getCurrentPlayer().setPoints(memoryGame.getCurrentPlayer().getPoints() + 1);
                        openedButtons.get(0).setEnabled(false);
                        openedButtons.get(1).setEnabled(false);
                        if(!memoryGame.isRuns()){
                            System.out.println("Game Over");
                            memoryGame.getPlayers().forEach(p->{
                                p.setStatus(PlayerStatus.FINISHED);
                                labelMap.get(p).setBackground(p.getStatus().getColor());
                            });
                            String lastGamesString = "";
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("memory.txt", true))) {
                                ;
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try (BufferedReader br = new BufferedReader(new FileReader("memory.txt"))) {
                                while (br.ready()) {
                                    String line = br.readLine();
                                    lastGamesString += line+"\n";
                                    System.out.println("Line Read: " + line);
                                }
                                br.close();
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }

                            String scoresString = "";
                            List<Player> playerListNew = memoryGame.getPlayers().stream().sorted(Comparator.comparingInt(Player::getPoints).reversed())
                                    .toList();

                            for (Player pl : playerListNew) {
                                scoresString += pl.getName() + " (" + pl.getPoints() + ")";
                                if(!pl.equals(playerListNew.get(playerListNew.size() - 1))) {
                                    scoresString += ",";
                                }
                            }

                            String resultString = "Game ends after " + rounds + " rounds, scores:" + scoresString;

                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("memory.txt", true))) {
                                bw.write(resultString);
                                bw.newLine();
                                bw.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(null, resultString + "\n\n Last games:\n" + lastGamesString, "Score", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry, those don't match", "Wrong", JOptionPane.ERROR_MESSAGE);
                        openedButtons.get(0).setIcon(MemoryImages.getBackside());
                        openedButtons.get(1).setIcon(MemoryImages.getBackside());
                    }
                    labelMap.get(memoryGame.getCurrentPlayer()).setText(memoryGame.getCurrentPlayer().getName() + "(" +memoryGame.getCurrentPlayer().getPoints() + ")");
                     labelMap.get(memoryGame.getCurrentPlayer()).setBackground(memoryGame.getCurrentPlayer().getStatus().getColor());
                     memoryGame.nextPlayer();
                    openedButtons.clear();
                }
            });
            toggleButtons.add(toggleButton);
            lowerPanel.add(toggleButton);
        }


        if(memoryGame.isBlancRequired() == true) {
            openedAtAll++;
            JToggleButton toggleButton = new JToggleButton();
            toggleButton.setIcon(MemoryImages.getBlank());
            lowerPanel.add(toggleButton);
            toggleButtons.add(toggleButton);
        }


        mainPanel.add(lowerPanel, BorderLayout.CENTER);

        this.add(mainPanel);
        this.setTitle("Soeder Memory");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Runnable runnable = () -> {
            int i = 0;
            this.setTitle(this.getTitle() + "("+String.valueOf(i)+")");
            while (memoryGame.isRuns()) {
                try {
                    Thread.sleep(1000);
                    i++;
                    this.setTitle(this.getTitle().substring(0, 13) + "("+String.valueOf(i)+")");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
