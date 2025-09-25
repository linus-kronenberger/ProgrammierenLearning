package myexam;

import javax.swing.ImageIcon;

/** Solution by TINF24B2 - 2025-07-16 */
public class ImageDescription {

	private String label;
	private ImageIcon mainImage;
	private ImageIcon referenceImage;
	
	public ImageDescription(String label, ImageIcon mainImage, ImageIcon referenceImage) {
		super();
		this.label = label;
		this.mainImage = mainImage;
		this.referenceImage = referenceImage;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ImageIcon getMainImage() {
		return mainImage;
	}

	public void setMainImage(ImageIcon mainImage) {
		this.mainImage = mainImage;
	}

	public ImageIcon getReferenceImage() {
		return referenceImage;
	}

	public void setReferenceImage(ImageIcon referenceImage) {
		this.referenceImage = referenceImage;
	}
	
}
