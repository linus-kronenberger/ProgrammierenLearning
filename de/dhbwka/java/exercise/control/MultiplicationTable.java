package de.dhbwka.java.exercise.control;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String res = String.valueOf(j * i);
                if (res.length() < 3)
                    while (res.length() < 3) {
                        res += " ";
                    }
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }
}
