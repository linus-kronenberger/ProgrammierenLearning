package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class Norm {
    public static void main(String[] args) {
        System.out.print("Bitte Anzahl der Elemente n eingeben: ");
        Scanner eingabe = new Scanner(System.in);
        int anzahl = eingabe.nextInt();
        double[] arr = new double[anzahl]; 
        for (int i = 0; i < anzahl; i++) {
            System.out.print("Bitte x_" + i + " eingeben: ");
            Scanner x = new Scanner(System.in);
            arr[i] = Math.pow(x.nextDouble(), 2);
        }
        double sum = 0.0;
        for(int j=0; j<arr.length; j++) {
            sum += arr[j];
        }
        double res = Math.pow(sum, 0.5);
        System.out.println("Der Betrag ist: " + res);
    }
}
