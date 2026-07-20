package fixed;

import domain.rand.Move;

public class TrueFixedMove implements Move {
    @Override
    public boolean isMovable() {
        return true;
    }
}
