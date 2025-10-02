package de.dhbwka.java.exercise.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardDeviation {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            myList.add((int) (Math.random() * 10 + 1));
        }
        System.out.println(myList);
        System.out.println(myList.size());
        double mittelwert = ((double) myList.stream().mapToInt(Integer::intValue).sum() / myList.stream().count());
        System.out.println("Mittelwert: " + mittelwert);
        double deviation = 0;
        double sum = 0;
        for (int i = 0; i <= 99; i++) {
            sum += Math.pow(myList.get(i) - mittelwert, 2);
        }
        deviation = Math.sqrt((double) 1 / (myList.size() - 1) * sum);
        System.out.println(deviation);
    }
}