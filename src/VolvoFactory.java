package src;

public class VolvoFactory implements Carfactory{
    public Car createCar() {
        return new Volvo240();
    }
}
