package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import src.Car;
import src.Volvo240;
import src.Bilverkstad;
import src.DrawPanel;
import src.CarView;
import src.CarController;
import src.Saab95;
import src.Truck;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    private Bilverkstad<Volvo240> bilverkstad;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.bilverkstad = new Bilverkstad<Volvo240>(5);

        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Scania scania = new Scania();

        cc.cars.add(volvo);
        cc.cars.add(saab);
        cc.cars.add(scania);

        volvo.setX(0);
        volvo.setY(0);
        saab.setX(100);
        saab.setY(0);
        scania.setX(200);
        scania.setY(0);

        cc.frame = new CarView("CarSim 1.0", cc);

        try{
            BufferedImage volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            BufferedImage saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            BufferedImage scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

            cc.frame.drawPanel.addCar(volvo, volvoImage);
            cc.frame.drawPanel.addCar(saab, saabImage);
            cc.frame.drawPanel.addCar(scania, scaniaImage);
        }catch(Exception e){
            e.printStackTrace();
        }

        // Start a new view and send a reference of self


        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(car, x, y);

                if (car instanceof Volvo240 && collidesWith((Volvo240) car)){
                    bilverkstad.addCar((Volvo240) car);
                    cars.remove(car);
                    frame.drawPanel.removeCar(car);
                    break;
                }
            }
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    private boolean collidesWith(Volvo240 volvo){
        Point workshopPosition = frame.drawPanel.getVolvoWorkshopPoint();
        double workshopX = workshopPosition.x;
        double workshopY = workshopPosition.y;
        double x = volvo.getX();
        double y = volvo.getY();
        double radius = 51;
        return Math.sqrt(Math.pow(x-workshopX, 2) + Math.pow(y-workshopY, 2)) <= radius;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        for (Car car : cars
                ) {
            car.gas(amount);
        }
    }
    void brake(int amount) {
        for (Car car : cars
        ) {
            car.breaks(amount);
        }
    }

    void startAllCars() {
        for (Car car : cars
        ) {
            car.startEngine();
        }
    }

    void stopAllCars() {
        for (Car car : cars
        ) {
            car.stopEngine();
        }
    }

    void turboOnSaab(){
        Saab95 saab = (Saab95) cars.get(1);
        saab.setTurboOn();
    }

    void turboOffSaab(){
        Saab95 saab = (Saab95) cars.get(1);
        saab.setTurboOff();
    }

    void liftBed(){
        Scania scania = (Scania) cars.get(2);
        for (int i = 0; i < 70; i++) {
            scania.raiseRamp();
        }
    }

    void lowerBed(){
        Scania scania = (Scania) cars.get(2);
        for (int i = 0; i < 70; i++) {
            scania.lowerRamp();
        }
    }

}
