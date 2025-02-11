package src;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.util.ArrayDeque;

public class CarTransport extends Truck{
    private final Deque<Car> loadedCars; // Stack-like structure to store loaded cars
    private final int maxCapacity; // Maximum number of cars the transport can carry
    private final double loadingDistance = 5.0; // Maximum distance for loading/unloading cars



    public CarTransport(int maxCapacity) {
        super(2, 200, Color.blue, "Car Transport", 6000);
        this.maxCapacity = maxCapacity;
        this.loadedCars = new ArrayDeque<>();
    }

    public void loadCar(Car car) {
        if (isRampDown() && isCloseToTransport(car) && !isCarTooLarge(car) && !(car instanceof CarTransport)) {
            if (loadedCars.size() < maxCapacity) {
                loadedCars.push(car);
                car.setPosition(this.getX(), this.getY());
            } else {
                System.out.println("Cannot load car: Transport is full.");
            }
        }
    }

    public void unloadCar() {
        if (isRampDown() && !loadedCars.isEmpty()) {
            Car car = loadedCars.pop();
            car.setPosition(this.getX() + 2, this.getY() + 2);
        } else {
            System.out.println("Cannot unload car: Ramp is up or no cars loaded.");
        }
    }

    private boolean isCloseToTransport(Car car) {
        double distance = Math.sqrt(Math.pow(car.getX() - this.getX(), 2) + Math.pow(car.getY() - this.getY(), 2));
        return distance <= loadingDistance;
    }

    private boolean isCarTooLarge(Car car) {
        return car.getWeight() > 3000;
    }

    @Override
    public void move() {
        super.move();
        for (Car car : loadedCars) {
            car.setPosition(this.getX(), this.getY());
        }
    }

    public int getNumberOfCars() {
        return loadedCars.size();
    }

}
