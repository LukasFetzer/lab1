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

    @Test
    public void testRampManagement() {
        assertFalse(scania.isRampDown());

        scania.lowerRamp();
        assertTrue(scania.isRampDown());

        scania.raiseRamp();
        assertFalse(scania.isRampDown());
    }

    @Test
    public void testMovementWithRampDown() {
        scania.lowerRamp();

        scania.startEngine();
        scania.gas(1);
        scania.move();
        assertEquals(0, scania.getX());
        assertEquals(0, scania.getY());
    }

    @Test
    public void testFlakAngleManagement() {
        scania.lowerRamp();

        scania.raiseFlak();
        assertEquals(1, scania.getFlakAngle());

        scania.lowerFlak();
        assertEquals(0, scania.getFlakAngle());

        for (int i = 0; i < 80; i++) {
            scania.raiseFlak();
        }
        assertEquals(70, scania.getFlakAngle());
    }
}
