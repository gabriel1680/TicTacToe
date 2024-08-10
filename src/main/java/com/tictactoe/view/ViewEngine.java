package com.tictactoe.view;

import com.tictactoe.game.Game;
import com.tictactoe.game.Player;

public interface ViewEngine {
    void renderBoard(Game game);
    void renderWinner(Player player);
    void renderError(RuntimeException e);
    Move getUserMove();
}
