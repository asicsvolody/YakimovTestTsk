package ru.yakimovvn.tick_tack.game;

/**
 * Класс игрового поля
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class GameBoard {

    private int size;
    private char[][] board;

    public GameBoard(int size) {
        this.size = size;
        this.board = new char[size][size];
        clearBoard();
    }


    /**
     * Очистка поля
     */
    public void clearBoard(){

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.DOT_EMPTY.getMark();
            }
        }
    }


    /**
     * Печать поля
     */
    public void showMap(){

        for (int i = 0; i <= size; i++) {
            System.out.print( i + "\t");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((i+1)+"\t");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     * Установка точки
     * @param point
     * @param mark
     * @return
     */
    public boolean putMark(int point, Mark mark){

        if(!isPointEmpty(point))
            return false;

        int x = point / size;
        int y = point % size;
        board[y][x] = mark.getMark();
        return true;

    }


    /**
     * Проверка свободна ли точка
     * @param point
     * @return
     */
    public boolean isPointEmpty(int point){
        int x = point / size;
        int y = point % size;

        return board[y][x] == Mark.DOT_EMPTY.getMark();
    }


    /**
     * Проверка есть ли выйгровшие
     * @param mark
     * @return
     */
    public boolean isSomebodyWin(Mark mark){
        return isSameWinDiagonals(mark.getMark()) || isSameWinLines(mark.getMark());
    }


    /**
     * Приватный метод проверки есть ли выйгровшие по диагонали
     * @param playerSymbol
     * @return
     */
    private boolean isSameWinDiagonals(char playerSymbol){
        boolean isUpDiagonalWin = true, isDownDiagonalWin = true;
        for (int i = 0; i < size ; i++) {
            isUpDiagonalWin &= board[i][i]== playerSymbol;
            isDownDiagonalWin &= board[board.length-1-i][i] ==playerSymbol;
        }
        return isUpDiagonalWin || isDownDiagonalWin;
    }


    /**
     * Приватный метод проверка есть ли выйгровшие по линии
     * @param playerSymbol
     * @return
     */
    private boolean isSameWinLines(char playerSymbol){

        boolean  isColsWin=true, isRollsWin=true;

        for (int col = 0; col < size ; col++) {
            isColsWin=true;
            isRollsWin=true;
            for (int rol = 0; rol < size; rol++) {
                isColsWin &= board[col][rol] == playerSymbol;
                isRollsWin &= board[rol][col] == playerSymbol;
            }
            if(isColsWin || isRollsWin) break;
        }
        return isColsWin || isRollsWin;
    }


    /**
     * Метод проверки заполнено ли поле
     * @return
     */
    public boolean isFull(){
        boolean result =true;
        outputCycle:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] == Mark.DOT_EMPTY.getMark()){
                    result=false;
                    break outputCycle;
                }
            }
        }
        return result;
    }


    /**
     * Метод проверки занята ли точка указанным сиволом
     * @param indexButton
     * @param playerSymbol
     * @return
     */
    public boolean isCurrentPlayerSymbol(int indexButton, char playerSymbol){

        int x = indexButton / size;
        int y = indexButton % size;
        return board[y][x] == playerSymbol;
    }


    public int getSize() {
        return size;
    }

    public int getCountPoints(){
        return size * size;
    }
}
