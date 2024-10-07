package com.tictactoe.view.cli;

import com.tictactoe.view.Move;
import com.tictactoe.view.ViewEngine;
import com.tictactoe.game.Board;
import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import com.tictactoe.view.GamePresenter;

public class CliViewEngine implements ViewEngine {
    private final GamePresenter presenter;
    private final Console console;

    public CliViewEngine(Console console) {
        this.console = console;
         presenter = new GamePresenter();
    }

    @Override
    public void renderBoard(Game game) {
        console.clear();
        console.printLine("The TicTacToe Game");
        console.printBlankLine();
        for (int col = 0; col <= Board.SIZE; col++) {
            renderRows(col, game);
        }
        console.printBlankLine();
        console.printLine("Is " + presenter.getPlayerName(game.whoseTurn()) + " turn!");
    }

    private void renderRows(int col, Game game) {
        for (int row = 0; row <= Board.SIZE; row++) {
            renderRow(row, presenter.getPlayerMark(game.getBoard().get(col, row)));
        }
        console.printLine();
        if (col < Board.SIZE) {
            console.printLine("-----------");
        }
    }

    private void renderRow(int row, String mark) {
        console.print(" %s ".formatted(mark));
        if (row < Board.SIZE) {
            console.print("|");
        }
    }

    @Override
    public void renderWinner(Player player) {
        console.printLine(presenter.getWinner(player));
    }

    @Override
    public void renderError(RuntimeException e) {
        console.clear();
        if (e instanceof Board.PlaceOutOfRangeException || e instanceof Board.SpaceOccupiedException) {
            console.printLine("Invalid move %s%n".formatted(e.getMessage()));
        } else {
            console.printLine("Something went wrong");
        }
    }

    @Override
    public Move getUserMove() {
        final var moves = console.readLine().split(",");
        return new Move(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
    }
}
