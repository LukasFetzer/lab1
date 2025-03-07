package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorkshopRenderer {
    private final BufferedImage image;
    private final Point position;

    public WorkshopRenderer(BufferedImage image, Point position) {
        this.image = image;
        this.position = position;
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
    }

    public Point getPosition() {
        return position;
    }
}