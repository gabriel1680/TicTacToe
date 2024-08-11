package com.tictactoe;

import com.tictactoe.view.cli.CliViewEngine;

public class Main {

    public static void main(String[] args) {
        final var view = new CliViewEngine();
        final var game = new TicTacToe(view);
        game.start();
    }
}
