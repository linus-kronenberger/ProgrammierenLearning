public class Stop {
    //repräsentiert Halt
    String name;
    Time arrival;
    int stayPeriod;
    int stage;
    public Stop(String name, Time arrival, int stayPeriod, int stage) {
        this.name = name;
        this.arrival = arrival;
        this.stayPeriod = stayPeriod;
        this.stage = stage;
    }
    public Time getArrival() {
        return this.arrival;
    }
    public void setArrrival(Time arrival) {
        this.arrival = arrival;
    }
    public int getStayPeriod() {
        return this.stayPeriod;
    }
    public String getName() {
        return this.name;
    }
    public int getStage() {
        return this.stage;
    }  
}
