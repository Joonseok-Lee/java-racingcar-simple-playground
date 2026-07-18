import domain.CarSetInitializer;
import domain.MovableCar;
import domain.RacingGame;
import domain.rand.MovableImpl;
import view.InputView;
import view.ResultView;

import java.util.Set;

public class RacingGameApplication {

    public static void run() {
        final String[] names = InputView.initNames();
        final int turnCount = InputView.initTurnCount();

        RacingGame racingGame = new RacingGame(CarSetInitializer.initCarSet(names), turnCount, new MovableImpl());

        ResultView.printGameStart();

        while(!racingGame.isFinished()) {
            racingGame.start();
            ResultView.printTurnResult(racingGame.getCarSet());
        }

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
