package com.tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesTest {

    private GameRules rules;
    private Board board;
    private Player player;

    @BeforeEach
    void setup() {
        player = Player.O;
        board = new Board();
        rules = new GameRules();
    }

    @Test
    void threeInARowIsWin() {
        board.place(0, 0, player);
        board.place(0, 1, player);
        board.place(0, 2, player);
        assertTrue(rules.isWin(board, player));
    }

    @Test
    void threeInAColumnIsWin() {
        board.place(0, 0, player);
        board.place(1, 0, player);
        board.place(2, 0, player);
        assertTrue(rules.isWin(board, player));
    }

    @Test
    void anyConsecutiveThreeInaRowAfterBoardLengthIsNotWin() {
        board.place(2, 0, player);
        board.place(0, 1, player);
        board.place(1, 1, player);
        assertFalse(rules.isWin(board, player));
    }

    @Test
    void anyConsecutiveThreeInAColumnAfterBoardLengthIsNotWin() {
        board.place(0, 2, player);
        board.place(1, 0, player);
        board.place(1, 1, player);
        assertFalse(rules.isWin(board, player));
    }

    @Test
    @Disabled
    void threeInADiagonalIsWin() {
        board.place(0, 0, player);
        board.place(1, 1, player);
        board.place(2, 2, player);
        assertTrue(rules.isWin(board, player));
    }
}