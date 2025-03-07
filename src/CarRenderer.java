package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CarRenderer {
    private final Car car;
    private final BufferedImage image;
    private final Point position;

    public CarRenderer(Car car, BufferedImage image) {
        this.car = car;
        this.image = image;
        this.position = new Point((int) car.getX(), (int) car.getY());
    }

    public Car getCar() {
        return car;
    }

    public void updatePosition() {
        position.x = (int) car.getX();
        position.y = (int) car.getY();
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
    }
}