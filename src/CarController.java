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
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOffSaab() {
        for (Car car : simulationController.getCars()) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Car car : simulationController.getCars()) {
            if (car instanceof Scania) {
                for (int i = 0; i < 70; i++) {
                    ((Scania) car).raiseRamp();
                }
            }
        }
    }

    public void lowerBed() {
        for (Car car : simulationController.getCars()) {
            if (car instanceof Scania) {
                for (int i = 0; i < 70; i++) {
                    ((Scania) car).lowerRamp();
                }
            }
        }
    }
}