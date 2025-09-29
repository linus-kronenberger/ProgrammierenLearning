package de.dhbwka.java.templates.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class BufferedReaderExampleIO {
    public static void main(String[] args) {
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        File f = new File("de/dhbwka/java/templates/Files/myFile.txt");
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