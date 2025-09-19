package de.dhbwka.java.exercise.classes.vehicles;

public class Ambulance extends Car{
    private boolean blaulicht;

    public Ambulance(double speed, boolean blaulicht) {
        super(speed);
        this.blaulicht = blaulicht;
    }

    public void enableBlaulicht() {
        this.blaulicht = true;
    }

    public void disableBlaulicht() {
        this.blaulicht = false;
    }
}
