import domain.MovableCar;
import domain.RacingGame;
import domain.rand.MovableImpl;
import view.InputView;
import view.ResultView;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<MovableCar> cars = InputView.initCars();
        final int turnCount = InputView.initTurnCount();

        RacingGame racingGame = new RacingGame(cars, turnCount, new MovableImpl());
        racingGame.start();

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
