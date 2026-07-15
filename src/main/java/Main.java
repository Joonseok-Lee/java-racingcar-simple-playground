import view.InputView;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final List<String> carNames = InputView.initCarName();
        final int turnCount = InputView.initTurnCount();

        RacingGame racingGame = new RacingGame(carNames, turnCount);
        racingGame.start();
    }
}
