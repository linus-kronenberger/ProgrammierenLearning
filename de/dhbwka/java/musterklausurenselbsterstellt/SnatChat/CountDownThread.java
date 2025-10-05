import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class CountDownThread implements Runnable {
    private List<Message> messages;
    private HashMap<Message, JLabel> map;
    private long starttimer = System.currentTimeMillis();
    public CountDownThread(List<Message> messages, HashMap<Message, JLabel> map) {
        this.messages = messages;
        this.map = map;
    }
    @Override
    public void run() {
        while(true) {
            if((System.currentTimeMillis() - starttimer) >= 1000 && map != null) {
                System.out.println("jetzt");
                messages.forEach(m->{
                    m.setSeconds(m.getSeconds() - 1);
                    map.get(m).setText(m.getText() + "[" + m.getSeconds() + "]");
                    if(m.getSeconds() == 0) {
                        map.get(m).setText("");
                        map.remove(m);
                    }
                });
                starttimer = System.currentTimeMillis();
                System.out.println("current time");
            }
        }
    }
}
