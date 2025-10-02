package de.dhbwka.java.templates.Exceptions;

class ChatterException extends Exception {
    // Parameterless Constructor
    public ChatterException() {
    }

    // Constructor that accepts a message
    public ChatterException(String message) {
        super(message);
    }
}