import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RacingGame {
    
    private Set<MovableCar> carSet;
    private Scanner scanner = new Scanner(System.in);
    private int turn;

    public RacingGame(Set<MovableCar> carSet) {
        int count = scanner.nextInt();
        carSetInit(count);
        this.turn = scanner.nextInt();
    }

    private void carSetInit(int count) {
        for (int i = 0; i < count; i++) {
            this.carSet.add(new MovableCar(i + "번 차"));
        }
    }

    public void game() {
        gameStart();
    }

    private void gameStart() {
        RaceStart();

        int highestDistance = getHighestDistance();
        List<MovableCar> winnerList = getWinnerList(highestDistance);

    }

    private List<MovableCar> getWinnerList(int highestDistance) {
        return carSet.parallelStream()
                .filter(car ->
                        car.getDistance() == highestDistance)
                .toList();
    }

    private void RaceStart() {
        for(int i = 0; i < turn; i++)
            moveAllCar();
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
