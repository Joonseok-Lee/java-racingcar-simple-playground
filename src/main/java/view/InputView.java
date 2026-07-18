package view;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String[] inputNames() {
        // 이름을 입력받음
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input;

        // 입력값이 유효한지 검증
        // 내부 메소드로 분리함
        while(true) {
            input = scanner.nextLine();
            if (initNames(input)) break;
        }

        return input.split(",");

    }

    private boolean initNames(String input) {
        try {
            namesInputValidation(input);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("입력값에는 공백이 포함될 수 없습니다.");
        }
        return false;
    }

    private void namesInputValidation(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException();
        }
    }

    public int inputTurnCount() {
        // 턴 수를 입력받음
        System.out.println("시도할 회수는 몇회인가요?");
        int turnCount;

        // 턴 수 검증
        // 내부 메소드로 분리함
        while(true) {
            turnCount = scanner.nextInt();
            if (initTurnCount(turnCount)) break;
        }
        return turnCount;
    }

    private boolean initTurnCount(int turnCount) {
        try {
            turnCountInputValidation(turnCount);
            return true;
        } catch (InputMismatchException e) {
            System.out.println("입력값은 음이 아닌 정수만 가능합니다.");
        }
        return false;
    }

    private void turnCountInputValidation(int turnCount) {
        if (turnCount < 0)
            throw new InputMismatchException();
    }
}
