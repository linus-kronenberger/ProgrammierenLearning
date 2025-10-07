package de.dhbwka.java.musterklausurenselbsterstellt.KloaCov2.bereitgestellte_Dateien;

public enum Variant {
    ALPHA("B.1.1.7"),
    BETA("B.1.351"),
    GAMMA("P.1"),
    DELTA("B.1.617"),
    LAMBDA("C.37");
    private String designation;
    private Variant(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
