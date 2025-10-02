package de.dhbwka.java.exercise.operators;

public class Round {
    public static void main(String[] args) {
        double pi = 3.1415926;
        double e = 2.7182818;

        if (pi < 0) {
            int piInt = (int) (pi - 0.5);
            System.out.println("Pi ganzzahlig: " + piInt);
        } else {
            int piInt = (int) (pi + 0.5);
            System.out.println("Pi ganzzahlig: " + piInt);
        }

        if (e < 0) {
            int eInt = (int) (e - 0.5);
            System.out.println("e ganzzahlig: " + eInt);
        } else {
            int eInt = (int) (e + 0.5);
            System.out.println("e ganzzahlig: " + eInt);
        }
    }
}
