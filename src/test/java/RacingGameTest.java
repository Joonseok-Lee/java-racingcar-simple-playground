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

    @Test
    @DisplayName("임의로 2칸 간 차량과 1칸 간 차량이 있는 경우")
    void winnerIsMoveTwice() {
        // given
        MovableCar move2 = new MovableCar("move_2");
        MovableCar move1 = new MovableCar("move_1");

        move2.move(4);
        move2.move(4);
        move1.move(4);

        // when
        RacingGame game = new RacingGame(Set.of(move1, move2), 0, () -> 4);
        game.start();

        // then
        assertThat(game.getWinnerNames()).containsExactly("move_2");
    }
}
