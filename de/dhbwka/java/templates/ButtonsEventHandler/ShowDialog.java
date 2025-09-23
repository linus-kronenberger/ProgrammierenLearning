package de.dhbwka.java.templates.ButtonsEventHandler;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowDialog extends JFrame {
    public static void main(String[] args) {
        ShowDialog frame = new ShowDialog();
        JDialog dialog = new JDialog(frame, "hi from my dialog!!!");
        JLabel labelForDialog = new JLabel("hey from my label");
        dialog.add(labelForDialog);
        dialog.setVisible(true);
        frame.setVisible(true);
    }
}
