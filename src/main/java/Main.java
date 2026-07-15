import view.InputView;

public class Main {

    public static void main(String[] args) {
        RacingGame racingGame = new RacingGame(new InputView());
        racingGame.start();
    }
}
