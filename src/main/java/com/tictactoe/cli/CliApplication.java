package com.tictactoe.cli;

import com.tictactoe.game.Board;
import com.tictactoe.game.Game;

import java.util.Scanner;

public class CliApplication {
    public static void main(String[] args) {
        final var game = new Game();
        final var presenter = new GamePresenter();
        final var scanner = new Scanner(System.in);

        boolean gameOver = false;
        while (!gameOver) {
            clear();
            renderBoard(presenter, game);
            makeMove(scanner.next(), game);
            if (game.isWin()) {
                gameOver = true;
                renderWinner(presenter, game);
            }
        }
    }

    private static void clear() {
        System.out.print("\033c");
        System.out.flush();
    }

    private static void renderWinner(GamePresenter presenter, Game game) {
        System.out.println(presenter.getWinner(game.whoseTurn()));
    }

    private static void makeMove(String move, Game game) {
        var moves = move.split(",");
        game.takeTurn(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
    }

    private static void renderBoard(GamePresenter presenter, Game game) {
        System.out.println("The TicTacToe Game");
        System.out.println("\n");
        for (int col = 0; col <= Board.SIZE; col++) {
            for (int row = 0; row <= Board.SIZE; row++) {
                System.out.printf(" %s ", presenter.getPlayerMark(game.getBoard().get(col, row)));
                if (row  < Board.SIZE) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (col < Board.SIZE) {
                System.out.println("-----------");
            }
        }
        System.out.println("\n");
        System.out.println("Is " + presenter.getPlayerName(game.whoseTurn()) + " turn!");
    }
}
