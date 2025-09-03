package de.dhbwka.java.exercise.methods;

import java.util.Arrays;

public class Quicksort {
    static int[] arr = {5, 3, 21, 10, 2, 1, 4};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));
        quicksort(0, arr.length - 1, (arr.length / 2));
        System.out.println(Arrays.toString(arr));
    }

    public static void quicksort(int left, int right, int pivot) {
        int leftStart = left;
        int rightStart = right;
        System.out.println(Arrays.toString(arr));
        if(left >= right) {
            return;
        }

        int pivotValue = arr[pivot];

        boolean sorted = true;
        boolean endWhile = false;
        while(!endWhile) {
            
            if(left == right) {
                left ++;
                right --;
                if(!(left <= right)) {
                    left --;
                    right --;
                    endWhile = true;
            }
            }
            int leftValue = arr[left];
            int rightValue = arr[right];
            if(leftValue < pivotValue) {
                left ++;
                if(!(left <= right)) {
                    left --;
                    endWhile = true;
            }
            if(rightValue > pivotValue) {
                right --;
                if(!(left <= right)) {
                    right ++;
                    endWhile = true;
            }
            if(leftValue >= pivotValue && rightValue <= pivotValue) {
                //swap
                arr[left] = rightValue;
                arr[right] = leftValue;
                left ++;
                right --;
                if(!(left <= right)) {
                    left --;
                    right --;
                    endWhile = true;
                sorted = false;
            
            }   
        }
        //recursion
        if(!sorted) {
            int left1 = leftStart;
            int right1 = pivot;
            int piv1 = (right1 + left1) / 2;
            quicksort(left1, right1, piv1);
            int left2 = piv1 + 1;
            int right2 = rightStart;
            int piv2 = (right2 + left2) / 2;
            quicksort(left2, right2, piv2);
        }
    }
}

