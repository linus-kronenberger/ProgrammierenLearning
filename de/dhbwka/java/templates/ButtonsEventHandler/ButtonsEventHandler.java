package de.dhbwka.java.templates.ButtonsEventHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

public class ButtonsEventHandler extends JFrame {
    public static void main(String[] args) {
        ButtonsEventHandler window = new ButtonsEventHandler();
        JButton button = new JButton();
        button.setText("My Button!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button geklickt!");
            }
        });
        window.add(button);
        window.setSize(250, 250);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
