package de.dhbwka.java.templates.RandomSelection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleList {
    private List<Integer> intList = new ArrayList<>();

    public RandomShuffleList() {
        for (int i = 0; i < 5; i++) {
            intList.add(i);
        }
        System.out.println(intList);
        Collections.shuffle(intList);
        System.out.println(intList);
    }

    public static void main(String[] args) {
        RandomShuffleList shuffleList = new RandomShuffleList();
    }
}
