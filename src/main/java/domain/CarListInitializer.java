package domain;

import java.util.ArrayList;
import java.util.List;

public class CarSetInitializer {

    public static List<MovableCar> initCarList(String[] names) {
        List<MovableCar> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new MovableCar(name));
        }
        return cars;
    }
}
