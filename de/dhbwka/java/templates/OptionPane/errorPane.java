package OptionPane;

import javax.swing.JOptionPane;

public class errorPane {
    public errorPane() {
        JOptionPane.showMessageDialog(
            null, // Beispiel: mainPanel
            "Operation erfolgreich!",// Nachricht
            "Information",  // Titel
            JOptionPane.ERROR_MESSAGE // Typ: Information (blaues Icon)
        );
    }
}
