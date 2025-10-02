package de.dhbwka.java.templates.OptionPane;

import javax.swing.JOptionPane;

public class okPane {
    public okPane() {
        JOptionPane.showMessageDialog(
                null, // Beispiel: mainPanel
                "Operation erfolgreich!",// Nachricht
                "Information",  // Titel
                JOptionPane.INFORMATION_MESSAGE // Typ: Information (blaues Icon)
        );
    }
}
