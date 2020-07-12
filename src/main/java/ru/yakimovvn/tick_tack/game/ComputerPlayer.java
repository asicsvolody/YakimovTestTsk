package ru.yakimovvn.tick_tack.game;

import java.util.Random;

/**
 * Класс с интеллектом компьютера
 * При анализе каждой клетке присваивается очки.
 * Компбьютер ходит на клетку с наибольшим количеством очков
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class ComputerPlayer extends Player {

    private Mark opponentMark;

    public ComputerPlayer(Mark mark, GameBoard board) {
        super(mark, board);
        this.opponentMark = mark.getAnotherMark();
    }

    /**
     * Публичный метод возвращающий результат анализа игрового поля
     * Сначала компьютер Проверяет может ли он произвести победный ход.
     * Далее проверяет может ли произвести побендный ход соперник.
     * Если это первый его ход то он занимает центр, если центр занят занимает Random клетку
     * В противном случает отдается наилучший по очкам результат клетки
     * @return
     */
    @Override
    public int makeStep() {

        Step resStep = new Step(-1, 0);
        Step tmpStep;

        tmpStep = cycleAnalysisField(mark.getMark());
        resStep.setIfMore(tmpStep);

        if(resStep.getStepPoints() < board.getSize()){
            tmpStep = cycleAnalysisField(opponentMark.getMark());
            if(tmpStep.getStepPoints() == board.getSize()){
                resStep.setIfMore(tmpStep);
            }
        }

        if(resStep.getStepPoints() == 1){
            resStep.setIfMore(firstComputerStep());
        }

        return resStep.getStep();
    }

    /**
     * Метод запускающий анализ игрового поля (горизонтальный, вертикальны, и две диагонали)
     * @param playerSymbol
     * @return
     */
    private Step cycleAnalysisField(char playerSymbol){

        Step resStep = new Step(-1, 0);

        for (int i = 0; i < board.getCountPoints(); i++) {

            if(board.isPointEmpty(i)){

                resStep.setIfMore(i, verticalCheck(i,playerSymbol));

                resStep.setIfMore(i, horizontalCheck(i,playerSymbol));

                resStep.setIfMore(i, upDownDiagonal(i,playerSymbol));

                resStep.setIfMore(i, downUpDiagonal(i,playerSymbol));
            }
        }
        return resStep;
    }

    /**
     * Метод выбора клетки для первого хода
     * @return
     */
    private Step firstComputerStep(){

        int centralPoint = board.getCountPoints() / 2;

        int firstComputerStep = board.isPointEmpty(centralPoint) ? centralPoint : getRandomPoint();

        return new Step(firstComputerStep, 10);
    }

    /**
     * Метод возвращающий Random значение
     * @return
     */
    private int getRandomPoint(){
        Random random = new Random();
        int randomInt;

        do{
            randomInt = random.nextInt(board.getCountPoints() - 1);

        }while(!board.isPointEmpty(randomInt));

        return randomInt;
    }


    /**
     * Метод анализа вертикальных линий
     * @param indexButton
     * @param playerSymbol
     * @return
     */
    private int verticalCheck(int indexButton, char playerSymbol){
        int howManySymbolsInLine=1;
        int howManyButtons = 1;

        for (int i = (indexButton - board.getSize()); i >= 0; i -= board.getSize()) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManySymbolsInLine++;
        }

        for (int i = (indexButton + board.getSize()); i < board.getCountPoints(); i += board.getSize()) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol))
                howManySymbolsInLine++;
        }

        howManySymbolsInLine = (howManyButtons == board.getSize()) ? howManySymbolsInLine:0;

        return howManySymbolsInLine;
    }


    /**
     * Метод анализа горизонтальных линий
     * @param indexButton
     * @param playerSymbol
     * @return
     */
    private int horizontalCheck(int indexButton,char playerSymbol){

        int howManyTrue=1;
        int howManyButtons = 1;

        for (int i = indexButton-1; i>=0 && i % board.getSize() != board.getSize() - 1 ; i --) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManyTrue++;
        }

        for (int i = indexButton + 1; i < board.getCountPoints() && i % board.getSize() != 0;
             i++) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManyTrue++;
        }
        howManyTrue = (howManyButtons == board.getSize()) ? howManyTrue : 0;

        return howManyTrue;
    }


    /**
     * Метод анализа диагонали сверху в низ
     * @param indexButton
     * @param playerSymbol
     * @return
     */
    private int upDownDiagonal(int indexButton, char playerSymbol){
        int howManyTrue=1;
        int howManyButtons = 1;

        for (int i = indexButton - (board.getSize() +1); i >=0 ; i -= (board.getSize() +1)) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManyTrue++;

        }
        for (int i = indexButton + (board.getSize() + 1); i < board.getCountPoints();
             i+=(board.getSize() + 1)) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManyTrue++;

        }
        howManyTrue=(howManyButtons == board.getSize()) ? howManyTrue:0;

        return howManyTrue;
    }

    /**
     * Метод анализа диагонали снизу вверх
     * @param indexButton
     * @param playerSymbol
     * @return
     */
    private int downUpDiagonal(int indexButton, char playerSymbol){
        int howManyTrue=1;
        int howManyButtons = 1;

        for (int i = indexButton - (board.getSize() - 1); i > 0
                && i % board.getSize() == (i+ board.getSize() - 1) % board.getSize() + 1;
             i -= (board.getSize() - 1)) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol)) howManyTrue++;
        }

        for (int i = indexButton +(board.getSize() - 1);
             i < board.getCountPoints()
                     && i % board.getSize() == (i- board.getSize() + 1) % board.getSize() - 1;
             i += (board.getSize() - 1)) {
            howManyButtons++;
            if(board.isCurrentPlayerSymbol(i, playerSymbol))
                howManyTrue++;
        }
        howManyTrue = (howManyButtons == board.getSize()) ? howManyTrue:0;

        return howManyTrue;
    }


    /**
     * Приватный класс для использования при анализе клеток
     * Каждой клетки назначается количетсво очков
     */
    private static class Step {
        private int step;
        private int stepPoints;

        public Step(int step, int stepPoints) {
            this.step = step;
            this.stepPoints = stepPoints;
        }

        public int getStep() {
            return step;
        }

        public int getStepPoints() {
            return stepPoints;
        }

        public void setIfMore(Step step){
            setIfMore(step.getStep(), step.getStepPoints());
        }

        public void setIfMore(int step, int stepPoints){
            if(this.stepPoints < stepPoints){
                this.step = step;
                this.stepPoints = stepPoints;
            }
        }
    }


}
