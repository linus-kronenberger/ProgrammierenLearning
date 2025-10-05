import java.lang.Math;

public class Game {
    private int min;
    private int max;
    private int seconds;
    private char firstCharacter;
    private boolean running;
    List<ColumnTyped> cols;
    public Game(int min, int max, int seconds) {
        this.min = min;
        this.max = max;
        if(!(min>=3 && max>=min)) {
            min = 3;
            max = min;
        }
    }
    public char createFirstCharacter() {
        int randomBuchstabe = Math.random() * 25;
        firstCharacter = 65 + randomBuchstabe;
        return firstCharacter;
    }
    public void createColumns() {

    }
}
