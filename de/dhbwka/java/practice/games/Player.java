package de.dhbwka.java.practice.games;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class Player {
    private int posX;
    private int posY;
    public ImageIcon playerSprite;

    public Player (int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        URL imageURL = getClass().getResource("sprites/Foto.png");
        if(imageURL == null) {
            System.out.println("Sprite Bild von Player nicht gefunden.");
        } else {
            System.out.println("Player Sprite Image erfolgreich importiert");
        }
        
        try {
            this.playerSprite = new ImageIcon("de/dhbwka/java/practice/games/sprites/Foto.jpg");
            Image image = playerSprite.getImage();
            Image newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            this.playerSprite = new ImageIcon(newImg);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        
    }

    public void keyboardMovement(int keyCode) {
        System.out.println("Key Code: " + keyCode);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }
}
