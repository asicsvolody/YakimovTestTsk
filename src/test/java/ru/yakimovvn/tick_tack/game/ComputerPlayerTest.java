package ru.yakimovvn.tick_tack.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {

    private final int SIZE = 3;
    private Player player;
    private GameBoard board;

    @Before
    public void init(){
        board = new GameBoard(SIZE);
        player = new ComputerPlayer(Mark.DOT_X, board);
    }

    @Test
    public void makeFirstStepCentre() {
        board.putMark(player.makeStep(), player.getMark());
        assertFalse(board.isPointEmpty(SIZE * SIZE / 2));
    }

    @Test
    public void makeStepForWin() {

        for (int i = 0; i < SIZE - 1 ; i++) {
            board.putMark(i, player.getMark());
        }
        assertEquals(SIZE - 1, player.makeStep());
    }

    @Test
    public void makeStepForOpponentNotWin() {

        for (int i = 0; i < SIZE - 1 ; i++) {
            board.putMark(i, player.getMark().getAnotherMark());
        }
        assertEquals(SIZE - 1, player.makeStep());
    }
}