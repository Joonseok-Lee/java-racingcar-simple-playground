import domain.CarSetInitializer;
import domain.RacingGame;
import domain.rand.MovableImpl;
import view.InputView;
import view.ResultView;

public class RacingGameApplication {

    public static void run() {
        final String[] names = InputView.inputNames();
        final int turnCount = InputView.inputTurnCount();

        RacingGame racingGame = new RacingGame(CarSetInitializer.initCarSet(names), turnCount, new MovableImpl());

        ResultView.printGameStart();

        while(!racingGame.isFinished()) {
            racingGame.start();
            ResultView.printTurnResult(racingGame.getCarSet());
        }

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
