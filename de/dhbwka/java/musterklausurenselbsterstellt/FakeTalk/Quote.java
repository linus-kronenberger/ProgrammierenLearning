public class Quote {
    private String text;
    private String person;
    private String role;
    private String source;
    private QuoteType type;

    public Quote(String text, String person, String role, String source, QuoteType type) {
        this.text = text;
        this.person = person;
        this.role = role;
        this.source = source;
        this.type = type;
    }

    public String getCitation() {
        return "Text: " + text + " Person: " + person + " Rolle: " + role + " source: " + source + " type: " + type;
    }

    public QuoteType getType() {
        return this.type;
    }
}  
