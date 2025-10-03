package de.dhbwka.java.templates.Threads.Timer;

public class TimerThreadExtended extends Thread {
    private int clock = 10;
    private long clockTime = System.currentTimeMillis();

    public int getClock() {
        return this.clock;
    }
    public void startTimer() {
        while (clock > 0) {
            if(System.currentTimeMillis() - clockTime > 1000) {
                clockTime = System.currentTimeMillis();
                System.out.println("clock: " + clock);
                clock --;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimerThreadExtended thread = new TimerThreadExtended();
        TimerThreadExtended thread2 = new TimerThreadExtended();
        thread.start();
        thread.startTimer();
        thread.join();
        System.out.println("timer finished");
    }
}
