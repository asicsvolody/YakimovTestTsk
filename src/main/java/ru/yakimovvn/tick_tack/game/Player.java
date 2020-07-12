package ru.yakimovvn.tick_tack.game;

/**
 * Абстактный класс игрока
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public abstract class Player {

    protected Mark mark;
    protected GameBoard board;


    public Player(Mark mark, GameBoard board) {
        this.mark = mark;
        this.board = board;
    }

    public abstract int makeStep();

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
