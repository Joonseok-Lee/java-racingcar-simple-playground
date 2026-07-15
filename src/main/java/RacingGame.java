import view.InputView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingGame {

    private Set<MovableCar> carSet = new HashSet<>();
    private int turn;

    public RacingGame(List<String> carNames, int turn) {
        carSetInit(carNames);
        this.turn = turn;
    }

    private void carSetInit(List<String> carNames) {
        for (String name : carNames) {
            this.carSet.add(new MovableCar(name));
        }
    }

    public void start() {
        RaceStart();

        int highestDistance = getHighestDistance();

        List<String> winnerList = getWinnerList(highestDistance).stream()
                .map(MovableCar::getName)
                .toList();

        System.out.println(String.join(", ", winnerList) + "가 최종 우승했습니다.");
    }

    private List<MovableCar> getWinnerList(int highestDistance) {
        return carSet.parallelStream()
                .filter(car ->
                        car.getDistance() == highestDistance)
                .toList();
    }

    private void RaceStart() {
        System.out.println("실행 결과");
        for(int i = 0; i < turn; i++) {
            moveAllCar();
            printTurnResult();
        }
    }

    private void printTurnResult() {
        for(MovableCar car : carSet) {
            System.out.println(car.getName() + " : " + car.getDistanceToString());
        }
        System.out.println();
    }

    private void moveAllCar() {
        carSet.stream()
                .parallel()
                .forEach(MovableCar::move);
    }

    private int getHighestDistance() {
        int highestDistance = 0;

        for(MovableCar car : carSet) {
            highestDistance = compareDistance(car, highestDistance);
        }

        return highestDistance;
    }

    private int compareDistance(MovableCar car, int highestDistance) {

        if(car.getDistance() >= highestDistance) {
            highestDistance = car.getDistance();
        }

        return highestDistance;
    }
}
