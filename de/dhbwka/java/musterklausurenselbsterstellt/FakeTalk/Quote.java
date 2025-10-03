package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;

public class Quote {
    private String text;
    private String person;
    private String role;
    private String source;
    private QuoteType type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public QuoteType getType() {
        return type;
    }

    public void setType(QuoteType type) {
        this.type = type;
    }

    public Quote(String text, String person, String role, String source, QuoteType type) {
        this.text = text;
        this.person = person;
        this.role = role;
        this.source = source;
        this.type = type;
    }
    public String getCitation() {
        return "This quote is " + type.getLabel() + "!\nFrom: " + person + " (" + source + ")";
    }
}
