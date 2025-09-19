package de.dhbwka.java.practice.games;

import javax.swing.JLabel;

public class Soccer extends GameEngine implements Runnable {
    Soccer soccerGame;

    public Soccer() {
        
    }

    public void startSoccer() {
        boolean engineStatus = this.init();
        System.out.println("Game Engine initialize status: " + engineStatus);
    }

    @Override
    public void run() {
        
        soccerGame.startSoccer();
        int posX = 0;
        int posY = 0;

        Player player = new Player(posX, posY);

        JLabel gameLabel = new JLabel("LOADING ...");
        gameLabel.setLocation(soccerGame.window.getWidth() / 2, soccerGame.window.getHeight() / 2);
        soccerGame.window.addKeyListener(new SoccerKeyController(player));
        soccerGame.window.add(gameLabel);
        soccerGame.window.add(player.playerLabel);
        soccerGame.window.setVisible(true);
        boolean gamePlaying = true;
        player.start();
        while (gamePlaying) {
            soccerGame.main();
        } 
    }

    public static void main(String[] args) {
        //soccerGame = new Soccer();
        
    }
}
