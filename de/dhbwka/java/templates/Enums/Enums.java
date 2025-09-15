package de.dhbwka.java.templates.Enums;

public enum Enums {
    // komplexes Enum: mit den Konstanten properties und Berechnungen verlinken
    ZUSTAND1("test", "test2"),
    ZUSTAND2("t", "t");
    private String prop1;
    private String prop2;

    Enums(String prop1, String prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }

    public String combineStrings() {
        return prop1 + prop2;
    }
}