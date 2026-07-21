package fixed;

import domain.rand.Move;

public class TrueFixedMove implements Move {

    @Override
    public boolean isMovable(int value) {
        return true;
    }

    @Override
    public int getRand() {
        return 4;
    }
}
