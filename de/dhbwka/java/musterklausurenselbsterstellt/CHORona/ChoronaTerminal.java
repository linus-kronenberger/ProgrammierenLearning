import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChoronaTerminal extends JFrame implements Runnable {
    private Variant variant;
    private Room room;
    private JLabel stepsLabel = new JLabel("Steps: 0");
    private JButton stepButton = new JButton("Step");
    private JButton playButton = new JButton("Play");
    private JButton saveButton = new JButton("Save");
    private List<CellButton> cellButtons = new ArrayList<>();
    private long timerTime = 0;


    @Override
    public void run() {
        boolean runs = true;
        int counter = 0;
        playButton.setEnabled(false);
        stepButton.setEnabled(false);
        while(runs) {
            if(System.currentTimeMillis() - timerTime > 500) {
                counter ++;
                room.step();

                for(Point point : room.getSetting().getPollutants()) {
                    int x = point.getX();
                    int y = point.getY();
                    cellButtons.get(y * room.getSetting().getWidth() + x).setDose(room.getDose(x, y));
                    Chorona.updateButtonForDose(cellButtons.get(y * room.getSetting().getWidth() + x), room.getDose(x, y));
                }

                stepsLabel.setText("Steps: " + room.getSteps());
                stepsLabel.repaint();
                if(counter == 20) {
                    runs = false;
                    playButton.setEnabled(true);
                    stepButton.setEnabled(true);
                }
                timerTime = System.currentTimeMillis();
            }
        }
    }

    public ChoronaTerminal(Variant variant, Room room){
        this.variant = variant;
        this.room = room;
        this.setTitle("Chorona - " + variant.getLabel() + " (" + variant.getDesignation() + ")");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel upperPanel = new JPanel();
        upperPanel.add(stepsLabel);
        mainPanel.add(upperPanel);
        JPanel middlePanel = new JPanel();
        int width = room.getSetting().getWidth();
        int height = room.getSetting().getHeight();
        middlePanel.setLayout(new GridLayout(width, height));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                CellButton button = new CellButton();
                Chorona.updateButtonForDose(button, 0);
                button.addActionListener(new ActionListener() {
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CellButton sourceButton = (CellButton) e.getSource();
                        sourceButton.setPolluter(true);
                    }
                });
                cellButtons.add(button);
                middlePanel.add(button);
            }
        }
        mainPanel.add(middlePanel);
        JPanel downPanel = new JPanel();
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.step();
                stepsLabel.setText("Steps: " + room.getSteps());
                int index = 0;
                for(Point point : room.getSetting().getPollutants()) {
                    int x = point.getX();
                    int y = point.getY();
                    cellButtons.get(y * room.getSetting().getWidth() + x).setDose(room.getDose(x, y));
                    Chorona.updateButtonForDose(cellButtons.get(y * room.getSetting().getWidth() + x), room.getDose(x, y));
                    index ++;
                }
            }
        });
        downPanel.add(stepButton);
        downPanel.add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread1 = new Thread(ChoronaTerminal.this);
                timerTime = System.currentTimeMillis();
                thread1.start();
            }
        });
        downPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = variant.getLabel() + "-"+variant.getDesignation()+"-"+room.getSteps()+".txt";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
                    for (int i = 0; i < room.getSetting().getHeight(); i++) {
                        for (int j = 0; j < room.getSetting().getWidth(); j++) {
                            String message = String.valueOf(j) + ";" + String.valueOf(i) + ";" + cellButtons.get(((i)*room.getSetting().getWidth()) + j).getDose() + "\n";
                            bw.write(message);
                        }
                    }
                    bw.close();
                    JOptionPane.showMessageDialog(
                    mainPanel,
                    fileName + " saved successfully.",
                    "Meldung",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(
                    mainPanel,
                    fileName + " not saved successfully",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    error.printStackTrace();
                }
            }
        });
        mainPanel.add(downPanel);
        this.add(mainPanel);
        this.setSize(500,500);
        this.setVisible(true);
    }
}
