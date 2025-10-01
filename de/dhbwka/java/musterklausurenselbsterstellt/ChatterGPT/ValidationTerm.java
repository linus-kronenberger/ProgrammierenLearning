import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ValidationTerm extends JFrame implements Runnable{
    private List<ImageDescription> images;
    private ResultStore classifications;
    private int countImages;
    private JButton mainImageButton = new JButton();
    private JButton newImagesButton = new JButton("New Images");
    private List<JButton> imageButtons = new ArrayList<>();
    private Map<JButton, ImageDescription> buttonMap = new HashMap<>();
    private JPanel mainPanel;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel downPanel;
    private List<ImageDescription> randomDescs = new ArrayList<>();
    private int countDown = 10;
    private long currentTime = System.currentTimeMillis();
    private Thread t = new Thread(this);
    private boolean runs = true;

    @Override
    public void run() {
        while(true) {
            if((System.currentTimeMillis() - currentTime >= 1000) && runs == true) {
                countDown --;
                this.setTitle("Validation Term (" + countDown + " Seconds)");
                currentTime = System.currentTimeMillis();
                if(countDown == 0) {
                    runs = false;
                    showNewImages();
                }
            }
        }
    }

    public ValidationTerm(List<ImageDescription> images, ResultStore classifications, int countImages) throws ChatterException {
        this.images = images;
        this.classifications = classifications;
        this.countImages = countImages;
        if(images.size() < countImages) {
            throw new ChatterException("not enough images");
        }
        this.setTitle("Validation Term (" + countDown + " Seconds)");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        upperPanel = new JPanel();
        upperPanel.add(mainImageButton);
        
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, countImages));

        // count images Anzahl random aus images ausählen
        
        showNewImages();
        downPanel = new JPanel();
        downPanel.add(newImagesButton);

        newImagesButton.addActionListener(e -> {
            
            showNewImages();
        });

        mainPanel.add(upperPanel);
        mainPanel.add(middlePanel);
       
        mainPanel.add(downPanel);
        this.add(mainPanel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        t.start();
    }
    public void showNewImages() {
        randomDescs.clear();
        countDown = 10;
        this.setTitle("Validation Term (" + countDown + " Seconds)");
        middlePanel.removeAll();
        Collections.shuffle(images);
        
        
        for (int i = 0; i < countImages; i++) {
            randomDescs.add(images.get(i));
        }

        mainImageButton.setIcon(randomDescs.get(0).getMainImage());
        mainImageButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Select the matching image below!");
        });

        ClassificationResult result = new ClassificationResult(randomDescs.get(0), ClassificationResultType.NOT_CLASSIFIED);
        classifications.addResult(result);

        for (int i = 0; i < countImages; i++) {
            JButton button = new JButton();
            imageButtons.add(button);
            buttonMap.put(button, randomDescs.get(i));
            middlePanel.add(button);
            button.setIcon(randomDescs.get(i).getReferenceImage());
            button.addActionListener(e -> {
                JButton imageButton = (JButton) e.getSource();
                ClassificationResult result2 = null;
                if(randomDescs.get(0).getMainImage().equals(buttonMap.get(imageButton).getMainImage())) {
                    System.out.println("Korrekt");
                    result2 = new ClassificationResult(randomDescs.get(0), ClassificationResultType.CORRECT);
                    classifications.addResult(result2);

                } else {
                    System.out.println("Falsch");
                    result2 = new ClassificationResult(randomDescs.get(0), ClassificationResultType.INCORRECT);
                    classifications.addResult(result2);
                }
            });
        }
        runs = true;
    }
}
