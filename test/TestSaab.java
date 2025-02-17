package test;
import java.awt.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import src.Direction;
import src.Saab95;
import static org.junit.jupiter.api.Assertions.*;

public class TestSaab {

    Saab95 saab = new Saab95();
    private final double initialSpeed = 0.1;
    private final int expectedEnginePower = 125;
    private double expectedX = 0, expectedY = 0;

    private int getExpectedGasBreak(int amount) {
        if (amount < 0){
            amount = 0;
        } else if (amount > 1){
            amount = 1;
        }
        return amount;
    }

    private double getExpectedSpeedFactor(boolean turboOn) {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return expectedEnginePower * 0.01 * turbo;
    }

    @BeforeEach
    void setup() {
        saab = new Saab95();
    }

    @Test
    public void testKonstruktor() {
        assertEquals(2, saab.getNrDoors());
        assertEquals(125, saab.getEnginePower());
        assertEquals(Color.red, saab.getColor());
        assertEquals("Saab95", saab.getModelName());
    }

    @Test
    public void testEngine() {
        saab.startEngine();

        assertEquals(initialSpeed, saab.getCurrentSpeed());

        saab.stopEngine();

        assertEquals(0, saab.getCurrentSpeed());

    }

    @Test
    public void testSetColor() {
        saab.setColor(Color.GREEN);
        assertEquals(Color.GREEN, saab.getColor());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testGasTurboOff(int amount) {
        saab.startEngine();

        saab.gas(amount);

        assertEquals(
                Math.min(initialSpeed + getExpectedSpeedFactor(false) * getExpectedGasBreak(amount), expectedEnginePower),
                saab.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testGasTurboOn(int amount) {
        saab.startEngine();
        saab.setTurboOn();

        saab.gas(amount);

        assertEquals(
                Math.min(initialSpeed + getExpectedSpeedFactor(true) * getExpectedGasBreak(amount), expectedEnginePower),
                saab.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testBreakTurboOff(int amount) {
        saab.startEngine();

        saab.breaks(amount);

        assertEquals(
                Math.max(initialSpeed - getExpectedSpeedFactor(false) * getExpectedGasBreak(amount), 0),
                saab.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints ={-300, 0, 1, 300})
    public void testBreakTurboOn(int amount) {
        saab.startEngine();
        saab.setTurboOn();

        saab.breaks(amount);

        assertEquals(
                Math.max(initialSpeed - getExpectedSpeedFactor(false) * getExpectedGasBreak(amount), 0),
                saab.getCurrentSpeed());
    }

    @Test
    public void testMove() {
        saab.startEngine();
        saab.turnLeft();

        assertEquals(Direction.WEST, saab.getDirection());

        saab.move();

        expectedX -= initialSpeed;
        assertEquals(expectedX, saab.getX());
        assertEquals(expectedY, saab.getY());

        saab.turnLeft();

        assertEquals(Direction.SOUTH, saab.getDirection());

        saab.move();

        expectedY -= initialSpeed;
        assertEquals(expectedX, saab.getX());
        assertEquals(expectedY, saab.getY());

        saab.turnLeft();

        assertEquals(Direction.EAST, saab.getDirection());

        saab.move();

        expectedX += initialSpeed;
        assertEquals(expectedX, saab.getX());
        assertEquals(expectedY, saab.getY());

        saab.turnLeft();

        assertEquals(Direction.NORTH, saab.getDirection());

        saab.move();

        expectedY += initialSpeed;
        assertEquals(expectedX, saab.getX());
        assertEquals(expectedY, saab.getY());
    }

    @Test
    public void testTurnLeft() {
        saab.startEngine();

        saab.turnLeft();

        assertEquals(Direction.WEST, saab.getDirection());

        saab.turnLeft();

        assertEquals(Direction.SOUTH, saab.getDirection());

        saab.turnLeft();

        assertEquals(Direction.EAST, saab.getDirection());

        saab.turnLeft();

        assertEquals(Direction.NORTH, saab.getDirection());
    }

    @Test
    public void testTurnRight() {
        saab.startEngine();

        saab.turnRight();

        assertEquals(Direction.EAST, saab.getDirection());

        saab.turnRight();

        assertEquals(Direction.SOUTH, saab.getDirection());

        saab.turnRight();

        assertEquals(Direction.WEST, saab.getDirection());

        saab.turnRight();

        assertEquals(Direction.NORTH, saab.getDirection());
    }



}
