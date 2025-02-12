package src;

import java.awt.*;

public class Scania extends Truck {

    private int flakAngle = 70;

    @Override
    public void lowerRamp() {
        if (getCurrentSpeed() == 0 && flakAngle > 0) {
            flakAngle -= 1;
        }
        if (flakAngle <= 0) {
            flakAngle = 0;
            super.lowerRamp();
        }
    }

    @Override
    public void raiseRamp() {
        if (getCurrentSpeed() == 0 && flakAngle < 70) {
            flakAngle += 1;
        }
        if (flakAngle >= 70) {
            flakAngle = 70;
            super.raiseRamp();
        }
    }

    public Scania(){
        super(2, 120, Color.white, "Scania lastbil", 5000);
        stopEngine();
    }

    public int getFlakAngle() {
        return flakAngle;
    }

}
