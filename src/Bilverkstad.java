package src;
import java.util.ArrayList;
import java.util.List;

public class Bilverkstad<T extends Car> {
    private final int maxCapacity;
    private final List<T> cars;

    public Bilverkstad(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }

    public void addCar(T car) {
        if (cars.size() < maxCapacity) {
            cars.add(car);
        } else {
            System.out.println("Workshop is full. Cannot add more cars.");
        }
    }

    public T retrieveCar() {
        if (!cars.isEmpty()) {
            return cars.removeFirst();
        } else {
            System.out.println("No cars available in the workshop.");
            return null;
        }
    }

    public int getNumberOfCars() {
        return cars.size();
    }

}
