package de.dhbwka.java.templates.Files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DateiSchreiber {
    public static void main(String[] args) {
        String dateiPfad = "de/dhbwka/java/templates/Files/myFile.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiPfad))) {
            bw.write("Erste Zeile Text");
            bw.newLine();
            bw.write("Zweite Zeile Text");
            bw.newLine();
            bw.write("Noch eine Zeile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
