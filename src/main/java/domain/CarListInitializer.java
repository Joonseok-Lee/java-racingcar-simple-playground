package domain;

import java.util.ArrayList;
import java.util.List;

public class CarListInitializer {

    public static List<RacingCar> initCarList(String[] names) {
        List<RacingCar> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new RacingCar(name));
        }
        return cars;
    }
}
