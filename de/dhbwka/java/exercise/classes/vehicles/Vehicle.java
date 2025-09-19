package de.dhbwka.java.exercise.classes.vehicles;

public class Vehicle {
    private int countWheels = 0;
    private double vMax = 0;
    private int position = 0;
    private double speed; // km/h

    public Vehicle(double speed, int countWheels, double vMax) {
        this.countWheels = countWheels;
        this.vMax = vMax;
        this.speed = speed;
    }

    public void setSpeed(double speed) {
        if (speed > vMax) {
            this.speed = vMax;
        } else {
            this.speed = speed;
        }
    }

    public void drive(double minutes) {
        double hours = (double) minutes / 60;
        position += hours * speed;
    }

    public String toString() {
        String information = "countWheels: " + countWheels + " vMax: " + vMax + " position: " + position + " speed: " + speed;
        return information;
    }
}
