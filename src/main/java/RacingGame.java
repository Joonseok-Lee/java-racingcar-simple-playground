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
}
