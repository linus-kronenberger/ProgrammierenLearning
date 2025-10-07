package de.dhbwka.java.musterklausurenselbsterstellt.KloaCov2.bereitgestellte_Dateien;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SewageTerminal extends JFrame {
    private SewagePlant sp;
    private RKITerminal rki;
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private List<JTextField> textFields = new ArrayList<>();
    private JButton sendButton = new JButton("Send");
    private HashMap<Variant, JTextField> textFieldMap = new HashMap<>();
    private HashMap<Variant, Integer> mapMeasurements = new HashMap<>();

    public SewageTerminal(SewagePlant sp, RKITerminal rki) {
        this.sp = sp;
        this.rki = rki;
        this.setTitle(sp.getName() + " (" + sp.getPopulation() +")");
        upperPanel.setLayout(new GridLayout(5, 2));
        for (int i = 0; i < Variant.values().length; i++) {
            upperPanel.add(new JLabel(Variant.values()[i].getDesignation()));
            JTextField textField = new JTextField();
            textFields.add(textField);
            upperPanel.add(textField);
            textFieldMap.put(Variant.values()[i], textField);
        }

        mainPanel.add(upperPanel, BorderLayout.NORTH);

        lowerPanel.add(sendButton);
        sendButton.addActionListener(e-> {
            Variant currentVariant = Variant.ALPHA;
            String currentText = "";
            try {
                for (int i = 0; i < textFieldMap.keySet().size(); i++) {
                    if(!textFieldMap.get(Variant.values()[i]).getText().isBlank()) {
                        currentVariant = Variant.values()[i];
                        mapMeasurements.put(Variant.values()[i], Integer.parseInt(textFieldMap.get(Variant.values()[i]).getText()));
                        currentText = textFieldMap.get(Variant.values()[i]).getText();
                    }

                }
                Date currentDate = new Date();
                sendButton.setEnabled(false);
                for (JTextField textField : textFields) {
                    textField.setEnabled(false);
                }
                VariantMeasurement measurement = new VariantMeasurement(sp, currentDate, mapMeasurements);
                rki.receiveMeasurement(measurement);
                Runnable runnable = () -> {
                    try {
                        Thread.sleep(10000);
                        for (JTextField textField : textFields) {
                            textField.setText("");
                            sendButton.setEnabled(true);
                            textField.setEnabled(true);
                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();

            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid input for " + currentVariant.getDesignation() + ": " + currentText);
                return;
            }

        });
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
