public interface FakeTalkClient {
    public String getPlayerName();
    public void setQuote(Quote q);
    public void addPoints(int points);
    public int getPoints();
}
