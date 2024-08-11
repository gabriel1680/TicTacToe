package com.tictactoe;

import com.tictactoe.game.Board;
import com.tictactoe.game.Player;
import com.tictactoe.view.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe sut;
    private ViewSpy view;

    @BeforeEach
    void setUp() {
        view = new ViewSpy();
        sut = new TicTacToe(view);
    }

    @Test
    void givenFiveMoves_whenPlayerWins_thenDisplaysAWinner() {
        view.setMoves(getWinnerMoves());
        sut.start();
        assertEquals(Player.O, view.winner);
    }

    private static List<Move> getWinnerMoves() {
        return List.of(
            new Move(0, 0), new Move(1, 0), new Move(0, 1), new Move(1, 1), new Move(0, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getOccupiedSpaceMoves")
    void givenOccupiedSpaceMove_whenPlayerPutItOnBoard_thenDisplaysErrorMessageAndRetryItsPlay(List<Move> moveList) {
        view.setMoves(moveList);
        sut.start();
        assertInstanceOf(Board.SpaceOccupiedException.class, view.exception);
    }

    private static Stream<List<Move>> getOccupiedSpaceMoves() {
        final var occupiedSpaceMove = List.of(new Move(0, 0));
        return Stream.of(composeMoves(occupiedSpaceMove));
    }

    @ParameterizedTest
    @MethodSource("getOutOfBoundsMoves")
    void givenOutOfBoundsMove_whenPlayerPutItOnBoard_thenDisplaysErrorMessageAndRetryItsPlay(List<Move> moveList) {
        view.setMoves(moveList);
        sut.start();
        assertInstanceOf(Board.PlaceOutOfRangeException.class, view.exception);
    }

    private static Stream<List<Move>> getOutOfBoundsMoves() {
        final var outOfBoundMove = List.of(new Move(0, Board.SIZE + 1));
        return Stream.of(composeMoves(outOfBoundMove));
    }

    private static List<Move> composeMoves(List<Move> firstMoves) {
        return Stream.concat(firstMoves.stream(), getWinnerMoves().stream()).toList();
    }
}