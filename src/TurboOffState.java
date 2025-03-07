package src;

public class TurboOffState implements CarState{
    @Override
    public void applyState(Car car) {
        if (car instanceof Saab95) {
            ((Saab95) car).setTurboOff();
        }
    }
}
