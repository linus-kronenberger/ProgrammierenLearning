package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        // mit String Buffer
        System.out.print("Bitte Wort eingeben: ");
        Scanner eingabeScanner = new Scanner(System.in);
        StringBuffer originalStringBuffer = new StringBuffer(eingabeScanner.next());
        eingabeScanner.close();
        System.out.print("umgekehrt: ");
        StringBuffer reversedStringBuffer = new StringBuffer();
        reversedStringBuffer = originalStringBuffer.reverse();


        System.out.println(reversedStringBuffer.toString());
        if (originalStringBuffer.toString().equals(reversedStringBuffer.toString())) {
            System.out.println("ist ein Palindrom.");
        } else {
            System.out.println("ist kein Palindrom.");
        }
    }
}
