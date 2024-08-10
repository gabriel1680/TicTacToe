package com.tictactoe.cli;

import com.tictactoe.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GamePresenterTest {

    private GamePresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new GamePresenter();
    }

    @Test
    void getPlayerMark() {
        assertEquals("O", presenter.getPlayerMark(Player.O));
        assertEquals("X", presenter.getPlayerMark(Player.X));
        assertEquals(" ", presenter.getPlayerMark(Player.Empty));
    }

    @Test
    void getPlayerName() {
        assertEquals("Player O", presenter.getPlayerName(Player.O));
    }


    @Test
    void getWinner() {
        assertEquals("Player X is a winner!!!", presenter.getWinner(Player.X));
    }
}