package de.dhbwka.java.exercise.classes.periodic;

public class Element {
    private String Name;
    private String Symbol;
    private int ordinal;
    private char shell;
    private int phase;
    private boolean gruppe; // MAIN=true SIDE=false

    public String toString() {
        String res = "Name: " + Name + " Symbol: " + Symbol + " ordinal: " + ordinal + " shell: " + shell + " phase: " + phase + " gruppe: " + gruppe;
        return res;
    }


    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSymbol() {
        return this.Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public char getShell() {
        return this.shell;
    }

    public void setShell(char shell) {
        this.shell = shell;
    }

    public int getPhase() {
        return this.phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public boolean isGruppe() {
        return this.gruppe;
    }

    public void setGruppe(boolean gruppe) {
        this.gruppe = gruppe;
    }
}
