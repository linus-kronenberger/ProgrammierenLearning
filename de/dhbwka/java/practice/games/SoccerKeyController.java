package de.dhbwka.java.practice.games;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SoccerKeyController extends KeyAdapter {
   @Override
   public void keyPressed(KeyEvent event) {
    char ch = event.getKeyChar();
    System.out.println(ch);
   }
}
