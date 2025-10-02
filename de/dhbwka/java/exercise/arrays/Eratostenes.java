package de.dhbwka.java.exercise.arrays;

import java.util.Arrays;

public class Eratostenes {
    public static void main(String[] args) {
        int[] primzahlen = ermittlePrimzahlen(10);
        System.out.println(Arrays.toString(primzahlen));

    }

    public static int[] ermittlePrimzahlen(int n) {
        int[] sieve = new int[n - 1];
        for (int i = 2; i < sieve.length + 2; i++) {
            sieve[i - 2] = i;
        }
        int[] primes = new int[n];
        int primesIndex = 0;
        boolean getauscht = true;
        while (getauscht) {
            int min = 0;
            for (int i = 0; i < sieve.length; i++) {
                if (sieve[i] < min || min == 0) {
                    if (sieve[i] != 0) {
                        min = sieve[i];
                    }
                }
            }
            if (min != 0) {
                primes[primesIndex] = min;
                primesIndex++;
            } else {
                getauscht = false;
            }
            for (int j = 0; j < sieve.length; j++) {
                if (min != 0 && sieve[j] % min == 0) {
                    sieve[j] = 0;
                }
            }
        }

        return primes;
    }
}
