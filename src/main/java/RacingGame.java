import java.util.Scanner;
import java.util.Set;

public class RacingGame {
    
    private Set<MovableCar> carSet;

    public RacingGame(Set<MovableCar> carSet) {
        int count = new Scanner(System.in).nextInt();
        carSetInit(count);
    }

    private void carSetInit(int count) {
        for (int i = 0; i < count; i++) {
            this.carSet.add(new MovableCar(i + "번 차"));
        }
    }
}
