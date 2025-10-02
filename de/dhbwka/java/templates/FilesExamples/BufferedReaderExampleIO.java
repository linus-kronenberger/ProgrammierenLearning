package de.dhbwka.java.templates.FilesExamples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExampleIO {
    public static void main(String[] args) {
        // String[] lines = l.toArray(new String[0]);
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        File f = new File("de/dhbwka/java/templates/FilesExamples/myFile.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.ready()) {
                String line = br.readLine();
                System.out.println("Line Read: " + line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}