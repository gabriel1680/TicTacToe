package com.tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void marksPlaced() {
        assertEquals(0, board.marksPlaced());
    }

    @Test
    void place() {
        board.place(0, 1, Player.X);
        assertEquals(1, board.marksPlaced());
    }

    @Test
    void placeOccupied() {
        board.place(0, 1, Player.X);
        Executable action = () -> board.place(0, 1, Player.X);
        assertThrows(Board.SpaceOccupiedException.class, action);
        assertEquals(1, board.marksPlaced());
    }

    @Test
    void wrongPlace() {
        var outOfRange = Board.SIZE + 1;
        assertThrows(Board.PlaceOutOfRangeException.class, () -> board.place(0, outOfRange, Player.X));
        assertThrows(Board.PlaceOutOfRangeException.class, () -> board.place(0, -1, Player.X));
        assertThrows(Board.PlaceOutOfRangeException.class, () -> board.place(outOfRange, 0, Player.X));
        assertThrows(Board.PlaceOutOfRangeException.class, () -> board.place(-1, 0, Player.X));
        assertEquals(0, board.marksPlaced());
    }

    @Test
    void getPlayerOn() {
        board.place(0, 1, Player.X);
        board.place(1, 0, Player.O);
        assertEquals(Player.X, board.get(0, 1));
        assertEquals(Player.O, board.get(1, 0));
        assertEquals(Player.Empty, board.get(0, 0));
    }
}