import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RacingGame {
    
    private Set<MovableCar> carSet;
    private Scanner scanner = new Scanner(System.in);
    private int turn;

    public RacingGame() {
        carSetInit();

        System.out.println("시도할 회수는 몇회인가요?");
        this.turn = scanner.nextInt();
    }

    private void carSetInit() {

        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String[] names = scanner.nextLine().split(",");

        for (String name : names) {
            this.carSet.add(new MovableCar(name));
        }
    }

    public void game() {
        RaceStart();

        int highestDistance = getHighestDistance();

        List<String> winnerList = getWinnerList(highestDistance).stream()
                .map(MovableCar::getName)
                .toList();

        System.out.println(winnerList + "가 최종 우승했습니다.");
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
