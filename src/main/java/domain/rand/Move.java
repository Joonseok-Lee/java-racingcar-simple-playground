package domain.rand;

public abstract class Move {

    private final int threshold;

    public Move(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public abstract boolean isMovable(int value);
    public abstract int getRand();
}
