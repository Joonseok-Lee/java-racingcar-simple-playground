package domain;

import domain.rand.Movable;

import java.util.List;
import java.util.Set;

public class RacingGame {

    private final Set<MovableCar> carSet;
    private final int turn;
    private int currentTurn;
    private final Movable movable;

    public RacingGame(Set<MovableCar> cars, int turn, Movable movable) {
        this.carSet = cars;
        this.turn = turn;
        this.currentTurn = 0;
        this.movable = movable;
    }

    public void start() {
        currentTurn++;
        moveAllCar();
    }

    public List<String> getWinnerNames() {

        int highestDistance = getHighestDistance();

        return getWinnerList(highestDistance).stream()
                .map(MovableCar::getName)
                .toList();
    }

    public Set<MovableCar> getCarSet() {
        return carSet;
    }

    public boolean isFinished() {
        return currentTurn == turn;
    }

    private List<MovableCar> getWinnerList(int highestDistance) {
        return carSet.parallelStream()
                .filter(car ->
                        car.getDistance() == highestDistance)
                .toList();
    }

    private void moveAllCar() {
        carSet.stream()
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
