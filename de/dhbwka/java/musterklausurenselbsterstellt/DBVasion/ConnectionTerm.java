import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConnectionTerm extends JFrame implements Runnable {
    private TrainConnection trainConnection;
    private List<StopComponent> stopComponents = new ArrayList<>();
    @Override
    public void run() {
        List<Stop> stops = trainConnection.getStops();
        int index = 0;
        boolean runs = true;
        while(runs) {
            System.out.println(index);
            StopComponent stopComponent = stopComponents.get(index);
            stopComponent.setStopReached(true);
            index ++;
            try {
                Thread.sleep((int) (Math.random() * 3 + 2) * 1000 );
                if(index == stops.size() - 1 ) {
                    StopComponent newStopComponent = stopComponents.get(index);
                    newStopComponent.setStopReached(true);
                    runs = false;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }

    public ConnectionTerm(TrainConnection trainConnection) throws DBException {
        this.trainConnection = trainConnection;
        this.setTitle(trainConnection.getName());
        
        List<Stop> stops = trainConnection.getStops();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(stops.size(), 1));
        
        for (int i = 0; i < stops.size(); i++) {
            Stop stop = stops.get(i);
            //Verspätung zufällig berechnen
            int delay = 0;
            delay = (int) (Math.random() * 15);
            
        
       
            StopComponent stopComponent = new StopComponent(stop);
            stopComponents.add(stopComponent);
            stopComponent.setDelay(delay);
            if(delay > 10) {
                stopComponent.setReason(StopComponent.DELAY_REASONS[(int)(Math.random() * StopComponent.DELAY_REASONS.length)]);
            }
            mainPanel.add(stopComponent);
        }

        Thread thread1 = new Thread(this);
        thread1.start();

        this.add(mainPanel);
    }

}
