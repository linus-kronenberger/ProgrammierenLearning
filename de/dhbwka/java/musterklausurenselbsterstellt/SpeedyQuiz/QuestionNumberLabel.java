package de.dhbwka.java.musterklausurenselbsterstellt.SpeedyQuiz;

import javax.swing.*;

public class QuestionNumberLabel extends JLabel {
    private Status status = Status.PENDING;
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.setBackground(status.getColor());
    }

    public QuestionNumberLabel() {
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(status.getColor());
    }
}
