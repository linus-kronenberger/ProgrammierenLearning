package de.dhbwka.java.templates.auswahlbox;

public class Person {
    private String name;
    public Person(String name) {
        super();
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    /** Used for combo box! */
    @Override
    public String toString() {
        return this.name;
    }
}