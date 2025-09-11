package de.dhbwka.java.practice.games;

import javax.swing.JPanel;

public abstract class GameEngine {
    public Window window;
    public JPanel mainPanel;
    public JPanel spritePanel;
    public int currentFPS;
    public int framesCounter = 0;
    
    public boolean init() {
         this.window = new Window(1920/2, 1080/2);
         /* this.mainPanel = new JPanel();
         this.spritePanel = new JPanel(); */
         return window != null;
    }

    public void main() {
        //calculate fps

    }

    public void rerender() {
        this.window.repaint();
    }
}
