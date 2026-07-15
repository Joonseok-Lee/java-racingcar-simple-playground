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

    @Test
    @DisplayName("턴 수가 0인 경우")
    void ifTurnIsZero() {
        // given
        RacingGame game = new RacingGame(Set.of(new MovableCar("1st"), new MovableCar("2nd"), new MovableCar("3rd")), 0, new MovableImpl());

        // when
        game.start();

        // then
        assertThat(game.getWinnerNames()).contains("1st", "2nd", "3rd");
    }
}
