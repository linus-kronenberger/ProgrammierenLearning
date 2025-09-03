package de.dhbwka.java.exercise.arrays;

public class Pascal {
    public static void main(String[] args) {
        int[][] pascalArr = new int[9][];
        for (int i = 0; i < pascalArr.length; i++) {
            pascalArr[i] = new int[i + 1];
            for(int j=0; j<pascalArr[i].length; j++) {
                if(j==0 || j==pascalArr[i].length - 1) {
                    pascalArr[i][j] = 1;
                } else {
                    pascalArr[i][j] = pascalArr[i - 1][j - 1] + pascalArr[i - 1][j];
                }
                System.out.print(pascalArr[i][j]);
            }
            System.out.println();
        }
    }
}
