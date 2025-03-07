package src;

import java.util.Random;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AddCarCommand implements Command {
    private final SimulationController simulationController;
    private final DrawPanel drawPanel;

    public AddCarCommand(SimulationController simulationController, DrawPanel drawPanel) {
        this.simulationController = simulationController;
        this.drawPanel = drawPanel;
    }

    @Override
    public void execute() {
        if (simulationController.getCars().size() >= 10) {
            return;
        }

        Random random = new Random();
        Car car;
        switch (random.nextInt(3)) {
            case 0 -> car = new Volvo240();
            case 1 -> car = new Saab95();
            case 2 -> car = new Scania();
            default -> throw new IllegalStateException("Unexpected value");
        }

        car.setX(random.nextInt(700));
        car.setY(random.nextInt(700));

        simulationController.addCar(car);

        try {
            BufferedImage carImage;
            if (car instanceof Volvo240) {
                carImage = ImageIO.read(Main.class.getResourceAsStream("pics/Volvo240.jpg"));
            } else if (car instanceof Saab95) {
                carImage = ImageIO.read(Main.class.getResourceAsStream("pics/Saab95.jpg"));
            } else {
                carImage = ImageIO.read(Main.class.getResourceAsStream("pics/Scania.jpg"));
            }
            drawPanel.addCarRenderer(new CarRenderer(car, carImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
