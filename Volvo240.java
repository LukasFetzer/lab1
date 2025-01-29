import java.awt.*;

public class Volvo240 extends Car {

    public final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, 100, Color.black, "Volvo240");
        stopEngine();
    }

    @Override protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }
}
