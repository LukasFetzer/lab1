package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final Map<Car, Point> carPositions = new HashMap<>();
    private final Map<Car, BufferedImage> carImages = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(50,300);

    public Point getVolvoWorkshopPoint() {
        return volvoWorkshopPoint;
    }

    // TODO: Make this general for all cars
    void moveit(Car car, int x, int y){
        Point position = carPositions.get(car);
        position.x = x;
        position.y = y;
    }

    public void addCar(Car car, BufferedImage image) {
        carPositions.put(car, new Point((int) car.getX(), (int) car.getY()));
        carImages.put(car, image);
    }

    public void removeCar(Car car) {
        carPositions.remove(car);
        carImages.remove(car);
    }



    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

        for (Map.Entry<Car, Point> entry : carPositions.entrySet()) {
            Car car = entry.getKey();
            Point position = entry.getValue();
            BufferedImage image = carImages.get(car);
            if (image != null) {
                g.drawImage(image, position.x, position.y, null);
            }
        }
        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
}
