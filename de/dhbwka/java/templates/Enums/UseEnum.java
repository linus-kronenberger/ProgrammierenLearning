package de.dhbwka.java.templates.Enums;

public class UseEnum {
    // Konstanten die im Code genutzt werden können
    enum Season {WINTER, SOMMER, HERBST, FRÜHLING}

    ;

    public static void main(String[] args) {
        Season currentSeason = Season.WINTER;
        if (currentSeason == Season.WINTER) {
            System.out.println(Enums.ZUSTAND1.combineStrings());
        }
    }
}