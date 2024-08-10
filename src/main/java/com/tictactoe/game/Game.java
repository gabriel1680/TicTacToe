package com.tictactoe.game;

public class Game {

    private Player player;
    private final Board board;
    private final GameRules rules;

    public Game() {
        player = Player.O;
        board = new Board();
        rules = new GameRules();
    }

    public Player whoseTurn() {
        return player;
    }

    public void takeTurn(int col, int row) {
        board.place(col, row, player);
        player = other();
    }

    private Player other() {
        return player == Player.O ? Player.X : Player.O;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWin(Player player) {
        return rules.isWin(board, player);
    }
}
