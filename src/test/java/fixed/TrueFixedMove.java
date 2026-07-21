package fixed;

import domain.rand.Move;

public class TrueFixedMove extends Move {

    public TrueFixedMove(int threshold) {
        super(threshold);
    }

    @Override
    public boolean isMovable(int value) {
        return value >= super.getThreshold();
    }

    @Override
    public int getRandValue() {
        return 4;
    }
}
