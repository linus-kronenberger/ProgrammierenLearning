import javax.swing.ImageIcon;

public class ImageDescription {
    private String label;
    private ImageIcon mainImage;
    private ImageIcon referenceImage;
    public ImageDescription(String label, ImageIcon mainImage, ImageIcon referenceImage) {
        this.label = label;
        this.mainImage = mainImage;
        this.referenceImage = referenceImage;
    }
    public ImageIcon getMainImage() {
        return this.mainImage;
    }
    public ImageIcon getReferenceImage() {
        return this.referenceImage;
    }
}
