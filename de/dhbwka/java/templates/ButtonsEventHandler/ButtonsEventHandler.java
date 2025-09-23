package de.dhbwka.java.templates.ButtonsEventHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

public class ButtonsEventHandler extends JFrame {
    public String labelString = "Hello from outer class";
    public ButtonsEventHandler() {
        this.labelString = labelString;
    }
    public static void main(String[] args) {
        ButtonsEventHandler window = new ButtonsEventHandler();
        JButton button = new JButton();
        button.setText("My Button!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // auf äußeres this zugreifen:
                //System.out.println(ButtonsEventHandler.this.labelString); -> möglich wenn in der Klasse und es nicht um Instanz geht
                //JButton button =  (JButton) e.getSource(); -> source bekommen
                System.out.println("Button geklickt!");
            }
        });
        window.add(button);
        window.setSize(250, 250);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
