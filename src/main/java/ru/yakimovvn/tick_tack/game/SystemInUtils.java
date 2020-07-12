package ru.yakimovvn.tick_tack.game;

import java.util.Scanner;

/**
 * Singleton класс
 * Набор системных утилит для взаимодействия с пользователем
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class SystemInUtils {


    private static SystemInUtils instance;

    private Scanner in;

    public static SystemInUtils getInstance()
    {
        if (instance == null)
        {
            synchronized (SystemInUtils.class)
            {
                if(instance==null)
                {
                    instance = new SystemInUtils();
                }

            }
        }
        return instance;
    }


    /**
     * Опрашивает пользователя число в деопозоне
     * @param msg
     * @param from
     * @param to
     * @return
     */
    public int askInt(String msg, int from, int to){

        if(in == null){
            in = new Scanner(System.in);
        }

        int answer;
        while (true){
            System.out.println(msg);
            try {
                answer = in.nextInt();
                if(answer < from || answer > to){
                    throw new NumberNotBetweenException("Число должно быть в ромежутке между " + from + " и " + to);
                }
                break;
            }catch (NumberNotBetweenException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println("Данные введены не корректно попробуйте еще раз.");
            }
            in.nextLine();
        }

        return answer;
    }


    /**
     * Порашивает пользователя по двум символам
     * @param msg
     * @param trueSymbol
     * @param falseSymbol
     * @return
     */
    public boolean askTwoSymbols(String msg, String trueSymbol, String falseSymbol){

        if(in == null){
            in = new Scanner(System.in);
        }

        trueSymbol = trueSymbol.toUpperCase();
        falseSymbol = falseSymbol.toUpperCase();
        System.out.print(msg + " ("+ trueSymbol +" / "+ falseSymbol +")? ");


        while (true) {
            String answer = in.nextLine();
            if (trueSymbol.equals(answer.toUpperCase())) {
                System.out.println();
                return true;
            }
            if (falseSymbol.equals(answer.toUpperCase())) {
                System.out.println();
                return false;
            }
            System.out.println("\nПопробуйте еще раз");
        }
    }


    /**
     * Метод закрывает ресурсы
     */
    public void close(){
        if(in != null){
            in.close();
        }
        instance = null;
    }
}
