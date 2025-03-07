package src;

public class LoweredBedState implements CarState {
    public void applyState(Car car) {
        if (car instanceof Scania) {
            for (int i = 0; i < 70; i++) {
                ((Scania) car).lowerRamp();
            }
        }
    }
}
