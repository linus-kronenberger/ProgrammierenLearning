public class ClassificationResult {
    private ImageDescription image;
    private ClassificationResultType type;
    public ClassificationResult(ImageDescription image, ClassificationResultType type) {
        this.image = image;
        this.type = type;
    }
    public ImageDescription getImage() {
        return this.image;
    }
    public ClassificationResultType getType() {
        return this.type;
    }
}
