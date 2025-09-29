import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class RoomSetting {
    private int width;
    private int height;
    private int countPollutants;
    private List<Point> pollutants = new ArrayList<>();

    public RoomSetting(int width, int height,  int countPollutants) throws AHAException {
        this.width = width;
        this.height = height;
        this.countPollutants = countPollutants;
        if(width * height < countPollutants ) {
            throw new AHAException("Insufficient singers");
        }
        List<Integer> randomXValues = new ArrayList<>();
       
        for (int i = 0; i < width; i++) {
            randomXValues.add(i);
        }

        Collections.shuffle(randomXValues);

        List<Integer> randomYValues = new ArrayList<>();

        for (int j = 0; j < height; j++) {
            randomYValues.add(j);
        }
        Collections.shuffle(randomYValues);

        for (int k= 0; k < countPollutants; k++) {
            Point randomPoint = new Point(randomXValues.get(k), randomYValues.get(k));
            pollutants.add(randomPoint);
        }

    }
    public List<Point> getPollutants() {
        return this.pollutants;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
}
