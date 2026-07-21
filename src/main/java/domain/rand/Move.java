package domain.rand;

public abstract class Move {

    private final int threshold;

    public Move(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean isMovable(int value) {
        return value >= threshold;
    }

    public abstract int getRandValue();
}
