package com.tictactoe;

import com.tictactoe.game.Player;

public class GameState {
    private Player lastPlayer;
    private boolean gameOver;

    public void setGameOver() {
        gameOver = true;
    }

    public void setLastPlayer(Player aPlayer) {
        lastPlayer = aPlayer;
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
