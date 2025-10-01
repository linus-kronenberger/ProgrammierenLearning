import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClassificationsTerm extends JFrame implements ClassificationsDisplay {
    private List<ImageDescription> images;
    private JPanel mainPanel = new JPanel();
    private JPanel upperPanel = new JPanel();
    private JPanel lowerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private List<JLabel> scoreLabels = new ArrayList<>();
    private List<JLabel> numbersLabels = new ArrayList<>();
    private HashMap<ImageDescription, JLabel> hashMapForScores = new HashMap<>();
    private HashMap<ImageDescription, JLabel> hashMapForNumbers = new HashMap<>();

    public ClassificationsTerm(List<ImageDescription> images) {
        this.images = images;
        this.setTitle("Classifications Term");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        upperPanel.add(new JLabel("Images to verify: " + images.size()));
        lowerPanel.setLayout(new GridLayout(1, 3));

        lowerPanel.add(new JLabel("Title"));
        lowerPanel.add(new JLabel("Score"));
        lowerPanel.add(new JLabel("Number"));

        bottomPanel.setLayout(new GridLayout(images.size(), 3));
        //lables von den einzelnen Images
        for (ImageDescription image : images) {
            bottomPanel.add(new JLabel(image.getLabel()));
            JLabel scoreLabel = new JLabel("0");
            JLabel numbersLabel = new JLabel("0");
            bottomPanel.add(scoreLabel);
            bottomPanel.add(numbersLabel);
            scoreLabels.add(scoreLabel);
            numbersLabels.add(numbersLabel);
            hashMapForScores.put(image, scoreLabel);
            hashMapForNumbers.put(image, numbersLabel);

        }

        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);
        mainPanel.add(bottomPanel);
        this.add(mainPanel);
        this.setSize(400, 400);
        this.setVisible(true);
    }
    @Override
    public void setValues(ImageDescription desc, int score, int number) {
        JLabel scoreLabel = hashMapForScores.get(desc);
        JLabel numberLabel = hashMapForNumbers.get(desc);
        scoreLabel.setText(String.valueOf(score));
        scoreLabel.repaint();
        numberLabel.setText(String.valueOf(number));
        numberLabel.repaint();
    }

}
