package com.tictactoe.game;

public class GameRules {
    public boolean isWin(final Board board, final Player player) {
        return isRowWin(board, player) || isColWin(board, player);
    }

    private static boolean isRowWin(Board board, Player player) {
        return isConsecutive(player, board::get);
    }

    private static boolean isColWin(Board board, Player player) {
        return isConsecutive(player, (i, j) -> board.get(j, i));
    }

    @FunctionalInterface
    private interface GetPlayerOn {
        Player get(int i, int j);
    }

    private static boolean isConsecutive(Player player, GetPlayerOn getPlayerOn) {
        for (int i = 0; i <= Board.SIZE; i++) {
            int consecutiveMarks = 0;
            for (int j = 0; j <= Board.SIZE; j++) {
                if (getPlayerOn.get(i, j) == player) {
                    consecutiveMarks++;
                    if (consecutiveMarks > 2) return true;
                } else {
                    consecutiveMarks = 0;
                }
            }
        }
        return false;
    }
}
