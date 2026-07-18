package domain;

import java.util.HashSet;
import java.util.Set;

public class CarSetInitializer {

    public static Set<MovableCar> initCarSet(String[] names) {
        Set<MovableCar> cars = new HashSet<>();

        for(String name : names) {
            cars.add(new MovableCar(name));
        }
        return cars;
    }
}
