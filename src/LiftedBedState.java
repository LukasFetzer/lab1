package src;

public class LiftedBedState implements CarState {
    public void applyState(Car car){
        if (car instanceof Scania) {
            for (int i = 0; i < 70; i++) {
                ((Scania) car).raiseRamp();
            }
        }
    }
}
