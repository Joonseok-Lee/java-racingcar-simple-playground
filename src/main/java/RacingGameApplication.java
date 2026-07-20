import domain.CarListInitializer;
import domain.RacingGame;
import domain.rand.RandomMove;
import view.InputView;
import view.ResultView;

public class RacingGameApplication {

    public static void run() {
        InputView inputView = new InputView(System.in);

        final String[] names = inputView.inputNames();
        final int turnCount = inputView.inputTurnCount();

        RacingGame racingGame = new RacingGame(CarListInitializer.initCarList(names), turnCount, new RandomMove());

        ResultView.printGameStart();

        while (!racingGame.isFinished()) {
            racingGame.start();
            ResultView.printTurnResult(racingGame.getCarList());
        }

        ResultView.printFinalResult(racingGame.getWinnerNames());
    }
}
