import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CellButton extends JButton {
    private double dose;
    private boolean polluter;
    public double getDose() {
        return dose;
    }
    public boolean getPolluter() {
        return this.polluter;
    }
    public void setDose(double dose) {
        this.setText(String.valueOf(dose));
        this.dose = dose;
    }
    public void setPolluter(boolean polluter) {
        if(polluter == true) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        }
        this.polluter = polluter;
    }
}

