package de.dhbwka.java.musterklausurenselbsterstellt.FakeTalk;

public interface FakeTalkClient {
    public String getPlayerName();
    public void setQuote(Quote q);
    public void addPoints(int points);
    public int getPoints();
    public Quote getQuote();
}
