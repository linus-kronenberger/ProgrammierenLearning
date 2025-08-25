package de.dhbwka.java.exercise.operators;

public class IncrementDecrement {
    public static void main(String[] args) {
        int i=0;
        int j=0;
        j = ++i; // j=1 i=1
        int k = j++ + ++i; // k=0 + 2 = 2, j=2, i=2
        System.out.println("k: " + k); // k:2
        System.out.println("*: " + j++ + ++i); // *: 2 + 3 = 5 i=3 j=3
        System.out.println(j++ + ++i); // 3 + 
        int m = j++ * ++i;
        System.out.println("m: " + m);
        int n = --j * --i;
        System.out.println("n: " + n);
        System.out.println("i: " + i);
        System.out.println("j: " + j);
    }
}