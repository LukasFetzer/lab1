package src;

public class ScaniaFactory implements Carfactory {
    public Car createCar() {
        return new Scania();
    }
}
