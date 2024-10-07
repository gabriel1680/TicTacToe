package com.tictactoe.view.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {

    private final Scanner scanner;
    private final PrintStream out;

    public Console(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    public void printLine(String s) {
        out.println(s);
    }

    public void printBlankLine() {
        out.println("\n");
    }

    public void printLine() {
        out.println();
    }

    public void print(String s) {
        out.print(s);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void clear() {
        out.print("\033c");
        out.flush();
    }
}
