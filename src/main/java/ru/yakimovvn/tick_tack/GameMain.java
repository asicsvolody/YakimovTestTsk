package ru.yakimovvn.tick_tack;

import ru.yakimovvn.tick_tack.game.Game;
import ru.yakimovvn.tick_tack.game.IGame;
import ru.yakimovvn.tick_tack.game.SystemInUtils;

/**
 * Класс с главным методом
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class GameMain {

    public static void main(String[] args) {
        IGame game = new Game();

        game.gameInit();
        game.startGame();

        SystemInUtils.getInstance().close();
    }
}
