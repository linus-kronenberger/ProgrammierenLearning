package de.dhbwka.java.exercise.control;

public class TemperatureTable {
    public static void main(String[] args) {
        System.out.printf("Fahrenheit%5s|%sCelsius", " ", " ");
        System.out.println();
        System.out.printf("%s+%s", "---------------", "---------------");
        System.out.println();
        for (int f = 0; f <= 300; f += 20) {

            double c = ((double) (5) / 9 * (f - 32) * 10);
            c = (double) Math.round(c) / 10;


            if (String.valueOf(f).length() < 2) {
                System.out.printf("%d%14s|%12s%f", f, " ", " ", c);
            } else if (String.valueOf(f).length() < 3) {
                System.out.printf("%d%13s|%12s%f", f, " ", " ", c);
            } else {
                System.out.printf("%d%12s|%12s%f", f, " ", " ", c);
            }
            System.out.println();
        }
    }
}
