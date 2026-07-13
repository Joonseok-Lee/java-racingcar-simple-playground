import java.util.Scanner;
import java.util.Set;

public class RacingGame {
    
    private Set<MovableCar> carSet;

    public RacingGame(Set<MovableCar> carSet) {
        int count = new Scanner(System.in).nextInt();
        carSetInit(carSet, count);
    }

    private void carSetInit(Set<MovableCar> carSet, int count) {
        for (int i = 0; i < count; i++) {
            carSet.add(new MovableCar(i + "번 차"));
        }
    }
}
