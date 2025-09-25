package myexam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** Solution by TINF24B2 - 2025-07-16 */
public class ValidationTerm {

	private List<ImageDescription> imageDescs;
	private ResultStore classifications;
	private int maxImages;
	
	private JFrame frame = new JFrame("Validation Term");	
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	private ImageDescription currentImage;
	private List<ImageDescription> displayedImages;
	private static final Random rnd = new Random();
	
	private int secondsLeft = 10;
	
	public ValidationTerm(List<ImageDescription> imageDescs, 
			ResultStore classifications, int maxImages) throws ChatterException {
		this.imageDescs = imageDescs;
		this.classifications = classifications;
		this.maxImages = maxImages;
		
		if (imageDescs.size() < maxImages) {
			throw new ChatterException("Too few images!");
		}
				
		displayNewImages();
		
		JButton newImagesButton = new JButton("New Images");
		bottomPanel.add(newImagesButton);
		newImagesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				classifications.addResult(new ClassificationResult(currentImage, 
                        ClassificationResultType.NOT_CLASSFIED));
				displayNewImages();
			}
		});
		
		frame.setLayout(new java.awt.BorderLayout(10, 10));
		frame.add(topPanel, java.awt.BorderLayout.NORTH);
		frame.add(centerPanel, java.awt.BorderLayout.CENTER);
		frame.add(bottomPanel, java.awt.BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setVisible(true);
		startThread();
	}

	public void startThread() {
		// Just a single ever-running Thread
		// Will be "reseted" by giving new "secondsLeft" 
		new Thread(() -> {
			while(true) {
				try {
					frame.setTitle("Validation Term " + "(" + secondsLeft-- + " Seconds)");
					Thread.sleep(1000);
					if (secondsLeft<0) 
						displayNewImages();					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void displayNewImages() {
		topPanel.removeAll();
		centerPanel.removeAll();
		currentImage = imageDescs.get(rnd.nextInt(imageDescs.size()));
		Set<ImageDescription> randomSet = new HashSet<>();
		randomSet.add(currentImage);
		do {
			randomSet.add(imageDescs.get(rnd.nextInt(imageDescs.size())));
		} while (randomSet.size() < maxImages);
		
		JButton currentImageButton = new JButton();
		currentImageButton.setIcon(currentImage.getReferenceImage());
		topPanel.add(currentImageButton);
		currentImageButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Select the matching image below!");
			}
		});
		
		List<ImageDescription> imagesList = new ArrayList<>(randomSet);
		Collections.shuffle(imagesList);
		
		for(ImageDescription desc : imagesList) {
			JButton imageButton = new JButton();
			imageButton.setIcon(desc.getMainImage());
			centerPanel.add(imageButton);
			imageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {			
					classifications.addResult(new ClassificationResult(desc, 
						desc.equals(currentImage) ? 
						ClassificationResultType.CORRECT : 
						ClassificationResultType.INCORRECT) );
					displayNewImages();
				}
			});
		}
		secondsLeft = 10;
		frame.validate();
	}
	
	public List<ImageDescription> getImageDescs() {
		return imageDescs;
	}

	public void setImageDescs(List<ImageDescription> imageDescs) {
		this.imageDescs = imageDescs;
	}

	public ResultStore getClassifications() {
		return classifications;
	}

	public void setClassifications(ResultStore classifications) {
		this.classifications = classifications;
	}

	public int getMaxImages() {
		return maxImages;
	}

	public void setMaxImages(int maxImages) {
		this.maxImages = maxImages;
	}

}
