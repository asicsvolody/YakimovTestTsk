package ru.yakimovvn.tick_tack.game;

import java.util.Scanner;

/**
 * Класс игрока
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class RealPlayer extends Player {

    public RealPlayer(Mark mark, GameBoard board) {
        super(mark, board);
    }


    /**
     * Метод опрашивающий игрока о ходе
     * @return
     */
    @Override
    public int makeStep() {

        int resPoint;

        while(true){

            int x = SystemInUtils.getInstance().askInt("Введите Х", 1 , board.getSize());
            int y = SystemInUtils.getInstance().askInt("Введите Y", 1 , board.getSize());

            if(x <= 0 && y <= 0 && x > board.getSize() && y > board.getSize() ){
                System.out.println("Данные введены не корректоно. Попробуйте еще раз.");
                continue;
            }

            resPoint = board.getSize() * --y + --x;
            if(board.isPointEmpty(resPoint)){
                break;
            }
        }
        return resPoint;
    }

}
