package ru.yakimovvn.tick_tack.game;

/**
 * Enum символов
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public enum Mark {
    DOT_EMPTY('.'),
    DOT_X('X'),
    DOT_O('O');

    private char mark;

    Mark(char cMark){
        this.mark = cMark;
    }

    public char getMark() {
        return mark;
    }

    public Mark getAnotherMark(){
        return this.equals(DOT_X) ? DOT_O : DOT_X;
    }
}
