package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.view.ViewEngine;

public class TicTacToe {
    private final Game game;
    private final ViewEngine view;
    private final GameState state;

    public TicTacToe(ViewEngine aView) {
        game = new Game();
        state = new GameState();
        view = aView;
    }

    public void start() {
        while (!state.isGameOver())
            ticGameCycle();
        view.renderWinner(state.getLastPlayer());
    }

    private void ticGameCycle() {
        view.renderBoard(game);
        state.setLastPlayer(game.whoseTurn());
        tryPlayerMakeAMove();
        if (game.isWin(state.getLastPlayer())) {
            state.setGameOver();
        }
    }

    private void tryPlayerMakeAMove() {
        try {
            final var move = view.getUserMove();
            game.takeTurn(move.col(), move.row());
        } catch (RuntimeException e) {
            view.renderError(e);
        }
    }
}
