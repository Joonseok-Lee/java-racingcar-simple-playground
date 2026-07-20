package controller;

import domain.CarListInitializer;
import domain.RacingGame;
import domain.rand.Move;
import domain.rand.RandomMove;
import view.InputView;
import view.ResultView;

public class RacingGameController {

    private final InputView inputView;
    private final RacingGame racingGame;

    public RacingGameController(InputView inputView, Move move) {
        this.inputView = inputView;
        this.racingGame = gameSetUp(move);
    }

    private RacingGame gameSetUp(Move move) {
        String[] names = inputView.inputNames();
        int turnCount = inputView.inputTurnCount();

        return new RacingGame(CarListInitializer.initCarList(names), turnCount, move);
    }

    public void gameStart() {

        ResultView.printGameStart();

        while (!racingGame.isFinished()) {
            racingGame.start();
            ResultView.printTurnResult(racingGame.getCarList());
        }

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
