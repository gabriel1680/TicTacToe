package com.tictactoe.view.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleTest {

    @Mock
    private Scanner scanner;

    @Mock
    private PrintStream out;

    @InjectMocks
    private Console sut;

    @Test
    void printBlankLine() {
        sut.printBlankLine();
        verify(out).println("\n");
    }

    @Test
    void printEmptyLine() {
        sut.printLine();
        verify(out).println();
    }

    @Test
    void printLine() {
        sut.printLine("a");
        verify(out).println("a");
    }

    @Test
    void print() {
        sut.print("x");
        verify(out).print("x");
    }

    @Test
    void clear() {
        sut.clear();
        verify(out).print("\033c");
        verify(out).flush();
    }

    @Test
    void readLine() {
        sut.readLine();
        verify(scanner).nextLine();
    }
}