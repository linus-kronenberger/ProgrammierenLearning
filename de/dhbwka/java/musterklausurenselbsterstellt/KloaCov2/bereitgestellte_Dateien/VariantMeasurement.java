package de.dhbwka.java.musterklausurenselbsterstellt.KloaCov2.bereitgestellte_Dateien;

import java.util.*;

public class VariantMeasurement {
    //Misst die cov varianten

    private SewagePlant plant;
    //Zeitpunkt messung
    private Date date;

    // DS Erfassung Messwerte
    private HashMap<Variant, Integer> measurements;

    // parts per poo

    public VariantMeasurement(SewagePlant plant, Date date, HashMap<Variant, Integer> measurements) {
        this.plant = plant;
        this.date = date;
        this.measurements = measurements;
    }

    public SewagePlant getPlant() {
        return plant;
    }
    public Date getDate() {
        return date;
    }

    public void setPlant(SewagePlant plant) {
        this.plant = plant;
    }

    public HashMap<Variant, Integer> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(HashMap<Variant, Integer> measurements) {
        this.measurements = measurements;
    }

    public int getTotalValue() {
        List<Integer> intValues = new ArrayList<>(measurements.values());
        return intValues.stream().reduce(Integer::sum).orElse(0);
    }
}

