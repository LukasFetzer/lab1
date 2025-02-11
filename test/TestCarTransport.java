package test;
import java.awt.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import src.CarTransport;
import src.Saab95;
import src.Bilverkstad;
import src.Volvo240;
import src.Car;
import static org.junit.jupiter.api.Assertions.*;

public class TestCarTransport {
    private CarTransport carTransport = new CarTransport(2);
    private Saab95 saab = new Saab95();
    private Volvo240 volvo = new Volvo240();

    @BeforeEach
    public void setUp() {
        carTransport = new CarTransport(2);
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @Test
    public void testLoadCar() {
        carTransport.lowerRamp();

        carTransport.loadCar(saab);
        carTransport.loadCar(volvo);

        assertEquals(2, carTransport.getNumberOfCars());

        //test if it loads more than the limit
        Car extraCar = new Saab95();
        carTransport.loadCar(extraCar);
        assertEquals(2, carTransport.getNumberOfCars());
    }

    @Test
    public void testUnloadCar() {
        carTransport.lowerRamp();
        carTransport.loadCar(saab);
        carTransport.loadCar(volvo);

        carTransport.unloadCar();

        assertEquals(1, carTransport.getNumberOfCars());

        carTransport.unloadCar();

        assertEquals(0, carTransport.getNumberOfCars());
    }

    @Test
    public void testMovementWithLoadedCars() {
        carTransport.lowerRamp();
        carTransport.loadCar(saab);
        carTransport.loadCar(volvo);

        carTransport.raiseRamp();
        carTransport.startEngine();
        carTransport.gas(1);
        carTransport.move();

        assertEquals(carTransport.getX(), saab.getX());
        assertEquals(carTransport.getY(), saab.getY());
        assertEquals(carTransport.getX(), volvo.getX());
        assertEquals(carTransport.getY(), volvo.getY());
    }

    @Test
    public void testRampRestrictions() {
        carTransport.loadCar(saab);
        assertEquals(0, carTransport.getNumberOfCars());

        carTransport.lowerRamp();
        carTransport.loadCar(saab);
        assertEquals(1, carTransport.getNumberOfCars());

        carTransport.startEngine();
        carTransport.gas(1);
        carTransport.move();
        assertEquals(0, carTransport.getX());
        assertEquals(0, carTransport.getY());
    }
}
