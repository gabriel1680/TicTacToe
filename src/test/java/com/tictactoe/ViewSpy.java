package com.tictactoe;

import com.tictactoe.game.Game;
import com.tictactoe.game.Player;
import com.tictactoe.view.Move;
import com.tictactoe.view.ViewEngine;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ViewSpy implements ViewEngine {
    public Queue<Move> moves;
    public Player winner;
    public RuntimeException exception;

    public ViewSpy() {
        moves = new LinkedList<>();
    }

    public void setMoves(List<Move> moveList) {
        moves.addAll(moveList);
    }

    @Override
    public void renderBoard(Game game) {
        // noop
    }

    @Override
    public void renderWinner(Player player) {
        winner = player;
    }

    @Override
    public void renderError(RuntimeException e) {
        exception = e;
    }

    @Override
    public Move getUserMove() {
        return moves.poll();
    }
}
