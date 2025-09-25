package myexam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Solution by TINF24B2 - 2025-07-16 */
public class ResultStore {

	private ClassificationsDisplay display;
	private List<ClassificationResult> results = new ArrayList<>();
	
	private Map<ImageDescription, Integer> scores = new HashMap<>();
	private Map<ImageDescription, Integer> counts = new HashMap<>();
	
	public ResultStore(ClassificationsDisplay display) {
		this.display = display;
	}

	public void addResult(ClassificationResult result) {
		ImageDescription image = result.getImage();
		results.add(result);
		scores.put(image, result.getType().getScore() + scores.getOrDefault(image, 0)); 
		counts.put(image, 1 + counts.getOrDefault(image, 0)); 
		display.setValues(image, scores.get(image), counts.get(image));
		log(result);
	}
	
	private void log(ClassificationResult result) {
		Path p = Path.of("classifications.txt");
		try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8, 
				StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
			bw.write(result.getImage().getLabel() + ";" + result.getType().getLabel());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
