package de.dhbwka.java.exercise.control;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner yearInput = new Scanner(System.in);
        int year = yearInput.nextInt();
        final String noLeapYearText = "ist kein Schaltjahr";
        if ((year % 4 == 0)) {
            if (year % 400 == 0) {
                System.out.println(year + noLeapYearText);
            } else if (year % 100 == 0) {
                System.out.println(year + noLeapYearText);
            } else {
                System.out.println(year + " ist ein Schaltjahr");
            }
        } else {
            System.out.println(year + noLeapYearText);
        }
    }
}   