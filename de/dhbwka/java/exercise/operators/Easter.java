package de.dhbwka.java.exercise.operators;

import java.util.Scanner;

public class Easter {
    public static void main(String[] args) {
        Scanner einScanner = new Scanner(System.in);
        int jahr = einScanner.nextInt();
        int a = jahr % 19;
        int b = jahr % 4;
        int c = jahr % 7;
        int k = jahr / 100;
        int p = (8 * k + 13) / 25;
        int q = k / 4;
        int m = (15 + k - p - q) % 30;
        int n = (4 + k - q) % 7;
        int d = (19 * a + m) % 30;
        double e = (2 * b + 4 * c + 6d + n) % 7;
        double ostern = (22 + d + e);
        System.out.println("Ostern: " + ostern);
    }

}
