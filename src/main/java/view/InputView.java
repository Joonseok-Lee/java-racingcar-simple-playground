package view;

import domain.MovableCar;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    public static Set<MovableCar> initCars() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] names = new Scanner(System.in).nextLine().split(",");

        Set<MovableCar> cars = new HashSet<>();

        for(String name : names) {
            cars.add(new MovableCar(name));
        }

        return cars;
    }

    public static int initTurnCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        return new Scanner(System.in).nextInt();
    }
}
