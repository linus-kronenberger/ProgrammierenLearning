package de.dhbwka.java.musterklausurenselbsterstellt.ChatterGPT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResultStore {
    private ClassificationsTerm display;
    private HashSet<ClassificationResult> classificationResults = new HashSet<>();

    public ResultStore(ClassificationsTerm display) {
        this.display = display;
    }

    public void addResult(ClassificationResult res) {
        ImageDescription desc = res.getImage();
        classificationResults.add(res);
        List<ClassificationResult> objects = classificationResults.stream()
                .filter(result -> result.getImage().equals(desc))
                .collect(Collectors.toList());
        Optional<Integer> score = objects.stream()
                .map(result2 -> result2.getType().getScore())
                .reduce(Integer::sum);
        int realScore = score.orElse(0);

        int number = (int) classificationResults.stream()
                .filter(result -> result.getImage().equals(desc))
                .count();

        display.setValues(desc, realScore, number);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("classifications.txt", true))) {

            bw.write(desc.getLabel() + ";" + res.getType().getLabel());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
