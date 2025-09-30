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

public class ValidationTerm extends JFrame {
    private List<ImageDescription> images;
    private ResultStore classifications;
    private int countImages;
    private JButton mainImageButton = new JButton();
    private JButton newImagesButton = new JButton("New Images");
    private List<JButton> imageButtons = new ArrayList<>();
    private Map buttonMap = new HashMap<>();
    public ValidationTerm(List<ImageDescription> images, ResultStore classifications, int countImages) throws ChatterException{
        this.images = images;
        this.classifications = classifications;
        this.countImages = countImages;
        if(images.size() < countImages) {
            throw new ChatterException("not enough images");
        }
        this.setTitle("Validation Term");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel upperPanel = new JPanel();
        upperPanel.add(mainImageButton);
        
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, countImages));

        // count images Anzahl random aus images ausählen
        
        Collections.shuffle(images);
        List<ImageDescription> randomDescs = new ArrayList<>();
        
        for (int i = 0; i < countImages + 1; i++) {
            randomDescs.add(images.get(i));
        }

        mainImageButton.setIcon(randomDescs.get(0).getReferenceImage());
        mainImageButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Select the matching image below!");
        });

        int index = 1;
        for (int i = 0; i < countImages; i++) {
            JButton button = new JButton();
            imageButtons.add(button);
            middlePanel.add(button);
            button.setIcon(randomDescs.get(index).getMainImage());
            index ++;
            button.addActionListener(e -> {
                JButton imageButton = (JButton) e.getSource();
                if(mainImageButton.getIcon().equals(imageButton.getIcon())) {
                    System.out.println("Korrekt");
                } else {
                    System.out.println("Falsch");
                }
            });
        }
         JPanel downPanel = new JPanel();
         downPanel.add(newImagesButton);

        mainPanel.add(upperPanel);
        mainPanel.add(middlePanel);
       
        mainPanel.add(downPanel);
        this.add(mainPanel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
