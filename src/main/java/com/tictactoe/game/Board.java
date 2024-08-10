package com.tictactoe.game;

import java.util.HashMap;

public class Board {
    public static int SIZE = 2;

    private final HashMap<String, Player> boardMap = new HashMap<>();

    public int marksPlaced() {
        return boardMap.size();
    }

    public void place(int col, int row, Player player) {
        final var key = getKey(col, row);
        if (boardMap.containsKey(key))
            throw new SpaceOccupiedException();
        if (col < 0 || col > SIZE || row < 0 || row > SIZE)
            throw new PlaceOutOfRangeException();
        boardMap.put(key, player);
    }

    private static String getKey(int col, int row) {
        return "%s,%s".formatted(col, row);
    }

    public Player get(int col, int row) {
        return boardMap.getOrDefault(getKey(col, row), Player.Empty);
    }

    public static class PlaceOutOfRangeException extends RuntimeException {}

    public static class SpaceOccupiedException extends RuntimeException {}
}
