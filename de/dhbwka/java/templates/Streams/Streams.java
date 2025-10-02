package de.dhbwka.java.templates.Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {


        Integer[] myValueArray = {1, 2, 3, 4, 5, 6};
        List<Integer> myValueList = new ArrayList<>();
        Collections.addAll(myValueList, myValueArray);

        // map
        // Menge an Werten jeweils verändern -> Output ist neue Menge
        //mehrere Zeilen einfügen wie folgt:

        myValueList.stream()
                .map(value -> {
                    return value += 2;
                })
                .forEach(System.out::println);

        // filter
        // Menge an Werten aus Menge an Werten filtern -> Output ist neue Menge
        //durch collect kann es in eine neue Liste abgespeichert werden (optional)

        List<Integer> myNewList = myValueList.stream()
                .filter((value) -> value == 1)
                .collect(Collectors.toList());
        myNewList.forEach((intValue) -> {
            System.out.println("value: " + intValue);
        });

        // reduce
        // Aus Menge einen Wert berechnen -> Output ist ein Wert
        System.out.println(myValueList.stream()
                .reduce(0, Integer::sum));


        //long anzahl = liste.stream().count();

    }
}
