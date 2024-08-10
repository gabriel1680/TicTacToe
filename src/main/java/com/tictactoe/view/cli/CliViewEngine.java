package com.tictactoe.view.cli;

import com.tictactoe.view.Move;
import com.tictactoe.view.ViewEngine;
import com.tictactoe.game.Board;
import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import com.tictactoe.view.GamePresenter;

import java.util.Scanner;

public class CliViewEngine implements ViewEngine {
    private final GamePresenter presenter = new GamePresenter();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void renderBoard(Game game) {
        clear();
        System.out.println("The TicTacToe Game");
        System.out.println("\n");
        for (int col = 0; col <= Board.SIZE; col++) {
            renderRows(col, game);
        }
        System.out.println("\n");
        System.out.println("Is " + presenter.getPlayerName(game.whoseTurn()) + " turn!");
    }

    private void clear() {
        System.out.print("\033c");
        System.out.flush();
    }

    private void renderRows(int col, Game game) {
        for (int row = 0; row <= Board.SIZE; row++) {
            renderRow(row, presenter.getPlayerMark(game.getBoard().get(col, row)));
        }
        System.out.println();
        if (col < Board.SIZE) {
            System.out.println("-----------");
        }
    }

    private void renderRow(int row, String mark) {
        System.out.printf(" %s ", mark);
        if (row < Board.SIZE) {
            System.out.print("|");
        }
    }

    @Override
    public void renderWinner(Player player) {
        System.out.println(presenter.getWinner(player));
    }

    @Override
    public void renderError(RuntimeException e) {
        clear();
        if (e instanceof Board.PlaceOutOfRangeException || e instanceof Board.SpaceOccupiedException) {
            System.out.printf("Invalid move %s%n", e.getMessage());
        } else {
            System.out.println("Something went wrong");
        }
    }

    @Override
    public Move getUserMove() {
        final var move = scanner.next();
        final var moves = move.split(",");
        return new Move(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
    }
}
