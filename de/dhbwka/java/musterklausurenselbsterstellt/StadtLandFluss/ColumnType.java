public enum ColumnType {
    CITY("Stadt"),
    COUNTRY("Land"),
    RIVER("Fluss"),
    PROFESSION("Beruf"),
    ANIMAL("Tier"),
    NAME("Vorname"),
    SPORT("Sportart"),
    FOOD("Lebensmittel"),
    BEVERAGE("Getränk"),
    GAME("Spiel");

    private ColumnType(String title) {
        this.title = title;
    }
    private String title;
}
