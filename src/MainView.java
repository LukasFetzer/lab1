package src;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final CarController carController;
    private final DrawPanel drawPanel;
    private final EventView eventView;

    public MainView(String title, CarController carController,DrawPanel drawPanel, EventView eventView) {
        this.carController = carController;
        this.drawPanel = drawPanel;
        this.eventView = eventView;
        initComponents(title);
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(800, 800));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addCarButton = new JButton("Add Car");
        buttonPanel.add(addCarButton);

        JButton removeCarButton = new JButton("Remove Car");
        buttonPanel.add(removeCarButton);

        JPanel gasPanel = new JPanel();
        JLabel gasLabel = new JLabel("Amount of gas");
        JSpinner gasSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        buttonPanel.add(gasPanel);

        JButton gasButton = new JButton("Gas");
        JButton brakeButton = new JButton("Brake");
        JButton turboOnButton = new JButton("Saab Turbo on");
        JButton turboOffButton = new JButton("Saab Turbo off");
        JButton liftBedButton = new JButton("Scania Lift Bed");
        JButton lowerBedButton = new JButton("Scania lower Bed");
        JButton startButton = new JButton("Start all cars");
        JButton stopButton = new JButton("Stop all cars");

        buttonPanel.add(gasButton);
        buttonPanel.add(brakeButton);
        buttonPanel.add(turboOnButton);
        buttonPanel.add(turboOffButton);
        buttonPanel.add(liftBedButton);
        buttonPanel.add(lowerBedButton);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        AddCarCommand addCarCommand = new AddCarCommand(carController.getSimulationController(), drawPanel);
        RemoveCarCommand removeCarCommand = new RemoveCarCommand(carController.getSimulationController(), drawPanel);

        addCarButton.addActionListener(e -> addCarCommand.execute());
        removeCarButton.addActionListener(e -> removeCarCommand.execute());

        eventView.addButtonListeners(gasButton, brakeButton, turboOnButton, turboOffButton,
                liftBedButton, lowerBedButton, startButton, stopButton, gasSpinner);

        this.pack();
        this.setVisible(true);
    }
}