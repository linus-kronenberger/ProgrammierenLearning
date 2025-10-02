package de.dhbwka.java.exercise.arrays;

import java.util.Scanner;

public class MatrixSubtraction {
    public static void main(String[] args) {
        System.out.print("Bitte Anzahl der Zeilen n eingeben: ");
        Scanner zeilenEingabe = new Scanner(System.in);
        int zeilen = zeilenEingabe.nextInt();
        System.out.print("Bitte Anzahl der Spalten m eingeben: ");
        Scanner spaltenEingabe = new Scanner(System.in);
        int spalten = spaltenEingabe.nextInt();
        spaltenEingabe.close();
        zeilenEingabe.close();
        int[][] matrix1 = new int[zeilen][spalten];
        System.out.println("Matrix 1: ");
        for (int zeile = 0; zeile < zeilen; zeile++) {
            for (int spalte = 0; spalte < spalten; spalte++) {
                matrix1[zeile][spalte] = (int) (Math.random() * 10 + 1);
                System.out.print(matrix1[zeile][spalte] + " ");
            }
            System.out.println();
        }
        System.out.println("Matrix 2: ");
        int[][] matrix2 = new int[zeilen][spalten];
        int[][] matrixRes = new int[zeilen][spalten];
        for (int zeile = 0; zeile < zeilen; zeile++) {
            for (int spalte = 0; spalte < spalten; spalte++) {
                matrix2[zeile][spalte] = (int) (Math.random() * 10 + 1);
                matrixRes[zeile][spalte] = (matrix1[zeile][spalte]) - (matrix2[zeile][spalte]);
                System.out.print(matrix2[zeile][spalte] + " ");
            }
            System.out.println();
        }
        System.out.println("x - y:");
        for (int zeile = 0; zeile < zeilen; zeile++) {
            for (int spalte = 0; spalte < spalten; spalte++) {
                System.out.print(matrixRes[zeile][spalte] + " ");
            }
            System.out.println();
        }
    }
}
