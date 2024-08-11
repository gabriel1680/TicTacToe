package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import com.tictactoe.view.ViewEngine;

public class TicTacToe {
    private final Game game;
    private final ViewEngine view;

    private Player lastPlayer;
    private boolean gameOver;

    public TicTacToe(ViewEngine aView) {
        game = new Game();
        view = aView;
        gameOver = false;
        lastPlayer  = game.whoseTurn();
    }

    public void start() {
        while (!gameOver)
            ticGameCycle();
        view.renderWinner(lastPlayer);
    }

    private void ticGameCycle() {
        view.renderBoard(game);
        lastPlayer = game.whoseTurn();
        takeTurn();
        if (game.isWin(lastPlayer)) {
            gameOver = true;
        }
    }

    private void takeTurn() {
        try {
            final var move = view.getUserMove();
            game.takeTurn(move.col(), move.row());
        } catch (RuntimeException e) {
            view.renderError(e);
        }
    }
}
