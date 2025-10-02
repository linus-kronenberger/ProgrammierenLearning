package de.dhbwka.java.templates.Threads;

public class ThreadValue {
    int value;

    public ThreadValue(int value) {
        this.value = value;
    }

    public synchronized void increaseValue() {
        this.value++;
    }
}