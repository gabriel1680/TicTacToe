package com.tictactoe.view.cli;

import java.util.Scanner;

public class TestableConsole extends Console {

    public String printlnCalledWith;
    public String doPrintCalledWith;

    public TestableConsole(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void println(String s) {
        printlnCalledWith = s;
    }

    @Override
    protected void doPrint(String s) {
        doPrintCalledWith = s;
    }
}
