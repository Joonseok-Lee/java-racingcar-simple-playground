package fixed;

import domain.rand.Move;

public class FalseFixedMove implements Move {
    @Override
    public boolean isMovable() {
        return false;
    }
}
