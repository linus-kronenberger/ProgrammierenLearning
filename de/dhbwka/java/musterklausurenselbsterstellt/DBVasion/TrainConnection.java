import java.util.ArrayList;
import java.util.List;

public class TrainConnection {
    private List<Stop> stops = new ArrayList<>();
    private String name;
    private String start;
    private String end;
    boolean regional;
    double price;

    //Zugverbindung
    public TrainConnection(String name, String start, String end, boolean regional, double price ) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.regional = regional;
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return this.name;
    }
    public List<Stop> getStops() {
        return this.stops;
    }
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}