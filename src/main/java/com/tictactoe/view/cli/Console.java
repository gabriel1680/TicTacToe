package com.tictactoe.view.cli;

import java.util.Scanner;

public class Console {

    private final Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printLine(String s) {
        println(s);
    }

    public void printBlankLine() {
        println("\n");
    }

    public void printLine() {
        println("");
    }

    protected void println(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        doPrint(s);
    }

    protected void doPrint(String s) {
        System.out.print(s);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void clear() {
        println("\033c");
        System.out.flush();
    }
}
