package ru.yakimovvn.tick_tack.game;

/**
 * Exception бросается если ввведеное число находится не в диапозоне
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class NumberNotBetweenException extends Exception {

    public NumberNotBetweenException(String message) {
        super(message);
    }
}
