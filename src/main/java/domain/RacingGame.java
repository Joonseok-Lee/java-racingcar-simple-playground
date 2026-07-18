package domain;

import domain.rand.Movable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingGame {

    private final Set<MovableCar> carSet;
    private final int turn;
    private final Movable movable;

    public RacingGame(Set<MovableCar> cars, int turn, Movable movable) {
        this.carSet = cars;
        this.turn = turn;
        this.movable = movable;
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
