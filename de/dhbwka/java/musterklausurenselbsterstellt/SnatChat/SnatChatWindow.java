import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SnatChatWindow extends JFrame implements SnatChatFrontend{
    private SnatChatRoom room;
    private Account account;
    private JPanel mainPanel = new JPanel();
    private JPanel radioPanel = new JPanel();
    private JLabel accountNameLabel = new JLabel();
    private ChatMessagesComponent chatComponent = new ChatMessagesComponent();
    private List<JRadioButton> radioButtons = new ArrayList<>();
    private JTextField messageTextField = new JTextField();
    private JPanel labelPanel = new JPanel();
    private JPanel sendPanel = new JPanel();
    private JButton sendButton = new JButton();
    private ButtonGroup radioGroup = new ButtonGroup();
    private HashMap<Message, JLabel> map = new HashMap<>();


    public SnatChatWindow(SnatChatRoom room, Account account) {
        this.room = room;
        this.account = account;
        this.setTitle(account.getName() + "(" + room.getRoomName() + ")");
        mainPanel.setLayout(new GridLayout(4, 1));
        accountNameLabel.setText(account.getName());
        //System.out.println(account.getColor());
        accountNameLabel.setBackground(account.getColor());
        //mainPanel.add(accountNameLabel);
        labelPanel.add(accountNameLabel, BorderLayout.CENTER);
        mainPanel.add(labelPanel);
        mainPanel.add(chatComponent);

        for(State state : State.values()) {
            JRadioButton radioButton = new JRadioButton(state.getLabel());
            radioButton.addActionListener(e-> {
                System.out.println("test");
                int index = 0;
                for(JRadioButton button : radioButtons) {
                    if(button == e.getSource()) {
                        break;
                    } else {
                        index ++;
                    }
                }
                account.setState(State.values()[index]);
                //System.out.println("State of user: " + account.getName() + " is now " + account.getState().getLabel());
                room.sendMessage("State of user: " + account.getName() + " is now " + account.getState().getLabel());

            });
            radioButtons.add(radioButton);
            radioGroup.add(radioButton);
            radioPanel.add(radioButton);
        }

        mainPanel.add(radioPanel);
        messageTextField.setColumns(20);
        sendPanel.add(messageTextField, BorderLayout.WEST);
        sendButton.setText("Send");
        messageTextField.addActionListener(e->{
            send();
        });
        sendButton.addActionListener(e->{
            send();
        });
        sendPanel.add(sendButton, BorderLayout.EAST);
        mainPanel.add(sendPanel);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        CountDownThread countdownThread = new CountDownThread(room.getMessages(), map);
        Thread thread = new Thread(countdownThread);
        thread.start();
    }

    public void send() {
        if(messageTextField.getText().isBlank()) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Dear " + account.getName() + ", please enter a message");
        } else {
            Message message = new Message(account, messageTextField.getText());
            room.sendMessage(message);
            messageTextField.setText("");
        }
    }

    @Override
    public void receiveMessage(Message msg) {
        String txt = msg.getSender().getName() + ": " +  msg.getText();
        System.out.println(txt);
        JLabel messageLabel = new JLabel(txt + "[30]");
        map.put(msg, messageLabel);
        messageLabel.setBackground(msg.getSender().getColor());

        chatComponent.add(messageLabel);
        chatComponent.repaint();
        chatComponent.revalidate();
    }

    @Override
    public void receiveMessage(String text) {
        System.out.println(text);
        JLabel messageLabel = new JLabel(text);
        messageLabel.setBackground(Color.GRAY);
        chatComponent.add(messageLabel);
        chatComponent.repaint();
        chatComponent.revalidate();
    }

    @Override
    public Account getAccount() {
        return null;
    }
}
