package de.dhbwka.java.templates.Threads;

public class ThreadExtended extends Thread {
    int threadNumber;
    ThreadValue myValue;

    public ThreadExtended(int threadNumber, ThreadValue myValue) {
        this.threadNumber = threadNumber;
        this.myValue = myValue;
    }

    @Override
    public void run() {
        System.out.println("my override");
        this.myValue.increaseValue(); // weil es synchronized ist kann das parallel geupdatet werden
        while (true) {
            System.out.println("thread number: " + threadNumber + " is running :D");

        }
    }

    public static void main(String[] args) {
        ThreadValue myValue = new ThreadValue(1);
        ThreadExtended thread1 = new ThreadExtended(1, myValue);
        ThreadExtended thread2 = new ThreadExtended(2, myValue);
        // beide threads laufen jetzt parallel
        thread1.start();
        thread2.start();
    }
}
