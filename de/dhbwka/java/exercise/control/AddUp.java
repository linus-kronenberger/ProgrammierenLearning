package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class AddUp {
    public static void main(String[] args) {
        boolean abbruch = false;
        int summe = 0;
        while (abbruch == false) {
            System.out.print("Zahl eingeben (<0 für Abbruch): ");
            Scanner eingabe = new Scanner(System.in);
            int value = eingabe.nextInt();
            if (value < 0) {
                abbruch = true;
            } else {
                summe += value;
            }
        }
        System.out.println(summe);
    }
}
