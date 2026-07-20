import domain.CarSetInitializer;
import domain.MovableCar;
import domain.RacingGame;
import domain.rand.MovableImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {

    @Test
    @DisplayName("차가 1대인 경우")
    void ifSingleCar() {
        // given
        String[] names = { "single" };
        RacingGame game = new RacingGame(CarSetInitializer.initCarSet(names), 3, new MovableImpl());

        // when
        while(!game.isFinished()) {
            game.start();
        }

        // then
        assertThat(game.getWinnerNames()).containsExactly("single");
    }

    @Test
    @DisplayName("턴 수가 0인 경우")
    void ifTurnIsZero() {
        // given
        String[] names = { "1st", "2nd", "3rd" };
        RacingGame game = new RacingGame(CarSetInitializer.initCarSet(names), 0, new MovableImpl());

        // when
        while(!game.isFinished()) {
            game.start();
        }

        // then
        assertThat(game.getWinnerNames()).contains("1st", "2nd", "3rd");
    }

    @Test
    @DisplayName("임의로 2칸 간 차량과 1칸 간 차량이 있는 경우")
    void winnerIsMoveTwice() {
        // given

        MovableCar move2 = new MovableCar("move_2");
        MovableCar move1 = new MovableCar("move_1");

        // move2 is moving twice
        move2.move(4);
        move2.move(4);

        //move1 is moving once
        move1.move(4);

        Set<MovableCar> cars = Set.of(move2, move1);

        // when
        RacingGame game = new RacingGame(cars, 0, () -> 4);
        while(!game.isFinished()) {
            game.start();
        }

        // then
        assertThat(game.getWinnerNames()).containsExactly("move_2");
    }

    @DisplayName("이름 입력에 공백이 포함된 경우 재입력 요청")
    @Test
    void ifContainSpaceInNamesInputRequestRetry() {
        // given
        ByteArrayInputStream bais = new ByteArrayInputStream("Alice, Bob, Charlie\n".getBytes());
        InputView inputView = new InputView(bais);

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::inputNames
        );
    }

    @DisplayName("턴 입력에 음이 아닌 정수가 아닌 값을 입력한 경우 재입력 요청")
    @Test
    void isNotNonNegativeInteger() {
        // given
        ByteArrayInputStream bais = new ByteArrayInputStream("it is string\n".getBytes());
        InputView inputView = new InputView(bais);

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::inputTurnCount
        );
    }

    @DisplayName("이름 입력값이 정상적으로 처리된 경우")
    @Test
    void validNamesInput() {
        // given
        ByteArrayInputStream bais = new ByteArrayInputStream("Alice,Bob,Charlie\n".getBytes());
        InputView inputView = new InputView(bais);

        // when
        String[] names = inputView.inputNames();

        // then
        assertThat(names).containsExactly("Alice", "Bob", "Charlie");
    }

    @DisplayName("턴 수 입력이 정상적으로 처리된 경우")
    @Test
    void validTurnCountInput() {
        // given
        ByteArrayInputStream bais = new ByteArrayInputStream("1\n".getBytes());
        InputView inputView = new InputView(bais);

        // when
        int turnCount = inputView.inputTurnCount();

        // then
        assertThat(turnCount).isEqualTo(1);
    }
}
