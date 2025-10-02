package de.dhbwka.java.templates.Threads;

public class ThreadTime extends Thread {
    private long time = System.currentTimeMillis();
    private int threadNumber;

    public ThreadTime(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            if (this.threadNumber == 2) {
                Thread.sleep(500);
                time = System.currentTimeMillis();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            long newTime = System.currentTimeMillis();
            if (newTime - time > 1000) {
                System.out.println("thread number: " + this.threadNumber + " peeps!!");
                time = System.currentTimeMillis();
            }
        }

    }

    public static void main(String[] args) {
        ThreadTime thread1 = new ThreadTime(1);
        ThreadTime thread2 = new ThreadTime(2);
        thread1.start();
        thread2.start();
    }
}
