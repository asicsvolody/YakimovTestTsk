package ru.yakimovvn.tick_tack.game;

/**
 * Клас игрового процесса
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public class Game implements IGame{

    private GameBoard board;
    private Player firstPlayer;
    private Player secondPlayer;

    /**
     * Инициализация компонентов
     */
    @Override
    public void gameInit(){


        System.out.println("Игра крестики нолики.");

        this.board = new GameBoard(SystemInUtils.getInstance().askInt("Введите размер игрового поля от 2 до 20", 2, 20));

        if(SystemInUtils.getInstance().askTwoSymbols("Первый игрок человек? ", "Y", "N")){
            this.firstPlayer = getRealPlayer(false);
        }else{
            this.firstPlayer = new ComputerPlayer(Mark.DOT_X,  board);
        }

        if(SystemInUtils.getInstance().askTwoSymbols("Второй игрок человек? ", "Y", "N")){
            this.secondPlayer = getRealPlayer(firstPlayer instanceof RealPlayer);
        }else{
            this.secondPlayer = new ComputerPlayer(firstPlayer.getMark().getAnotherMark(),  board);
        }
    }


    /**
     * Игровой процесс
     */
    @Override
    public void startGame() {

        board.showMap();

        try {
            while (true) {
                gameStepOfPlayer(firstPlayer, 1);
                gameStepOfPlayer(secondPlayer, 2);
            }
        }catch (WinException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Метод хода игрока
     * @param player
     * @param numberOfPlayer
     * @throws WinException
     */
    public void gameStepOfPlayer(Player player, int numberOfPlayer) throws WinException {
        System.out.println("Ход игрока " + numberOfPlayer + " " + player.getMark().getMark());
        board.putMark(player.makeStep(), player.getMark());
        board.showMap();
        if(board.isSomebodyWin(player.getMark())){
            throw new WinException("Игрок " + numberOfPlayer + " победил!" );
        }

        if(board.isFull()){
            throw new WinException("Не осталось свободных клеток" );
        }
    }


    /**
     * Метод инициализации реального игрока
     * @param isFirstPlayerReal
     * @return
     */
    private Player getRealPlayer(boolean isFirstPlayerReal){
        Mark mark;
        if(!isFirstPlayerReal){
            mark = SystemInUtils.getInstance().askTwoSymbols("Выберите знак", "X", "O")? Mark.DOT_X: Mark.DOT_O;
            if(firstPlayer != null){
                firstPlayer.setMark(mark.getAnotherMark());
            }
        }else {
            System.out.println("Вам остался знак " + firstPlayer.mark.getMark());
            mark = firstPlayer.getMark().getAnotherMark();

        }
        return new RealPlayer(mark, board);
    }
}
