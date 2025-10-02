package de.dhbwka.java.exercise.arrays;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10 + 1);
        }
        System.out.println(Arrays.toString(bubblesort(arr)));
    }

    public static int[] bubblesort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int save = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = save;
                }
            }
        }
        return arr;
    }
}
