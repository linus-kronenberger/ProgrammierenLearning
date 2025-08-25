package de.dhbwka.java.exercise.datatypes;

public class shortValue {
    private static short variable = 32767;
    public static void main(String[] args) {
        System.out.println(variable);
        variable ++;
        //Wertebreich Ende erreicht, fängt wieder bei Negativsten short Wert an
        System.out.println(variable);
    }
}