package src;

import java.awt.*;

public class Scania extends Truck {

    private int flakAngle = 0;


    public void lowerFlak() {
        if (getCurrentSpeed() == 0 && flakAngle > 0) {
            flakAngle -= 1;
        }
    }

    public void raiseFlak() {
        if (getCurrentSpeed() == 0 && flakAngle < 70) {
            flakAngle += 1;
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
