import java.util.Random;

public class MovableCar {

    private String name;

    public MovableCar(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    private boolean isMovable() {
        return new Random().nextInt(10) >= 4;
    }
}
