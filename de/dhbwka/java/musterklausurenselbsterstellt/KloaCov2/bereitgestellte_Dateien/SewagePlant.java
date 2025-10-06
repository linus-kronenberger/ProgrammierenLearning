public class SewagePlant implements MapItem{
    private String name;
    private int population; //anzahl personen
    private double longitude;
    private double latitude;

    public SewagePlant(String name, int population, double longitude, double latitude) {
        this.name = name;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
        if(!(longitude >= 7 && longitude <= 10) || !(latitude <= 50 && latitude >= 47)) {
            throw new KloaCov2Exception("Out of bounds: " + String.valueOf(longitude) + " " + String.valueOf(latitude));
        }
    }

    @Override
    public String getTitle() {
        return name + " (" + population + " people";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
    // Klärwerk Repräsentation
    // Werden später in Karte angezeigt

}
