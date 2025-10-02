package de.dhbwka.java.templates.auswahlbox;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Auswahlbox extends JFrame {
    public static void main(String[] args) {
        JFrame f = new JFrame("Demo");
        List<Person> persons = new LinkedList<>();
        persons.add(new Person("Tinky Winky"));
        persons.add(new Person("Dipsi"));
        persons.add(new Person("Laa-Laa"));
        persons.add(new Person("Po"));
        JComboBox<Person> cbxPersons = new JComboBox<>(persons.toArray(new Person[0]));
        JButton button = new JButton("Sag Hallo!");
        button.addActionListener(e->{
            Person selectedPerson = (Person)cbxPersons.getSelectedItem();
// Person selectedPerson = persons.get(cbxPersons.getSelectedIndex()); // Alternativ
            JOptionPane.showMessageDialog(f, selectedPerson.getName() + " sagt: Ah Oh!");
        });
        f.add(new JLabel("Zeit für Teletubbies! Zeit für Teletubbies!"), BorderLayout.NORTH);
        f.add(cbxPersons, BorderLayout.CENTER);
        f.add(button, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
