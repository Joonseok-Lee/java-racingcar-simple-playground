package view;

import domain.MovableCar;
import domain.RacingGame;

import java.util.List;
import java.util.Set;

public class ResultView {

    public static void printInGameResult(RacingGame racingGame) {
        Set<MovableCar> cars = racingGame.getCarSet();
        System.out.println("실행 결과");

        for(int i = 0; i <= racingGame.getTurn(); i++) {
            racingGame.start();
            printTurnResult(cars);

        }
    }

    private static void printTurnResult(Set<MovableCar> cars) {
        for(MovableCar car : cars) {
            System.out.println(car.getName() + " : " + car.getDistanceToString());
        }
        System.out.println();
    }

    public static void printFinalResult(List<String> winnerNames) {
        System.out.println(String.join(", ", winnerNames) + "가 최종 우승했습니다.");
    }
}
