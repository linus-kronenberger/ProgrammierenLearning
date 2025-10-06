package de.dhbwka.java.musterklausurenselbsterstellt.StadtLandFluss;

public enum ColumnType {
    CITY("Stadt"),
    COUNTRY ("Land"),
    RIVER ("Fluss"),
    PROFESSION ("Beruf"),
    ANIMAL ("Tier"),
    NAME ("Vorname"),
    SPORT ("Sportart"),
    FOOD ("Lebensmittel"),
    BEVERAGE ("Getränk"),
    GAME ("Spiel");
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private ColumnType(String title) {
        this.title = title;
    }
}
