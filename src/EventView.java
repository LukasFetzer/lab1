package src;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EventView {
    private final CarController carController;

    public EventView(CarController carController) {
        this.carController = carController;
    }

    public void addButtonListeners(JButton gasButton, JButton brakeButton, JButton turboOnButton, JButton turboOffButton,
                                   JButton liftBedButton, JButton lowerBedButton, JButton startButton, JButton stopButton,
                                   JSpinner gasSpinner) {
        gasButton.addActionListener(e -> carController.gas((int) gasSpinner.getValue()));
        brakeButton.addActionListener(e -> carController.brake((int) gasSpinner.getValue()));
        turboOnButton.addActionListener(e -> carController.turboOnSaab());
        turboOffButton.addActionListener(e -> carController.turboOffSaab());
        liftBedButton.addActionListener(e -> carController.liftBed());
        lowerBedButton.addActionListener(e -> carController.lowerBed());
        startButton.addActionListener(e -> carController.startAllCars());
        stopButton.addActionListener(e -> carController.stopAllCars());
    }
}