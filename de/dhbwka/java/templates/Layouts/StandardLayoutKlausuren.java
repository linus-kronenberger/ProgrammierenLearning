package de.dhbwka.java.templates.Layouts;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

public class StandardLayoutKlausuren extends JFrame {
    JLabel topLabel = new JLabel("Oben");
    List<JButton> buttons = new ArrayList<>();
    JLabel bottomLabel = new JLabel("Unten");

    public StandardLayoutKlausuren() {
        this.setTitle("Standard Layout Klausuren");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int gridWidth = 3;
        int gridHeight = 3;
        // drei Komponenten: 
        // oben Label
        // mitte Grid mit Buttons
        // unten Label
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        topPanel.add(topLabel);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(gridHeight, gridWidth));
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                JButton button = new JButton("0");
                buttons.add(button);
                centerPanel.add(button);
            }
        }
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(bottomLabel);
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(bottomPanel);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        StandardLayoutKlausuren layout = new StandardLayoutKlausuren();
    }

}