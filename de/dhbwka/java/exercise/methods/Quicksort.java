package de.dhbwka.java.exercise.methods;

import java.util.Arrays;

public class Quicksort {

    public static void main(String[] args) {
        //int[] arr = {5, 3, 21, 10, 2, 1, 4};
        int[] arr = {9, 9, 9, 10, 11, 11, 11};
        System.out.println("Array unsorted: " + Arrays.toString(arr));
        int pivot = arr.length / 2; // 3
        int[] arrSorted = quicksort(arr, pivot);
        System.out.println("Array sorted: " + Arrays.toString(arrSorted));
    }

    public static int[] quicksort(int[] arr, int pivot) {
        int pivotValue = arr[pivot];
        System.out.println("pivot pointer: " + pivot);
        System.out.println("pivot: " + pivotValue);
        int left = 0;
        int right = arr.length - 1;
        boolean leftStopped = false;
        boolean rightStopped = false;
        while(left <= right) {
            int leftValue = arr[left];
            int rightValue = arr[right];
            if (leftValue < pivotValue) {
                left ++;
            } else {
                System.out.println("left stopped on: " + leftValue);
                leftStopped = true;
            }
            if(rightValue > pivotValue) {
                right --;
            } else {
                System.out.println("right stopped on: " + rightValue);
                rightStopped = true;
            }
            if(leftStopped && rightStopped) {
                //swap
                arr[right] = leftValue;
                arr[left] = rightValue;
                left ++;
                right --;
                leftStopped = false;
                rightStopped = false;
                System.out.println("SWAP: " + leftValue + " and " + rightValue);
                System.out.println("new Array: " + Arrays.toString(arr));
            }
        }
        return arr;
    }
}

