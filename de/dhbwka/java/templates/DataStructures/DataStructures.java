package de.dhbwka.java.templates.DataStructures;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;

public class DataStructures {
    public static void main(String[] args) {
        Map<String, Integer> ages = Map.ofEntries(
        Map.entry("Peter", 22),
        Map.entry("Ulrike", 21),
        Map.entry("Katrin", 23),
        Map.entry("Max", 20) );
        ages.values().stream()
                     .forEach(System.out::println);
        ages.keySet().stream()
                     .forEach(System.out::println);

        Stack<Integer> myStack = new Stack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        List<Integer> myValues = myStack.stream().filter((value) -> value==2).collect(Collectors.toList());
        myValues.stream().forEach(System.out::println);
        //weitere Datenstrukturen aus den Folien
        HashSet<String> mySet = new HashSet<>();
        mySet.add("Hallo");
        mySet.add("test");
        System.out.println(mySet);
    }
}
