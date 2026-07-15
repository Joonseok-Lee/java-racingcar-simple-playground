package view;

import domain.MovableCar;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    public static Set<MovableCar> initCars() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");

        String input;

        while(true) {
            input = new Scanner(System.in).nextLine();
            try {
                if (input.contains(" "))
                    throw new IllegalArgumentException();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("입력값에는 공백이 포함될 수 없습니다.");
            }
        }

        String[] names = input.split(",");

        Set<MovableCar> cars = new HashSet<>();

        for(String name : names) {
            cars.add(new MovableCar(name));
        }

        return cars;
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
