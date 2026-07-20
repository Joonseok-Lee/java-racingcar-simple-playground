package domain.rand;

import java.util.Random;

public class RandomMove implements Move {

    @Override
    public int isMovable() {
        return new Random().nextInt(10);
    }
}
