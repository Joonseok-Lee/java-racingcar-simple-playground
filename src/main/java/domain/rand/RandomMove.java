package domain.rand;

import java.util.Random;

public class RandomMove implements Move {

    private final Random rand;

    public RandomMove(Random rand) {
        this.rand = rand;
    }

    @Override
    public boolean isMovable(int value) {
        return value >= 4;
    }

    @Override
    public int getRand() {
        return rand.nextInt(10);
    }
}
