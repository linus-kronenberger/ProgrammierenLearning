package de.dhbwka.java.templates.Layouts;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample extends JFrame{
    private JLabel upperLabel = new JLabel("NORTH");
    private JLabel centerLabel = new JLabel("CENTER");

    public JLabel getSouthLabel() {
        return southLabel;
    }

    public void setSouthLabel(JLabel southLabel) {
        this.southLabel = southLabel;
    }

    public JLabel getCenterLabel() {
        return centerLabel;
    }

    public void setCenterLabel(JLabel centerLabel) {
        this.centerLabel = centerLabel;
    }

    public JLabel getUpperLabel() {
        return upperLabel;
    }

    public void setUpperLabel(JLabel upperLabel) {
        this.upperLabel = upperLabel;
    }

    private JLabel southLabel = new JLabel("SOUTH");


    public static void main(String[] args) {
        BorderLayoutExample frame = new BorderLayoutExample();

        frame.add(frame.getUpperLabel(), BorderLayout.NORTH);
        frame.add(frame.getCenterLabel(), BorderLayout.CENTER);
        frame.add(frame.getSouthLabel(), BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
