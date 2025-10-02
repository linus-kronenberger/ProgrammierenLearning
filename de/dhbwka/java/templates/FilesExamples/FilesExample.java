package de.dhbwka.java.templates.FilesExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FilesExample {
    public static void main(String[] args) {
        try {
            Path p = Paths.get("de/dhbwka/java/templates/FilesExamples/myFile.txt");
            if (Files.exists(p)) {
                List<String> allLines = Files.readAllLines(p);
                System.out.println(allLines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
