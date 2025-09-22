package de.dhbwka.java.templates.Layouts;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Grid extends JFrame {
    // jpanel benutzen um mehrere Zeilen zu initialisieren
    public Grid() {
        this.setLayout(new GridLayout(3, 4));
    }
}

