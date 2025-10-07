package de.dhbwka.java.musterklausurenselbsterstellt.KloaCov2.bereitgestellte_Dateien;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RKITerminal extends JFrame {
    private HashMap<SewagePlant, VariantMeasurement> measurements = new HashMap<>();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel upperPanel = new JPanel();
    private JPanel centerPanel = new JPanel(new GridLayout(5, 2));
    private JPanel downPanel = new JPanel();
    private JLabel populationFont = new JLabel("Population measured: ");
    private JLabel populationCountLabel = new JLabel("0");
    private BWMap bwMap = new BWMap();
    public HashMap<SewagePlant, VariantMeasurement> getMeasurements() {
        return measurements;
    }

    private void calculateCenter() {
        centerPanel.removeAll();
        for (int i = 0; i < Variant.values().length; i++) {
            JLabel variantLabel = new JLabel(Variant.values()[i].getDesignation());
            centerPanel.add(variantLabel);

            int sum = 0;
            for (VariantMeasurement varM : measurements.values()) {
                // Verhindere NullPointerException durch Überprüfung von null und Setzen eines Default-Werts
                Integer mes = varM.getMeasurements().get(Variant.values()[i]);
                sum += (mes != null) ? mes : 0;
            }

            double average = (measurements.isEmpty()) ? 0 : (double) sum / measurements.size();
            JLabel variantNumberLabel = new JLabel(String.format("%.2f ppp", average));
            centerPanel.add(variantNumberLabel);
        }
    }

    public RKITerminal() {
        this.setTitle("RKI(Overview BW)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        upperPanel.add(populationFont);
        upperPanel.add(populationCountLabel);

        calculateCenter();

        downPanel.add(bwMap);
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(downPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setSize(500, 500);
        this.setVisible(true);
    }


    public void setMeasurements(HashMap<SewagePlant, VariantMeasurement> measurements) {
        this.measurements = measurements;
    }

    public void countPopulation() {
        int count = 0;
        for(SewagePlant sp : measurements.keySet()) {
            count += sp.getPopulation();
        }

        populationCountLabel.setText(String.valueOf(count));
    }

    public void receiveMeasurement(VariantMeasurement meas) {
        measurements.put(meas.getPlant(), meas);
        int populationCount = Integer.parseInt(populationCountLabel.getText());
        calculateCenter();
        //TODO Gesamtbevölkerung aktualisieren
        this.countPopulation();
        bwMap.setMapItem(meas.getPlant(), meas.getTotalValue());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("de/dhbwka/java/musterklausurenselbsterstellt/KloaCov2/ppp-logs.txt", true))) {
            String dataStructureValues = "";
            for(Variant var : meas.getMeasurements().keySet()) {
                dataStructureValues += var.toString() + "=" + String.valueOf(meas.getMeasurements().get(var));
                if(var != meas.getMeasurements().keySet().toArray()[meas.getMeasurements().keySet().size() - 1]) {
                    dataStructureValues += ", ";
                }
            }
            bw.write(meas.getPlant().getName() + " (" + meas.getDate().toString() + "):" + " {" + dataStructureValues +"}");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
