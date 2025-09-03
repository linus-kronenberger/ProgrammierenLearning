package de.dhbwka.java.exercise.methods;

import java.util.Scanner;

public class Exponentiation {
    public static void main(String[] args) {
        System.out.print("Geben Sie bitte die Basis ein: ");
        Scanner scannerEingabe = new Scanner(System.in);
        int basis = scannerEingabe.nextInt();
        // scannerEingabe.close();
        System.out.print("Geben Sie bitte den positiven ganzzahligen Exponenten ein: ");
        Scanner scannerExp = new Scanner(System.in);
        int exp = scannerExp.nextInt();
        // scannerExp.close();
        System.out.println("Eegebnis: " + calculateExpotenz(basis, exp));
    }

    public static int calculateExpotenz(int a, int c) {
        if(c == 0) {
            return 1;
        } else {
            return a * calculateExpotenz(a, c - 1);
        }
    }
}
