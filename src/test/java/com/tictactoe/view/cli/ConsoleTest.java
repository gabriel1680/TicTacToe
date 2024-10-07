package com.tictactoe.view.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private TestableConsole sut;

    @Test
    void printBlankLine() {
        sut.printBlankLine();
        assertEquals("\n", sut.printlnCalledWith);
    }

    @Test
    void printEmptyLine() {
        sut.printLine();
        assertEquals("", sut.printlnCalledWith);
    }

    @Test
    void printLine() {
        sut.printLine("a");
        assertEquals("a", sut.printlnCalledWith);
    }

    @Test
    void print() {
        sut.print("x");
        assertEquals("x", sut.doPrintCalledWith);
    }

    @Test
    void clear() {
        sut.clear();
        assertEquals("\033c", sut.printlnCalledWith);
        assertNull(sut.doPrintCalledWith);
    }

    @Test
    void readLine() {
        sut.readLine();
        verify(scanner).nextLine();
    }
}