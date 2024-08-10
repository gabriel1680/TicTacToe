package com.tictactoe.cli;

import com.tictactoe.game.Board;
import com.tictactoe.game.Game;
import com.tictactoe.game.Player;

import java.util.Scanner;

public class CliApplication {

    private static final Game game = new Game();
    private static final GamePresenter presenter = new GamePresenter();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean gameOver = false;

    public static void main(String[] args) {
        while (!gameOver) {
            clear();
            renderBoard();
            final var player = game.whoseTurn();
            makeMove(scanner.next());
            if (game.isWin(player)) {
                gameOver = true;
                renderWinner(player);
            }
        }
    }

    private static void renderWinner(Player player) {
        System.out.println(presenter.getWinner(player));
    }

    private static void clear() {
        System.out.print("\033c");
        System.out.flush();
    }

    private static void makeMove(String move) {
        var moves = move.split(",");
        game.takeTurn(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
    }

    private static void renderBoard() {
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
