package ru.yakimovvn.tick_tack.game;

/**
 * Exception бросается при победе одного из игроков
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class WinException extends Exception {

    public WinException(String message) {
        super(message);
    }
}
