package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class CrossTotal {
    public static void main(String[] args) {
        System.out.print("Gib eine Zahl ein: ");
        Scanner eingabe = new Scanner(System.in);
        String eingabeWert = eingabe.next();
        eingabe.close();
        int sum = 0;
        for(int i=0; i<eingabeWert.length(); i++)
        {
            String myValue = Character.toString(eingabeWert.charAt(i));
            int z = Integer.parseInt(myValue);
            sum += z;
        }
        System.out.println(sum);
    }
}
