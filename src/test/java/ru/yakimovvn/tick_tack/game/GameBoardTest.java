package ru.yakimovvn.tick_tack.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    private final int SIZE = 3;
    private GameBoard board;
    private final Mark MARK = Mark.DOT_X;
    private final int POINT= 0;


    @Before
    public void init(){
        board = new GameBoard(SIZE);
    }

    @Test
    public void putMark() {
        board.putMark(POINT, MARK);
        assertFalse(board.isPointEmpty(POINT));
    }

    @Test
    public void isPointEmpty() {
        assertTrue(board.isPointEmpty(POINT));
    }

    @Test
    public void isSomebodyWinTrue() {
        for (int i = 0; i < SIZE; i++) {
            board.putMark(i, MARK);
        }
        assertTrue(board.isSomebodyWin(MARK));
    }

    @Test
    public void isSomebodyWinFalse() {
        assertFalse(board.isSomebodyWin(MARK));
    }

    @Test
    public void isFullTrue() {
        for (int i = 0; i < SIZE * SIZE ; i++) {
            board.putMark(i, MARK);
        }
        assertTrue(board.isFull());
    }

    @Test
    public void isFullFalse() {
        assertFalse(board.isFull());
    }

    @Test
    public void isCurrentPlayerSymbol() {
        board.putMark(POINT, MARK);
        assertTrue(board.isCurrentPlayerSymbol(POINT, MARK.getMark()));
    }
}