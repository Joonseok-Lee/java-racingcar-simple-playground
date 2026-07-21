package fixed;

import domain.rand.Move;

public class FalseFixedMove implements Move {

    @Override
    public boolean isMovable(int value) {
        return value >= 4;
    }

    @Override
    public int getRand() {
        return 3;
    }
}
