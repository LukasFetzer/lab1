import java.awt.*;

public interface Movable {
    void move(double x, double y);
    void turnLeft();
    void turnRight();
}

enum Direction {
    NORTH, EAST, SOUTH, WEST
}

