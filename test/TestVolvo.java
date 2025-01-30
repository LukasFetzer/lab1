package test;
import java.awt.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import src.Direction;
import src.Volvo240;
import static org.junit.jupiter.api.Assertions.*;

public class TestVolvo {

    Volvo240 volvo = new Volvo240();
    private final double initialSpeed = 0.1;
    private final int expectedEnginePower = 100;
    private final double expectedTrimFactor = 1.25;
    private double expectedX = 0, expectedY = 0;

    private int getExpectedGasBreak(int amount) {
        if (amount < 0){
            amount = 0;
        } else if (amount > 1){
            amount = 1;
        }
        return amount;
    }

    private double getExpectedSpeedFactor() {
        return expectedEnginePower * 0.01 * expectedTrimFactor;
    }

    @BeforeEach
    void setup() {
        volvo = new Volvo240();
    }

    @Test
    public void testKonstruktor() {
        assertEquals(4, volvo.getNrDoors());
        assertEquals(100, volvo.getEnginePower());
        assertEquals(Color.black, volvo.getColor());
        assertEquals("Volvo240", volvo.getModelName());
    }

    @Test
    public void testEngine() {
        volvo.startEngine();
        assertEquals(initialSpeed, volvo.getCurrentSpeed());
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    public void testSetColor() {
        volvo.setColor(Color.GREEN);
        assertEquals(Color.GREEN, volvo.getColor());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testIncrementSpeed(int amount) {
        volvo.startEngine();

        volvo.incrementSpeed(amount);

        assertEquals(
                Math.min(initialSpeed + getExpectedSpeedFactor() * amount, expectedEnginePower),
                volvo.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testGas(int amount) {
        volvo.startEngine();

        volvo.gas(amount);

        assertEquals(
                Math.min(initialSpeed + getExpectedSpeedFactor() * getExpectedGasBreak(amount), expectedEnginePower),
                volvo.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testBreak(int amount) {
        volvo.startEngine();

        volvo.breaks(amount);

        assertEquals(
                Math.max(initialSpeed - getExpectedSpeedFactor() * getExpectedGasBreak(amount), 0),
                volvo.getCurrentSpeed());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testMove(Direction direction) {
        volvo.startEngine();
        volvo.setDirection(direction);
        switch (direction){
            case EAST -> expectedX += initialSpeed;
            case WEST -> expectedX -= initialSpeed;
            case NORTH -> expectedY += initialSpeed;
            case SOUTH -> expectedY -= initialSpeed;
        }

        volvo.move();

        assertEquals(expectedX, volvo.getX());
        assertEquals(expectedY, volvo.getY());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testTurnLeft(Direction direction) {
        volvo.startEngine();
        volvo.setDirection(direction);
        switch (direction){
            case NORTH -> direction = Direction.WEST;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
            case WEST -> direction = Direction.SOUTH;
        }

        volvo.turnLeft();

        assertEquals(direction, volvo.getDirection());
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testTurnRight(Direction direction) {
        volvo.startEngine();
        volvo.setDirection(direction);
        switch (direction){
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }

        volvo.turnRight();

        assertEquals(direction, volvo.getDirection());
    }



}
