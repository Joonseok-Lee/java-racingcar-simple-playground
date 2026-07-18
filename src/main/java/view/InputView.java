package view;

import domain.MovableCar;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    public static String[] initNames() {
        // 이름을 입력받음
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input = new Scanner(System.in).nextLine();

        // 입력값이 유효한지 검증
        // 내부 메소드로 분리함
        while(true) {
            if (initNames(input)) break;
        }

        return input.split(",");

    }

    public static Set<MovableCar> initCars(String[] names) {
        Set<MovableCar> cars = new HashSet<>();

        for(String name : names) {
            cars.add(new MovableCar(name));
        }

        return cars;
    }

    private static boolean initNames(String input) {
        try {
            namesInputValidation(input);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("입력값에는 공백이 포함될 수 없습니다.");
        }
        return false;
    }

    private static void namesInputValidation(String input) {
        if (input.contains(" "))
            throw new IllegalArgumentException();
    }

    public static int initTurnCount() {
        System.out.println("시도할 회수는 몇회인가요?");

        int turnCount;

        while(true) {
            try {
                turnCount = new Scanner(System.in).nextInt();
                if (turnCount < 0)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("입력값은 음이 아닌 정수만 가능합니다.");
            }
        }
        return turnCount;
    }
}
