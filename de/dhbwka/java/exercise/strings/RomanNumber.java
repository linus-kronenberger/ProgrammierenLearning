package de.dhbwka.java.exercise.strings;

import java.util.Scanner;

public class RomanNumber {
    public static void main(String[] args) {
        System.out.print("Bitte geben Sie eine römische Zahl ein: ");
        Scanner eingabeScanner = new Scanner(System.in);
        StringBuilder romanStringBuffer = new StringBuilder(eingabeScanner.next());
        eingabeScanner.close();
        String romanString = romanStringBuffer.toString();
        int res = 0;
        for (int i = 0; i < romanString.length(); i += 2) {
            String seqString = romanStringBuffer.substring(i, i + 1);
            switch (seqString) {
                case "IV":
                    res += 4;
                    break;
                case "IX":
                    res += 9;
                    break;
                case "XL":
                    res += 40;
                    break;
                case "XC":
                    res += 90;
                    break;
                case "CD":
                    res += 400;
                    break;
                case "CM":
                    res += 900;
                    break;
                default:
                    res += defaultValuesAdded(seqString);
                    break;
            }
        }
        System.out.println("res: " + res);
    }

    public static int defaultValuesAdded(String subString) {
        int res = 0;
        for (int i = 0; i < subString.length(); i++) {
            switch (subString.charAt(i)) {
                case 'M':
                    res += 1000;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'C':
                    res += 100;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'X':
                    res += 10;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'I':
                    res += 1;
                    break;
                default:
                    break;
            }
        }
        return res;
    }
}
