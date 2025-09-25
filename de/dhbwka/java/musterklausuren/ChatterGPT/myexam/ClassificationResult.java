package myexam;

/** Solution by TINF24B2 - 2025-07-16 */
public class ClassificationResult {

	private ImageDescription image;
	private ClassificationResultType type;
	
	public ClassificationResult(ImageDescription image, ClassificationResultType type) {
		super();
		this.image = image;
		this.type = type;
	}

	public ImageDescription getImage() {
		return image;
	}

	public void setImage(ImageDescription image) {
		this.image = image;
	}

	public ClassificationResultType getType() {
		return type;
	}

	public void setType(ClassificationResultType type) {
		this.type = type;
	}
	
}
