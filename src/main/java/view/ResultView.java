package view;

import domain.MovableCar;

import java.util.List;
import java.util.Set;

public class ResultView {

    public static void printTurnResult(Set<MovableCar> cars) {

    }

    public static void printFinalResult(List<String> winnerNames) {
        System.out.println(String.join(", ", winnerNames) + "가 최종 우승했습니다.");
    }
}
