package de.dhbwka.java.practice.games;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class SoccerKeyController extends KeyAdapter {
    Player player;
    boolean wPressed = false;
    boolean sPressed = false;
    boolean aPressed = false;
    boolean dPressed = false;

    public SoccerKeyController(Player currPlayer) {
        this.player = currPlayer;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char ch = event.getKeyChar();
        int posX = player.playerLabel.getLocation().x;
        int posY = player.playerLabel.getLocation().y;
        System.out.println("key pressed: " + ch);
        if (ch == 'w') {
            player.playerLabel.setLocation(posX, (int) (posY - player.speed));
            System.out.println("new player animation state: " + player.animationState);
        } else if (ch == 's') {
            player.playerLabel.setLocation(posX, (int) (posY + player.speed));
            player.animationState = 'd';
            System.out.println("new player animation state: " + player.animationState);
        } else if (ch == 'd') {
            player.playerLabel.setLocation((int) (posX + player.speed), posY);
            System.out.println("new player animation state: " + player.animationState);
        } else if (ch == 'a') {
            player.playerLabel.setLocation((int) (posX - player.speed), posY);
            System.out.println("new player animation state: " + player.animationState);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        ;
    }
}
