package src;
import java.awt.*;

public abstract class Truck extends Car {
    private boolean rampDown;

    public Truck(int nrDoors, double enginePower, Color color, String modelName, int weight) {
        super(nrDoors, enginePower, color, modelName, weight);
        this.rampDown = false;
    }


    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            rampDown = true;
        }
    }

    public void raiseRamp() {
        rampDown = false;
    }

    public boolean isRampDown() {
        return rampDown;
    }

    @Override
    public void startEngine() {
        if (!rampDown) {
            super.startEngine();
        }
    }

    @Override
    public void move() {
        if (!rampDown) {
            super.move();
        }
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
