package de.dhbwka.java.exercise.control;

public class Babylon {
    public static void main(String[] args) {
        double xalt = 10.0;
        for (int i = 1; i <= 5; i++) {
            int a = 36;
            
            double xneu = (double)((xalt + ((double)(a) / (xalt))) / 2);
            xalt = xneu;
            System.out.println(xneu);
        }
    }
}
