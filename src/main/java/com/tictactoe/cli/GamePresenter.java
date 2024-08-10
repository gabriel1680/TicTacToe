package com.tictactoe.cli;

import com.tictactoe.game.Player;

public class GamePresenter {
    public String getPlayerName(Player player) {
        return "Player " + getPlayerMark(player);
    }

    public String getPlayerMark(Player player) {
        return switch (player) {
            case O -> "O";
            case X -> "X";
            case Empty -> " ";
        };
    }

    public String getWinner(Player player) {
        return getPlayerName(player) + " is a winner!!!";
    }
}
