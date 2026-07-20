package controller;

import domain.CarListInitializer;
import domain.RacingGame;
import domain.rand.Move;
import view.InputView;
import view.ResultView;

public class RacingGameController {

    private final InputView inputView;
    private final Move move;

    public RacingGameController(InputView inputView, Move move) {
        this.inputView = inputView;
        this.move = move;
    }

    private RacingGame gameSetUp() {
        String[] names = inputView.inputNames();
        int turnCount = inputView.inputTurnCount();

        return new RacingGame(CarListInitializer.initCarList(names), turnCount, move);
    }

    public void gameStart() {

        RacingGame racingGame = gameSetUp();

        play(racingGame);

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }

    private void play(RacingGame racingGame) {
        ResultView.printGameStart();

        while (!racingGame.isFinished()) {
            racingGame.start();
            ResultView.printTurnResult(racingGame.getCarList());
        }
    }
}
