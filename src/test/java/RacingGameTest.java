import domain.MovableCar;
import domain.RacingGame;
import domain.rand.MovableImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {

    @Test
    @DisplayName("차가 1대인 경우")
    void ifSingleCar() {
        // given
        RacingGame game = new RacingGame(Set.of(new MovableCar("single")), 3, new MovableImpl());

        // when
        game.start();

        // then
        assertThat(game.getWinnerNames()).containsExactly("single");
    }
}
