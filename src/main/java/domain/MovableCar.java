package domain;

import java.util.Random;

public class MovableCar {

    private String name;
    private int distance;

    public int getDistance() {
        return distance;
    }

    public String getDistanceToString() {
        return "-".repeat(distance);
    }

    public MovableCar(String name) {
        this.name = name;
        this.distance = 0;
    }

    public String getName() { return name; }

    public void move(int movable) {
        if (movable >= 4) {
            this.distance += 1;
        }
    }
}
