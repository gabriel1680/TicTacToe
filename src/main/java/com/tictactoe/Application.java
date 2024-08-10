package com.tictactoe;

import com.tictactoe.view.cli.CliViewEngine;
import com.tictactoe.game.Game;
import com.tictactoe.view.ViewEngine;

public class Application {

    private static final Game game = new Game();
    private static final ViewEngine view = new CliViewEngine();
    private static boolean gameOver = false;

    public static void main(String[] args) {
        while (!gameOver) {
            view.renderBoard(game);
            final var player = game.whoseTurn();
            takeTurn();
            if (game.isWin(player)) {
                gameOver = true;
                view.renderWinner(player);
            }
        }
    }

    private static void takeTurn() {
        try {
            final var move = view.getUserMove();
            game.takeTurn(move.col(), move.row());
        } catch (RuntimeException e) {
            view.renderError(e);
        }
    }
}
