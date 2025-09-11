package de.dhbwka.java.practice.games;

import javax.swing.JLabel;

public class Soccer extends GameEngine {
    public void startSoccer() {
        boolean engineStatus = this.init();
        System.out.println("Game Engine initialize status: " + engineStatus);
    }

    public static void main(String[] args) {
        Soccer soccerGame = new Soccer();
        SoccerKeyController keyController = new SoccerKeyController();

        soccerGame.startSoccer();
        int posX = 0;
        int posY = 0;
        Player player = new Player(posX, posY);
        JLabel playerLabel = new JLabel(player.playerSprite);
        JLabel gameLabel = new JLabel("SOCCER");
        playerLabel.setBounds(10, 10, 100, 100);
        gameLabel.setLocation(100, 100);
        soccerGame.window.add(gameLabel);
        soccerGame.window.add(playerLabel);
        soccerGame.window.setVisible(true);
        boolean gamePlaying = true;
        while (gamePlaying) {
            // main cycle
            ;
        }
    }
}
