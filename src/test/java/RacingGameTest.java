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
    private RandomMove randMove;

    @BeforeEach
    void moveSetUp() {
        trueMove = new TrueFixedMove(4);
        falseMove = new FalseFixedMove(4);
        randMove = new RandomMove(4);
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

        @Test
        @DisplayName("4 이상이면 전진, 3 이하면 멈춤 테스트")
        void moveLogicTest() {
            // given
            MovableCar move = new MovableCar("move");
            MovableCar dontMove = new MovableCar("dontMove");

            // when
            move.move(randMove.isMovable(4));
            move.move(randMove.isMovable(9));
            dontMove.move(randMove.isMovable(3));
            dontMove.move(randMove.isMovable(0));

            // then
            assertThat(move.getDistance()).isEqualTo(2);
            assertThat(dontMove.getDistance()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("차량 리스트 테스트")
    class CarListTest {

        @Test
        @DisplayName("이름 배열과, 초기화된 차량들의 이름이 동일한지 테스트")
        void testCompareInputValueWithCarNames() {
            // given
            String[] names = { "Alice", "Bob", "Chloe" };

            // when
            List<MovableCar> cars = CarListInitializer.initCarList(names);

            // then
            assertThat(cars)
                    .extracting(MovableCar::getName)
                    .containsExactly("Alice", "Bob", "Chloe");
        }
    }

    @Nested
    @DisplayName("게임 로직 테스트")
    class WinLogicTest {

        @Test
        @DisplayName(", 구분자로 구분된 이름 배열의 길이와, 초기화된 게임의 차량 대수가 같은지 테스트")
        void testCountingCarCountAfterCarInit() {
            // given
            String[] names = { "Alice", "Bob", "Chloe" };
            int expected = names.length;

            // when
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 0, trueMove);

            // then
            assertThat(game.getCarList().size()).isEqualTo(expected);
        }

        @Test
        @DisplayName("차가 1대인 경우")
        void ifSingleCar() {
            // given
            String[] names = { "single" };
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 3, randMove);

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
            RacingGame game = new RacingGame(CarListInitializer.initCarList(names), 0, randMove);

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
