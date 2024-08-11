package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void whoseTurn() {
        assertEquals(Player.O, game.whoseTurn());
    }

    @Test
    void takeTurnAndThenWhoseTurn() {
        game.takeTurn(0, 0);
        assertEquals(Player.O, game.getBoard().get(0, 0));
        assertEquals(Player.X, game.whoseTurn());

        game.takeTurn(1, 0);
        assertEquals(Player.X, game.getBoard().get(1, 0));
        assertEquals(Player.O, game.whoseTurn());
    }

    @Test
    void isWin() {
        game.takeTurn(0, 0);
        assertFalse(game.isWin(Player.O));
        assertFalse(game.isWin(Player.X));

        game.takeTurn(1, 0);
        assertFalse(game.isWin(Player.O));
        assertFalse(game.isWin(Player.X));

        game.takeTurn(0, 1);
        assertFalse(game.isWin(Player.O));
        assertFalse(game.isWin(Player.X));

        game.takeTurn(1, 1);
        assertFalse(game.isWin(Player.O));
        assertFalse(game.isWin(Player.X));

        game.takeTurn(0, 2);
        assertTrue(game.isWin(Player.O));
        assertFalse(game.isWin(Player.X));
    }
}