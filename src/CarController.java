package src;

public class CarController {
    private final SimulationController simulationController;

    public CarController(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void gas(int amount) {
        for (Car car : simulationController.getCars()) {
            car.gas(amount);
        }
    }

    public void brake(int amount) {
        for (Car car : simulationController.getCars()) {
            car.breaks(amount);
        }
    }

    public void startAllCars() {
        for (Car car : simulationController.getCars()) {
            car.startEngine();
        }
    }

    public void stopAllCars() {
        for (Car car : simulationController.getCars()) {
            car.stopEngine();
        }
    }

    public void turboOnSaab() {
        for (Car car : simulationController.getCars()) {
            car.setState( new TurboState());
        }
    }

    public void turboOffSaab() {
        for (Car car : simulationController.getCars()) {
            car.setState(new TurboOffState());
        }
    }

    public void liftBed() {
        for (Car car : simulationController.getCars()) {
            if (car instanceof Scania) {
                car.setState(new LiftedBedState());
            }
        }
    }

    public void lowerBed() {
        for (Car car : simulationController.getCars()) {
            if (car instanceof Scania) {
                car.setState(new LoweredBedState());
            }
        }
    }
}