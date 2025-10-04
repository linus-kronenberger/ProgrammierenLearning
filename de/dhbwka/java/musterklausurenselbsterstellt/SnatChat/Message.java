public class Message {
    private Account sender;
    private int seconds = 30;
    private long timerTime = System.currentTimeMillis();

    public Message(Account sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public static String rot13(Message message) {
        String oldText = message.getText();
        String newText = "";
        for(char c : oldText.toCharArray()) {

            if(c <= 90 && c >= 65) {
                // Großbuchstaben
                if(c <= 'M') {
                    newText += (char)(c + 13);
                } else {
                    newText += (char)(c - 13);
                }

            } else {
                // Kleinbuchstaben
                if(c <= 'm') {
                    newText += (char)(c + 13);
                } else {
                    newText += (char)(c - 13);
                }
            }
        }

        message.setText(newText);
        return newText;
    }

}
