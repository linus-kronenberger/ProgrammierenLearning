import javax.print.attribute.standard.SheetCollate;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Game {
    private char startingLetter;
    private List<ColumnType> columns = new ArrayList<>(); //Auswahl an Spalten
    private int secondsRemaining;
    private boolean running = false;
    private int min;
    private int max;
    private int seconds; // Gesamtdauer des Spiels
    private List<Sheet> sheets = new ArrayList<>();
    private List<String> validWords = new ArrayList<>();

    public Game(int min, int max, int seconds) {
        this.min = min;
        this.max = max;
        this.seconds = seconds;
        if(!(min>=3 && max>min)) {
            min = 3;
            max = min;
        }
    }

    public List<ColumnType> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnType> columns) {
        this.columns = columns;
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
    }

    public void setSecondsRemaining(int secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public char createFirstCharacter() {
        // Wert zwischen 65 und 90 ermitteln
        // Wert zwischen 0 und 25 + 65
        return (char) ((int)(Math.random() * 25) + 65);
    }

    public char getStartingLetter() {
        return startingLetter;
    }

    public void setStartingLetter(char startingLetter) {
        this.startingLetter = startingLetter;
    }

    public List<ColumnType> createColumns() {
        // Anzahl Spalten erzeugen zwischen min und max
        int randomValue = (int) (Math.random() * (max - min) + min);
        List<ColumnType> cols = new ArrayList<>();
        cols.add(ColumnType.CITY);
        cols.add(ColumnType.COUNTRY);
        cols.add(ColumnType.RIVER);
        randomValue -= 3;
        List<ColumnType> all = new ArrayList<>(Arrays.asList(ColumnType.values()));
        all.remove(ColumnType.CITY);
        all.remove(ColumnType.COUNTRY);
        all.remove(ColumnType.RIVER);
        Collections.shuffle(all);
        for (int i = 0; i < randomValue; i++) {
            cols.add(all.get(i));
        }
        return cols;
    }
    public void register(Sheet sheet) {
        sheets.add(sheet);
    }
    public void readIn() {
        try (BufferedReader br = new BufferedReader(new FileReader("validwords.txt"))) {
            while (br.ready()) {
                String line = br.readLine();
                System.out.println("Line Read: " + line);
                validWords.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startGame() {
        this.readIn();

        if(!running) {
            running = true;
            columns.clear();
            columns = createColumns();
            startingLetter = createFirstCharacter();
            sheets.forEach(s->{
                s.getExplicitRemSecondsLabel().setText("15");
                s.getStartingLetterLabel().setText(String.valueOf(startingLetter));
                s.getStartButton().setEnabled(false);
                s.getStopButton().setEnabled(true);
                s.getCenterPanel().removeAll();

                columns.forEach(c-> {
                    JPanel xPanel = new JPanel();
                    s.getCenterPanel().setLayout(new BoxLayout(s.getCenterPanel(), BoxLayout.Y_AXIS));
                    xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.X_AXIS));
                    JLabel columnLabel = new JLabel(c.getTitle());
                    s.getColumnLabels().add(columnLabel);
                    JTextField columnField = new JTextField();
                    s.getTextFields().add(columnField);
                    JLabel columnPoints = new JLabel("0");
                    s.getPointsForTerms().add(columnPoints);
                    xPanel.add(columnLabel);
                    xPanel.add(columnField);
                    xPanel.add(columnPoints);
                    s.getMapTextFields().put(c, columnField);
                    s.getCenterPanel().add(xPanel);
                    s.getCenterPanel().revalidate();
                    s.getCenterPanel().repaint();
                    s.getMapPointsForTerms().put(c, columnPoints);
                    // private List<JLabel> columnLabels = new ArrayList<>(); ->
                    //    private List<JTextField> textFields = new ArrayList<>(); ->
                    //    private HashMap<ColumnType, JTextField> mapTextFields = new HashMap<>(); ->
                    //    private List<JLabel> pointsForTerms = new ArrayList<>();  ->
                    //    private  HashMap<ColumnType, JLabel> mapPointsForTerms = new HashMap<>();

                });
            });
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRunning()) {
                        sheets.forEach(s->{
                            int seconds = Integer.parseInt(s.getExplicitRemSecondsLabel().getText()) - 1;
                            if(seconds == 0) {
                                stopGame();
                            }
                            s.getExplicitRemSecondsLabel().setText(String.valueOf(seconds));
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    public void showResults() {
        HashMap<ColumnType, Integer> countMap = new HashMap<>();
        columns.forEach(cs->{
            countMap.put(cs, 0);
        });
        List<String> termsList = new ArrayList<>();

        sheets.forEach(s-> {
            columns.forEach(c->{
                String term = s.getMapTextFields().get(c).getText();
                if(term.length() > 1 && term.charAt(0) == startingLetter) {
                    countMap.put(c, countMap.get(c) + 1);
                    termsList.add(term);
                }
            });
        });


        sheets.forEach(s-> {
            columns.forEach(c->{
                String term = s.getMapTextFields().get(c).getText().toUpperCase();

                if(!validWords.contains(term) && !term.isBlank()) {
                    if(JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Ist " + term + " korrekt für Kategorie '" + c.getTitle() + "'?", "Option auswählen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE  ) == JOptionPane.YES_OPTION) {
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter("validwords.txt", true))) {
                            bw.write(term);
                            bw.newLine();
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(term.length() > 1 && term.charAt(0) == startingLetter) {
                    if(countMap.get(c) == 1) {
                        s.getPlayer().setPoints(s.getPlayer().getPoints() + 20);
                        s.getMapPointsForTerms().get(c).setText(String.valueOf(Integer.parseInt (s.getMapPointsForTerms().get(c).getText()) + 20));
                    } else if(!termsList.contains(term)) {
                        s.getPlayer().setPoints(s.getPlayer().getPoints() + 10);
                        s.getMapPointsForTerms().get(c).setText(String.valueOf(Integer.parseInt (s.getMapPointsForTerms().get(c).getText()) + 10));
                    } else if(countMap.get(c) >= 1) {
                        s.getPlayer().setPoints(s.getPlayer().getPoints() + 5);
                        s.getMapPointsForTerms().get(c).setText(String.valueOf(Integer.parseInt (s.getMapPointsForTerms().get(c).getText()) + 5));
                    }
                }
            });
        });
    }
    public void stopGame() {
        running = false;
        this.showResults();
        sheets.forEach(s->{
            s.getStartButton().setEnabled(true);
            s.getStopButton().setEnabled(false);
            //s.clear();
            s.getPointsLabel().setText(String.valueOf(s.getPlayer().getPoints()));
        });
    }
}
