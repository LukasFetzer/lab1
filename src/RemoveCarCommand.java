package src;

public class RemoveCarCommand implements Command {
    private final SimulationController simulationController;
    private final DrawPanel drawPanel;

    public RemoveCarCommand(SimulationController simulationController, DrawPanel drawPanel) {
        this.simulationController = simulationController;
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute() {
        if (simulationController.getCars().isEmpty()) {
            return;
        }

        Car car = simulationController.getCars().get(0);
        simulationController.getCars().remove(car);
        drawPanel.removeCar(car);
    }
}
