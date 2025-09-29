import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.lang.Math;

public class ConnectionSelectionTerm extends JFrame {
    List<TrainConnection> zugverbindungen;
    JTextField nameButton = new JTextField();
    List<JButton> iceButtons = new ArrayList<>();
    List<JButton> buyButtons = new ArrayList<>();
    // graphisch Oberfläch zur Auswahl verfügbarer Zugverbindungen
    public ConnectionSelectionTerm(List<TrainConnection> zugverbindungen) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.zugverbindungen = zugverbindungen;
        this.setTitle("Connections");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));;
        //upperPanel
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(1, 2));
        upperPanel.add(new JLabel("Name"));
        upperPanel.add(nameButton);
        mainPanel.add(upperPanel);
        JPanel lowerPanel = new JPanel();
        // ices und buy ticket buttons
        lowerPanel.setLayout(new GridLayout(zugverbindungen.size(), 2));
        for (int i = 0; i < zugverbindungen.size(); i++) {
            JButton iceButton = new JButton(zugverbindungen.get(i).getName());
            iceButton.putClientProperty("trainConnection", zugverbindungen.get(i));
            JButton buyButton = new JButton("Buy Ticket");
            buyButton.putClientProperty("price", zugverbindungen.get(i).getPrice() );
            buyButton.putClientProperty("iceName", zugverbindungen.get(i).getName() );
            lowerPanel.add(iceButton);
            lowerPanel.add(buyButton);
            iceButtons.add(iceButton);
            buyButtons.add(buyButton);
            
            if(zugverbindungen.get(i).regional == true) {
                iceButton.setIcon(DBVasion.createDLTicketIcon());
            }
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double price =  (double)((JButton) e.getSource()).getClientProperty("price");
                    String iceName = (String)((JComponent) e.getSource()).getClientProperty("iceName");
                    String regularPrice = String.valueOf(price);
                    String reducedPrice = String.format("%.2f", 0.2 * price); 
                    if(nameButton.getText().equals("")) {
                        JOptionPane.showMessageDialog(
                        null,
                        "Please enter a name.",
                        "Meldung",
                        JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int result = JOptionPane.showConfirmDialog(
                            null,
                            "RegularPrice: " + regularPrice + ". Do you want to buy a 'Super-Sparpreis ticket?(80% discount)" ,
                            "Discount?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );
                        
                        if (result == JOptionPane.YES_OPTION) {
                            System.out.println("Benutzer hat 'Ja' gewählt.");
                             String text = "Ticket for " + iceName + ": " + nameButton.getText() + " (" + "EUR " + reducedPrice + " - Super-Sparpreis)";
                            JOptionPane.showMessageDialog(
                                null,
                                text,
                                "Meldung",
                                JOptionPane.INFORMATION_MESSAGE);
                                try (BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.txt", true))) {
                                    bw.write(text);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                        } else if (result == JOptionPane.NO_OPTION) {
                            System.out.println("Benutzer hat 'Nein' gewählt.");
                        } else { // result == JOptionPane.CLOSED_OPTION
                            System.out.println("Dialog wurde geschlossen ohne Auswahl.");
                        }
                    }
                }
            });
            iceButton.putClientProperty("trainConnection", zugverbindungen.get(i));
            iceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //70% Wkt, das nur eine Meldung kommt, dass der Zug ausfällt
                    double random = Math.random();
                    if(random <= 0.7) {
                        JOptionPane.showMessageDialog(
                        null,
                        "This connection was cancelled.",
                        "Meldung",
                        JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        TrainConnection trainConnection = (TrainConnection)((JComponent) e.getSource()).getClientProperty("trainConnection");
                        ConnectionTerm connectionTerm;
                        try {
                            connectionTerm = new ConnectionTerm(trainConnection);
                            connectionTerm.setVisible(true);
                        } catch (DBException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }   
                }
            });
        }
        mainPanel.add(lowerPanel);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
    }
}
