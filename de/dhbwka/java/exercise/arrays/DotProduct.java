package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class DotProduct {
    public static void main(String[] args) {
        System.out.print("Bitte Anzahl der Elemente n angeben: ");
        Scanner eingabe = new Scanner(System.in);
        int anz = eingabe.nextInt();
        int[] arr1 = new int[anz];
        int[] arr2 = new int[anz];
        for (int i = 0; i < anz; i++) {
            System.out.print("Bitte x_" + i + " eingeben: ");
            Scanner zahlEingabe = new Scanner(System.in);
            int zahl = zahlEingabe.nextInt();
            arr1[i] = zahl;

        }

        int sum = 0;

        for (int j = 0; j < anz; j++) {
            System.out.print("Bitte y_" + j + " eingeben: ");
            Scanner zahlEingabe = new Scanner(System.in);
            int zahl = zahlEingabe.nextInt();
            arr2[j] = zahl;
            sum+=arr1[j] * arr2[j];
        }
        System.out.println("Skalarprodukt: " + sum);
    }
}
