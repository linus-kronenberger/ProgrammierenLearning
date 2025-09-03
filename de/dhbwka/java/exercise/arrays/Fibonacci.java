package de.dhbwka.java.exercise.arrays;

public class Fibonacci {
    public static int fib(int n) {
        if(n==0) {
            return 1;
        } else if(n == 1) {
            return 1 + fib(n-1);
        } else {
            return fib(n-1) + fib(n-2);
        }
    }
    public static void main(String[] args) {
        int[] myFibArr = new int[20];
        for (int i = 1; i <= 18; i++) {
            myFibArr[i] = fib(i);
            System.out.println(myFibArr[i]);
        }
    }
}
