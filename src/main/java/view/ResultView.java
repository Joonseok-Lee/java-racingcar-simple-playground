package view;

import java.util.List;

public class ResultView {

    public static void printResult(List<String> winnerNames) {
        System.out.println(String.join(", ", winnerNames) + "가 최종 우승했습니다.");
    }
}
