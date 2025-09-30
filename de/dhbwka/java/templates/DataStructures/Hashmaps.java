package de.dhbwka.java.templates.DataStructures;

import java.util.HashMap;
import java.util.Map;

public class Hashmaps {
    public static void main(String[] args) {
        System.out.println("Hashmap test");
        Map<Integer, Integer> myMap = new HashMap<>();
        myMap.put(1, 10);
        System.out.println("Keys:" + myMap.keySet());
        System.out.println("Values:" + myMap.values());
        System.out.println("Value for key 1:" + myMap.get(1));
        System.out.println("Key vor value 10:" + myMap.entrySet().stream().filter(entry -> entry.getValue() == 10).map(Map.Entry::getKey).findFirst().orElse(null));
    }
}
