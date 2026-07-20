package domain;

public class MovableCar {

    private final String name;
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

    public void move(boolean movable) {
        if (movable) {
            this.distance += 1;
        }
    }
}
