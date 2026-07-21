package domain.rand;

import java.util.Random;

public class RandomMove extends Move {

    private final Random rand;

    public RandomMove(int  threshold) {
        super(threshold);
        this.rand = new Random();
    }

    @Override
    public boolean isMovable(int value) {
        return value >= super.getThreshold();
    }

    @Override
    public int getRandValue() {
        return rand.nextInt(10);
    }
}
