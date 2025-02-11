package src;

import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, 100, Color.black, "Volvo240", 2000);
        stopEngine();
    }

    @Override public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
