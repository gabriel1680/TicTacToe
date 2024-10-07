package com.tictactoe;

import com.tictactoe.view.cli.CliViewEngine;
import com.tictactoe.view.cli.Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var console = new Console(scanner);
        final var view = new CliViewEngine(console);
        final var game = new TicTacToe(view);
        game.start();
    }
}
