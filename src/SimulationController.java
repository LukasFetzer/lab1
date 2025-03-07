package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    private final ArrayList<Car> cars = new ArrayList<>();
    private final Bilverkstad workshop;
    private final DrawPanel drawPanel;
    private final Timer timer;
    private final Point workshopPosition;
    private final List<Observer> observers = new ArrayList<>();

    public SimulationController(Bilverkstad<Volvo240> workshop, DrawPanel drawPanel, Point workshopPosition) {
        this.workshop = workshop;
        this.drawPanel = drawPanel;
        this.workshopPosition = workshopPosition;

        this.observers.add(drawPanel);

        this.timer = new Timer(50, e -> updateSimulation());
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void startSimulation() {
        timer.start();
    }

    public void stopSimulation() {
        timer.stop();
    }

    public void handleCollisions() {
        for (Car car : cars) {
            if (car instanceof Volvo240 && collidesWithWorkshop((Volvo240) car)) {
                workshop.addCar((Volvo240) car);
                cars.remove(car);
                drawPanel.removeCar(car);
                break;
            }
        }
    }

    public void updateSimulation() {
        for (Car car : cars) {
            car.move();

        }

        handleCollisions();

        notifyObserver();
    }

    private boolean collidesWithWorkshop(Volvo240 car) {
        double distance = Math.sqrt(Math.pow(car.getX() - workshopPosition.x, 2) + Math.pow(car.getY() - workshopPosition.y, 2));
        return distance <= 51;
    }
}