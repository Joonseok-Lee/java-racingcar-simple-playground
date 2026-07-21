package fixed;

import domain.rand.Move;

public class FalseFixedMove extends Move {

    public FalseFixedMove(int threshold) {
        super(threshold);
    }

    @Override
    public boolean isMovable(int value) {
        return value >= super.getThreshold();
    }

    @Override
    public int getRandValue() {
        return 3;
    }
}
