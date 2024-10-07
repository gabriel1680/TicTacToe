package com.tictactoe.view.cli;

import com.tictactoe.game.Board;
import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import com.tictactoe.view.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CliViewEngineTest {

    @Mock
    private Console console;

    @InjectMocks
    private CliViewEngine sut;

    @Test
    void renderBoardShouldRenderTitle() {
        sut.renderBoard(new Game());
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).clear();
        inOrder.verify(console).printLine("The TicTacToe Game");
        inOrder.verify(console).printBlankLine();
    }

    @Test
    void renderBoardShouldRenderEmptyBoard() {
        sut.renderBoard(new Game());
        verify(console, times(9)).print("   ");
        verify(console, times(6)).print("|");
        verify(console, times(3)).printLine();
        verify(console, times(2)).printLine("-----------");
    }

    @Test
    void renderBoardShouldRenderPlayerTurnAfterBoard() {
        sut.renderBoard(new Game());
        verify(console).printLine("Is Player O turn!");
    }

    @Test
    void renderWinner() {
        sut.renderWinner(Player.O);
        verify(console).printLine(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("is a winner!!!"));
    }

    @Captor
    private ArgumentCaptor<String> printCaptor;

    @ParameterizedTest
    @MethodSource("renderErrorClearSource")
    void renderErrorShouldClearPreviousInfoOnConsole(RuntimeException e) {
        sut.renderError(e);
        verify(console, times(1)).clear();
    }

    private static Stream<RuntimeException> renderErrorClearSource() {
        return Stream.of(
            new RuntimeException(),
            new Board.PlaceOutOfRangeException(),
            new Board.SpaceOccupiedException()
        );
    }

    @ParameterizedTest
    @MethodSource("renderErrorKnownSource")
    void renderErrorShouldRenderKnownBoardErrors(RuntimeException e) {
        sut.renderError(e);
        verify(console).printLine(printCaptor.capture());
        assertTrue(printCaptor.getValue().contains("Invalid move"));
    }

    private static Stream<RuntimeException> renderErrorKnownSource() {
        return Stream.of(
            new Board.PlaceOutOfRangeException(),
            new Board.SpaceOccupiedException()
        );
    }

    @Test
    void renderErrorShouldRenderUnknownErrors() {
        sut.renderError(new RuntimeException());
        verify(console).printLine(printCaptor.capture());
        assertEquals("Something went wrong", printCaptor.getValue());
    }

    @Test
    void getUserMoveShouldParseCliEntry() {
        when(console.readLine()).thenReturn("1,2");
        assertEquals(new Move(1, 2), sut.getUserMove());
    }
}