import java.util.List;

public class ClassificationsTerm implements ClassificationsDisplay {
    private List<ImageDescription> images;
    public ClassificationsTerm(List<ImageDescription> images) {
        this.images = images;
    }
    @Override
    public void setValues(ImageDescription desc, int score, int number) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValues'");
    }

}
