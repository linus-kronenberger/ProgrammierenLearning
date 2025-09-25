package myexam;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Solution by TINF24B2 - 2025-07-16 */
public class ClassificationsTerm implements ClassificationsDisplay {

	List<ImageDescription> imageDescs;
	Map<ImageDescription, JLabel> scores = new Hashtable<>();
	Map<ImageDescription, JLabel> counts = new Hashtable<>();
	
	public ClassificationsTerm(List<ImageDescription> imageDescs) {
		this.imageDescs = imageDescs;
		//compare to -1, +1
		Collections.sort(imageDescs, (a,b) -> a.getLabel().compareTo(b.getLabel()) );
        
		JFrame f = new JFrame("Classifications Term");
		JLabel info = new JLabel("Images to Verify: " + imageDescs.size());
		f.add(info, BorderLayout.NORTH);
		
		JPanel infoTable = new JPanel();
		infoTable.setLayout(new java.awt.GridLayout(imageDescs.size()+1, 3, 5, 5));
		infoTable.add(new JLabel("TITLE"));
		infoTable.add(new JLabel("SCORE"));
		infoTable.add(new JLabel("COUNT"));
		for (ImageDescription id : imageDescs) {
			JLabel scoreLabel = new JLabel("0");
			JLabel countLabel = new JLabel("0");
			scores.put(id, scoreLabel);
			counts.put(id, countLabel);
			infoTable.add(new JLabel(id.getLabel()));
			infoTable.add(scoreLabel);
			infoTable.add(countLabel);
		}
		f.add(infoTable, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 600);
		f.setVisible(true);
	}

	@Override
	public void setValues(ImageDescription desc, int score, int number) {
		scores.get(desc).setText("" + score);
		counts.get(desc).setText("" + number);
	}

}
