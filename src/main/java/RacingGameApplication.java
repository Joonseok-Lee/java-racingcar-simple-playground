import controller.RacingGameController;
import domain.rand.Move;
import domain.rand.RandomMove;
import view.InputView;

import java.util.Random;

public class RacingGameApplication {

    public static void run() {
        InputView inputView = new InputView(System.in);
        Move move = new RandomMove(4);

        RacingGameController racingGameController = new RacingGameController(inputView, move);
        racingGameController.gameStart();
    }
}
