package src;

public class TurboState implements CarState{
    @Override
    public void applyState(Car car) {
        if (car instanceof Saab95) {
            ((Saab95) car).setTurboOn();
        }
    }

}
