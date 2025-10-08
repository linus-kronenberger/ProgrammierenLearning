package de.dhbw.ka.soedermemory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame {
    private int rows;
    private int cols;
    private List<Player> players;
    private List<MemoryImages.MemoryImage> images = new ArrayList<>(); //ausgewählte Memory Images
    private Player currentPlayer;
    private boolean runs = true;

    public int getRows() {
        return rows;
    }

    public boolean isRuns() {
        return runs;
    }

    public void setRuns(boolean runs) {
        this.runs = runs;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<MemoryImages.MemoryImage> getImages() {
        return images;
    }

    public void setImages(List<MemoryImages.MemoryImage> images) {
        this.images = images;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player activePlayer) {
        this.currentPlayer = activePlayer;
    }

    public void nextPlayer() {
        int playerIndex = 0;
        for(Player player : players) {
            if(player.equals(currentPlayer)) {
                break;
            } else {
                playerIndex ++;
            }
        }
        if(playerIndex == players.size() - 1) {
            playerIndex = 0;
        } else {
            playerIndex ++;
        }
        players.forEach(p->{p.setStatus(PlayerStatus.WAITING);});
        currentPlayer = players.get(playerIndex);
        currentPlayer.setStatus(PlayerStatus.ACTIVE);
    }

    public boolean isBlancRequired() {
        return (rows*cols)%2 != 0;
    }

    public MemoryGame(List<Player> players, List<MemoryImages.MemoryImage> images, int rows, int cols) {
        this.players = players;
        this.rows = rows;
        this.cols = cols;
        if(players.size() < 2) {
            throw new MemoryException("At least two players required");
        }
        int count = rows * cols;
        if(count%2 != 0) {
            count --;
        }
        if(images.size() < count) {
            throw new MemoryException("Too few images available");
        }
        this.images.clear();
        for (int i = 0; i < rows * cols / 2; i++) {
            this.images.add(images.get(i));
        }
        int size = this.images.size();
        for (int k = 0; k < size; k++) {
            this.images.add(this.images.get(k));
        }
        Collections.shuffle(this.images);
    }
}
