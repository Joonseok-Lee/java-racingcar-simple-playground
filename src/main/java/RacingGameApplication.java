import domain.MovableCar;
import domain.RacingGame;
import domain.rand.MovableImpl;
import view.InputView;
import view.ResultView;

import java.util.Set;

public class RacingGameApplication {

    public static void run() {
        final Set<MovableCar> cars = InputView.initCars();
        final int turnCount = InputView.initTurnCount();

        RacingGame racingGame = new RacingGame(cars, turnCount, new MovableImpl());

        ResultView.printInGameResult(racingGame);
        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
