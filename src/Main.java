package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        Bilverkstad<Volvo240> workshop = new Bilverkstad<Volvo240>(5);
        Point volvoWorkshopPoint = new Point(50,300);
        DrawPanel drawPanel = new DrawPanel(800, 800);
        SimulationController simulationController = new SimulationController(workshop, drawPanel, volvoWorkshopPoint);
        CarController carController = new CarController(simulationController);
        EventView eventView = new EventView(carController);

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Scania scania = new Scania();

        volvo.setX(0);
        volvo.setY(0);
        saab.setX(100);
        saab.setY(0);
        scania.setX(200);
        scania.setY(0);

        simulationController.addCar(volvo);
        simulationController.addCar(saab);
        simulationController.addCar(scania);

        // Load car images
        try {
            BufferedImage volvoImage = ImageIO.read(Main.class.getResourceAsStream("pics/Volvo240.jpg"));
            BufferedImage saabImage = ImageIO.read(Main.class.getResourceAsStream("pics/Saab95.jpg"));
            BufferedImage scaniaImage = ImageIO.read(Main.class.getResourceAsStream("pics/Scania.jpg"));
            BufferedImage workshopImage = ImageIO.read(Main.class.getResourceAsStream("pics/VolvoBrand.jpg"));

            drawPanel.addCarRenderer(new CarRenderer(volvo, volvoImage));
            drawPanel.addCarRenderer(new CarRenderer(saab, saabImage));
            drawPanel.addCarRenderer(new CarRenderer(scania, scaniaImage));
            drawPanel.addWorkshopRenderer(new WorkshopRenderer(workshopImage, volvoWorkshopPoint));
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainView mainView = new MainView("CarSim 1.0", carController, drawPanel, eventView);

        simulationController.startSimulation();
    }
}