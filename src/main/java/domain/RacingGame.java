package domain;

import domain.rand.Movable;
import domain.rand.MovableImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingGame {

    private Set<MovableCar> carSet = new HashSet<>();
    private int turn;
    private Movable movable;

    public RacingGame(List<String> carNames, int turn, Movable movable) {
        carSetInit(carNames);
        this.turn = turn;
        this.movable = movable;
    }

    private void carSetInit(List<String> carNames) {
        for (String name : carNames) {
            this.carSet.add(new MovableCar(name));
        }
    }

    public void start() {
        raceStart();
    }

    public List<String> getWinnerNames() {

        int highestDistance = getHighestDistance();

        return getWinnerList(highestDistance).stream()
                .map(MovableCar::getName)
                .toList();
    }

    private List<MovableCar> getWinnerList(int highestDistance) {
        return carSet.parallelStream()
                .filter(car ->
                        car.getDistance() == highestDistance)
                .toList();
    }

    private void raceStart() {
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
                .forEach(car -> car.move(movable.isMovable()));
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
