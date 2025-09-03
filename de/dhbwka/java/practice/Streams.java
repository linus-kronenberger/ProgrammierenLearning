package de.dhbwka.java.practice;

import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        // filter names starting with A
        List<String> myNamesList = List.of("Linus", "Anna", "Nikita");
        System.out.println(myNamesList);
        myNamesList.stream().filter( x->
                            x.startsWith("A"))
                            .map(String::toUpperCase)
                            .sorted()
                            .forEach(System.out::println);


        // remove duplicates from a list of integers
        List<Integer> myList = List.of(1, 2, 3, 4, 4, 4, 4);
        List<Integer> newList = myList.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println(newList);
        
        //map list of strings to their length
        List<String> myStringList = List.of("myFirstString", "Linus");
        List<Integer> myStringLengthList = myStringList.stream()
                                                        .map(String::length)
                                                        .collect(Collectors.toList());
        System.out.println(myStringLengthList);

        // join strings with a comma
        String myCommaString = myStringList.stream()
                                            .collect(Collectors.joining(","));
                                            
        System.out.println(myCommaString);
    }
}
