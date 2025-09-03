package de.dhbwka.java.exercise.methods;

import java.util.Arrays;

public class Quicksort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 21, 10, 2, 1, 4};
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
        boolean needRecursion = false;
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
                if(leftValue != rightValue) {
                    needRecursion = true;
                }
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
        //resursion
        if(needRecursion == true) {
            System.out.println("recursion");
            int pivotLeft = arr.length / 4;
            int pivotRight = arr.length/2 + arr.length / 4;
            int arrLeftLength = pivot;
            int arrRightLength = arr.length - pivot;
            if(arrLeftLength > 1 ) {
                int[] leftArr = new int[arrLeftLength];
                int[] rightArr = new int[arrRightLength];

                //fill left array
                for (int i = 0; i < arr.length; i++) {
                    if(i < pivot) {
                        leftArr[i] = arr[i];
                    }
                    else {
                        rightArr[i - pivot] = arr[i];
                    }
                }

                System.out.println("left array: " + Arrays.toString(leftArr));
                System.out.println("right array: " + Arrays.toString(rightArr));

                //fill right array

                int[] leftRes = quicksort(leftArr, pivotLeft);
                int[] rightRes = quicksort(rightArr, pivotRight);

                //merge
                for (int i = 0; i < arr.length; i++) {
                    if(i < pivot) {
                        arr[i] = leftRes[i];
                    }
                    else {
                        arr[i] = rightRes[i - pivot];
                    }
                }
            }
            
        }
        return arr;
    }
}