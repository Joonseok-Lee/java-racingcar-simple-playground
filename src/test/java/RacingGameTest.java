import domain.CarListInitializer;
import domain.MovableCar;
import domain.RacingGame;
import domain.rand.RandomMove;
import fixed.FalseFixedMove;
import fixed.TrueFixedMove;
import org.junit.jupiter.api.*;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {

    private TrueFixedMove trueMove;
    private FalseFixedMove falseMove;

    @BeforeEach
    void moveSetUp() {
        trueMove = new TrueFixedMove();
        falseMove = new FalseFixedMove();
    }

    @Nested
    @DisplayName("MovableCar 이동 테스트")
    class CarMoveTest {
        @Test
        @DisplayName("move(true)가 1회 호출되면, 1칸 전진한다.")
        void testFixedMove() {
            // given
            MovableCar car = new MovableCar("car");

            // when
            car.move(true);

            // then
            assertThat(car.getDistance()).isEqualTo(1);
        }

        @Test
        @DisplayName("move(false)가 1회 호출되면, 전진하지 않는다.")
        void testFixedDontMove() {
            // given
            MovableCar car = new MovableCar("car");

            // when
            car.move(false);

            // then
            assertThat(car.getDistance()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("게임 우승자 처리 테스트")
    class WinLogicTest {

        @Test
        @DisplayName("차가 1대인 경우")
        void ifSingleCar() {
            // given
            String[] names = { "single" };
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 3, new RandomMove());

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
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 0,new RandomMove());

            // when
            while(!game.isFinished()) {
                game.start();
            }

            // then
            assertThat(game.getWinnerNames()).containsExactly("1st", "2nd", "3rd");
        }

        @Test
        @DisplayName("모두 움직이지 않는 경우 모두 승리한다.")
        void allCarIsStopped() {
            // given
            String[] names = { "1st", "2nd", "3rd" };
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 3, falseMove);

            // when
            while (!game.isFinished()) {
                game.start();
            }

            // then
            assertThat(game.getWinnerNames()).containsExactly("1st", "2nd", "3rd");
        }

        @Test
        @DisplayName("1칸 더 이동한 차량이 항상 우승한다.")
        void winnerIsMoreMove() {
            // given
            MovableCar exactCar = new MovableCar("exactCar");
            MovableCar overCar = new MovableCar("overCar");

            overCar.move(true);

            List<MovableCar> cars = List.of(exactCar, overCar);

            // when
            RacingGame game = new RacingGame(cars, 10, trueMove);
            while(!game.isFinished()) {
                game.start();
            }

            // then
            assertThat(overCar.getDistance() > exactCar.getDistance()).isEqualTo(true);
            assertThat(game.getWinnerNames()).containsExactly("overCar");
        }
    }

    @Nested
    @DisplayName("차량 이름, 턴 수 입력 테스트")
    class InputViewTest {

        @Test
        @DisplayName("이름 입력에 공백이 포함된 경우 재입력 요청")
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

        @Test
        @DisplayName("이름 입력이 5글자 이상 된 경우 재입력 요청")
        void ifNameInputLengthExceeded5() {
            // given
            ByteArrayInputStream bais = new ByteArrayInputStream("edward\n".getBytes());
            InputView inputView = new InputView(bais);

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::inputNames
            );
        }

        @Test
        @DisplayName("중복된 이름을 입력한 경우 재입력을 요청")
        void ifDuplicateNamesInput() {
            // given
            ByteArrayInputStream bais = new ByteArrayInputStream("Alice,Alice".getBytes());
            InputView inputView = new InputView(bais);

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::inputNames
            );
        }

        @Test
        @DisplayName("턴 입력에 음의 정수를 입력한 경우 재입력 요청")
        void isNegativeInteger() {
            // given
            ByteArrayInputStream bais = new ByteArrayInputStream("-1\n".getBytes());
            InputView inputView = new InputView(bais);

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::inputTurnCount
            );
        }

        @Test
        @DisplayName("턴 입력에 문자열을 입력한 경우 재입력 요청")
        void isStringValueDuringInputTurnCount() {
            // given
            ByteArrayInputStream bais = new ByteArrayInputStream("hello_world\n".getBytes());
            InputView inputView = new InputView(bais);

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::inputTurnCount
            );
        }

        @Test
        @DisplayName("이름 입력값이 정상적으로 처리된 경우")
        void validNamesInput() {
            // given
            ByteArrayInputStream bais = new ByteArrayInputStream("Alice,Bob,Chloe\n".getBytes());
            InputView inputView = new InputView(bais);

            // when
            String[] names = inputView.inputNames();

            // then
            assertThat(names).containsExactly("Alice", "Bob", "Chloe");
        }

        @Test
        @DisplayName("턴 수 입력이 정상적으로 처리된 경우")
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
}
