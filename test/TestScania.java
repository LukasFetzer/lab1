package test;
import java.awt.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import src.Scania;
import static org.junit.jupiter.api.Assertions.*;

public class TestScania {
    private Scania scania = new Scania();

    @BeforeEach
    public void setUp() {
        scania = new Scania();
    }

    /*@Test
    public void testRampManagement() {
        assertFalse(scania.isRampDown());

        scania.lowerRamp();
        assertTrue(scania.isRampDown());

        scania.raiseRamp();
        assertFalse(scania.isRampDown());
    } */

    @Test
    public void testMovementWithRampDown() {
        for (int i = 0; i < 70; i++) {
            scania.lowerRamp();
        }

        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertEquals(0, scania.getX());
        assertEquals(0, scania.getY());
    }

    @Test
    public void testRampAngleManagement() {
        for (int i = 0; i < 70; i++) {
            scania.lowerRamp();
        }
        assertEquals(0, scania.getFlakAngle());
        assertTrue(scania.isRampDown());


        scania.raiseRamp();
        assertEquals(1, scania.getFlakAngle());

        scania.lowerRamp();
        assertEquals(0, scania.getFlakAngle());
        assertTrue(scania.isRampDown());

        for (int i = 0; i < 80; i++) {
            scania.raiseRamp();
        }
        assertEquals(70, scania.getFlakAngle());
        assertFalse(scania.isRampDown());
    }
}
