import java.util.Random;

public class MovableCar {

    private String name;
    private int distance;

    public MovableCar(String name) {
        this.name = name;
        this.distance = 0;
    }

    public String getName() { return name; }

    private boolean isMovable() {
        return new Random().nextInt(10) >= 4;
    }

    public void move() {
        if (isMovable()) {
            this.distance += 1;
        }
    }
}
