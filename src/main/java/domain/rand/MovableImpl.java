package domain.rand;

import java.util.Random;

public class MovableImpl implements Movable {

    @Override
    public int isMovable() {
        return new Random().nextInt(10);
    }
}
