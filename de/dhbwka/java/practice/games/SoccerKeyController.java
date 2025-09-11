package de.dhbwka.java.practice.games;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class SoccerKeyController implements KeyEventDispatcher {
    public boolean dispatchKeyEvent(KeyEvent e) {
        int kc = e.getKeyCode();
        if(e.getID() == KeyEvent.KEY_PRESSED) {
            ;
        }
        return true;
    }
}
