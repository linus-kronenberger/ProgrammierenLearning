import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;
public class ConnectionSelectionTerm extends JFrame {
    private List<TrainConnection> connections;
    private JPanel mainPanel = new JPanel();
    private JLabel nameLabel = new JLabel("Name");
    private JPanel namePanel = new JPanel();
    private JTextField nameTextField = new JTextField();
    private JPanel buttonPanel = new JPanel();
    private HashMap<JButton, TrainConnection> trainMap = new HashMap<>();
    private HashMap<JButton, TrainConnection> buyMap = new HashMap<>();

    public ConnectionSelectionTerm(List<TrainConnection> connections) {
        this.setTitle("Connections");
        mainPanel.setLayout(new BorderLayout());
        namePanel.setLayout(new GridLayout(1, 2));
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        mainPanel.add(namePanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(connections.size(), 2));
        for(TrainConnection connection : connections) {
            JButton trainButton = new JButton(connection.getName());
            trainMap.put(trainButton, connection);
            trainButton.addActionListener(e->{
                JButton sourceButton = (JButton) e.getSource();
                TrainConnection co = trainMap.get(sourceButton);
                int randomValue = (int) (Math.random() * 100 + 1);
                if(randomValue <= 70) {
                    JOptionPane.showMessageDialog(null, "This connection was cancelled.", "Meldung", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    ConnectionTerm connectionTerm = new ConnectionTerm(co);
                }
            });
            if(connection.isRegional() == true) {
                trainButton.setIcon(DBVasion.createDLTicketIcon());
            }
            buttonPanel.add(trainButton);
            JButton buyButton = new JButton("Buy Ticket");
            buyMap.put(buyButton, connection );
            buyButton.addActionListener(e->{
                JButton sourceButton = (JButton) e.getSource();
                TrainConnection conn = buyMap.get(sourceButton);
                if(nameTextField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please enter a name.", "Meldung", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    double superPreis = 0.2 * conn.getPrice();
                    double regularPreis = 1 * conn.getPrice();
                    String messageTicketKauf = "Regular Price: EUR " + regularPreis + ". Do you want to buy a 'Super-Sparpreis' ticket? (80% discount)";
                    if(JOptionPane.showConfirmDialog(null, messageTicketKauf, "Discount?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        messageTicketKauf = "Ticket for: " + conn.getName() + ": " + nameTextField.getText() + " (EUR " + String.valueOf(superPreis) +" - Super-Sparpreis)";
                        JOptionPane.showMessageDialog(null, messageTicketKauf, "Meldung", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        messageTicketKauf = "Ticket for: " + conn.getName() + ": " + nameTextField.getText() + " (EUR " + String.valueOf(regularPreis) +")";
                        JOptionPane.showMessageDialog(null, "Ticket for: " + conn.getName() + ": " + nameTextField.getText() + " (EUR " + String.valueOf(regularPreis) +")", "Meldung", JOptionPane.INFORMATION_MESSAGE);
                    }
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.txt", true))) {
                        bw.write(messageTicketKauf);
                        bw.newLine();
                        bw.close();
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            });
            buttonPanel.add(buyButton);
        }

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
