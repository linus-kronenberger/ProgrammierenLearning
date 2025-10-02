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
        boolean needRecursion = true;
        while (left <= right) {
            int leftValue = arr[left];
            int rightValue = arr[right];
            if (leftValue < pivotValue) {
                left++;
            } else {
                System.out.println("left stopped on: " + leftValue);
                leftStopped = true;
            }
            if (rightValue > pivotValue) {
                right--;
            } else {
                System.out.println("right stopped on: " + rightValue);
                rightStopped = true;
            }
            if (leftStopped && rightStopped) {
                //swap
                arr[right] = leftValue;
                if (left == pivot) {
                    pivot = right;
                }
                arr[left] = rightValue;
                if (right == pivot) {
                    pivot = left;
                }
                left++;
                right--;
                leftStopped = false;
                rightStopped = false;
                System.out.println("SWAP: " + leftValue + " and " + rightValue);
                System.out.println("new Array: " + Arrays.toString(arr));
            }
        }
        //resursion
        if (needRecursion == true) {
            System.out.println("recursion");

            int pivotLeft = pivot / 2;
            int pivotRight = (int) (pivot * 1.5);
            int arrLeftLength = pivot;
            int arrRightLength = arr.length - pivot;

            int[] rightArr = new int[arrRightLength];
            int[] leftArr = new int[arrLeftLength];

            //fill left array
            for (int i = 0; i < arr.length; i++) {
                if (i < pivot) {
                    leftArr[i] = arr[i];
                } else {
                    rightArr[i - pivot] = arr[i];
                }
            }

            System.out.println("left array: " + Arrays.toString(leftArr));
            System.out.println("right array: " + Arrays.toString(rightArr));

            //fill right array
            pivotRight = pivotRight - arrLeftLength;
            int[] leftRes = null;
            int[] rightRes = null;
            if (leftArr.length > 0) {
                leftRes = quicksort(leftArr, pivotLeft);
            }
            if (rightArr.length > 0) {
                rightRes = quicksort(rightArr, pivotRight);
            }

            //merge
            for (int i = 0; i < arr.length; i++) {
                if (i < pivot) {
                    if (arrLeftLength > 1) {
                        arr[i] = leftRes[i];
                    }
                } else {
                    if (arrRightLength > 1) {
                        arr[i] = rightRes[i - pivot];
                    }
                }
            }
        }
        return arr;
    }
}