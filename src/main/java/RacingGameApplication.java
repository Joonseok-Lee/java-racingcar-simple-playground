import controller.RacingGameController;
import domain.CarListInitializer;
import domain.RacingGame;
import domain.rand.Move;
import domain.rand.RandomMove;
import view.InputView;
import view.ResultView;

public class RacingGameApplication {

    public static void run() {
        InputView inputView = new InputView(System.in);
        Move move = new RandomMove();

        RacingGameController racingGameController = new RacingGameController(inputView, move);
        racingGameController.gameStart();
    }
}
