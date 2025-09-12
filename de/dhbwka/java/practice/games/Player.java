package de.dhbwka.java.practice.games;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player {
    private int posX;
    private int posY;
    public float speed = 50.0f;
    public char animationState = 'i';
    public char prevAnimationState = 'i';
    private long animationStartPoint;
    private int sizeX = 200;
    private int sizeY = 200;
    public JLabel playerLabel;

    public ImageIcon playerSprite;

    public Player (int posX, int posY) {
        this.playerLabel = new JLabel();
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
            this.playerSprite = new ImageIcon("de/dhbwka/java/practice/games/sprites/playerIDLE.PNG");
            Image image = playerSprite.getImage();
            Image newImg = image.getScaledInstance(sizeX, sizeY, java.awt.Image.SCALE_SMOOTH);
            this.playerSprite.setImage(newImg);
            this.playerLabel.setIcon(this.playerSprite);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        
    }

    public void update() {
        //System.out.println("Player Updating...");
        this.checkCollisions();
        this.animate();
    }

    public void animate() {
        //System.out.println("Player animating...");
        switch (animationState) {
            case 'i':
                if(prevAnimationState != 'i') {
                    /* this.playerSprite = new ImageIcon("de/dhbwka/java/practice/games/sprites/playerIDLE.PNG");
                    Image image = playerSprite.getImage();
                    Image newImg = image.getScaledInstance(sizeX, sizeY, java.awt.Image.SCALE_SMOOTH);
                    this.playerSprite = new ImageIcon(newImg);
                    this.playerLabel = new JLabel(this.playerSprite); */
                    prevAnimationState = 'd';
                }
            case 'r':
                break;
            case 'l':
                break;
            case 'u':
                break;
            case 'd':
                if(prevAnimationState != 'd') {
                    animationStartPoint = System.currentTimeMillis();
                    System.out.println("running animation started at: " + animationStartPoint);

                    /* this.playerSprite = new ImageIcon("de/dhbwka/java/practice/games/sprites/playerIDLE.PNG");
                    Image image = playerSprite.getImage();
                    Image newImg = image.getScaledInstance(sizeX, sizeY, java.awt.Image.SCALE_SMOOTH);
                    this.playerSprite.setImage(newImg);
                    this.playerLabel.setIcon(this.playerSprite); */
                    prevAnimationState = 'd';
                }
                if(System.currentTimeMillis() - animationStartPoint >= 500) {
                    System.out.println("toggle running animation after 0.5 seconds: ");
                    animationStartPoint = System.currentTimeMillis();
                }
        }
       
    }

    public void checkCollisions() {
        //System.out.println("Player checking collisions...");
        ;
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
