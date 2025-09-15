package de.dhbwka.java.templates.Threads;

public class ThreadImplemented implements Runnable {
    @Override
    public void run() {
        System.out.println("my thread works!!!");
    }
    public static void main(String[] args) {
        ThreadImplemented myThread = new ThreadImplemented();
        ThreadImplemented myThread2 = new ThreadImplemented();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread2);
        thread1.start();
        thread2.start();
    }
}
