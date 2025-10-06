package de.dhbwka.java.templates.OptionPane;

import javax.swing.*;

public class YES_NO_PANE extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        int auswahl = JOptionPane.showConfirmDialog(
                frame,
                "Möchten Sie wirklich fortfahren?",
                "Bestätigung",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        switch (auswahl) {
            case JOptionPane.YES_OPTION:
                System.out.println("Benutzer hat \"Ja\" gewählt.");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("Benutzer hat \"Nein\" gewählt.");
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("Benutzer hat \"Abbrechen\" gewählt.");
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("Dialog wurde geschlossen ohne Auswahl.");
                break;
        }
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
