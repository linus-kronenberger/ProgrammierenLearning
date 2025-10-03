package de.dhbwka.java.templates.Threads.Timer;

import javax.swing.JFrame;

public class Timer extends JFrame implements Runnable {
    private long timerTime = System.currentTimeMillis();
    private int timerClock = 10;
    private Thread thread = new Thread(this);
    private boolean threadRuns = true;

    @Override
    public void run() {
        while (threadRuns) {
            if (timerClock == 0) {
                threadRuns = false;
                this.dispose();
                System.exit(0);
            } else if (System.currentTimeMillis() - timerTime >= 1000) {
                timerClock--;
                timerTime = System.currentTimeMillis();
                this.setTitle(String.valueOf(timerClock) + " Seconds");
            }
        }

    }

    public Timer() {
        this.setTitle(String.valueOf(timerClock) + " Seconds");
        this.setSize(200, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Thread getThread() {
        return thread;
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.getThread().start();
    }
}
