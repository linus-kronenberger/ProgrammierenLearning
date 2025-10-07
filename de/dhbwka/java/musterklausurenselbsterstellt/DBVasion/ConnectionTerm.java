package de.dhbwka.java.musterklausurenselbsterstellt.DBVasion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionTerm extends JFrame {
    private TrainConnection connection;
    private List<StopComponent> stopComponents = new ArrayList<>();
    public ConnectionTerm(TrainConnection connection) {
        this.connection = connection;
        this.setLayout(new GridLayout(connection.getStops().size(),1));
        int index = 0;
        int delay = 0;
        for(Stop stop : connection.getStops()) {
            StopComponent component = new StopComponent(stop);
            if(index != 0) {
                delay += (int)(Math.random() * 15 - 4);
                component.setDelay(delay);
                if(delay > 10) {
                    component.setStopReached(false);
                    component.setReason(StopComponent.DELAY_REASONS[(int)(Math.random() * (StopComponent.DELAY_REASONS.length))]);
                }
            }
            this.add(component);
            stopComponents.add(component);
            index ++;
        }
        this.setTitle(connection.getName());
        this.setSize(500, 500);
        this.setVisible(true);

        Runnable runnable = () -> {
            int randomSeconds = (int) ((Math.random() * 3) + 1) * 1000;
            int i = 0;
            while(i < stopComponents.size()) {
                System.out.println("test");
                try {
                    stopComponents.get(i).setStopReached(true);
                    stopComponents.get(i).repaint();
                    Thread.sleep(randomSeconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            };
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
