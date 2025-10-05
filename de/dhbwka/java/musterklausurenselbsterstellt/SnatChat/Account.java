package de.dhbwka.java.musterklausurenselbsterstellt.SnatChat;

import java.awt.*;

public class Account {
    private String name;
    private State state;
    private Color color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Account(String name) {
        this.name = name;
        int rgb1 = (int) (Math.random() * 200);
        int rgb2 = (int) (Math.random() * 200);
        int rgb3 = (int) (Math.random() * 200);
        this.color = new Color(rgb1, rgb2, rgb3);
        state = State.AVAILABLE;
    }
}
