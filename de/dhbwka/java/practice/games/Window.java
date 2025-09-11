package de.dhbwka.java.practice.games;

import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame {
    private int width;
    private int height;

    public Window(int width, int height) {
        this.getContentPane().setBackground(new Color(0, 150, 0));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}