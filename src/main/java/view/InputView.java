package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static List<String> initPlayerName() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] names = new Scanner(System.in).nextLine().split(",");
        return Arrays.asList(names);
    }

    public static int initTurnCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        return new Scanner(System.in).nextInt();
    }
}
