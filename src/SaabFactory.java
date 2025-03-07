package src;

public class SaabFactory implements Carfactory{
    public Car createCar() {
        return new Saab95();
    }
}
