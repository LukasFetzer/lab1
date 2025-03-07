package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel implements Observer{
    private final List<CarRenderer> carRenderers = new ArrayList<>();
    private final List<WorkshopRenderer> workshopRenderers = new ArrayList<>();

    public DrawPanel(int x, int y) {
        this.setPreferredSize(new Dimension(x, y)); // Set preferred size
        this.setBackground(Color.green); // Set background color
    }

    public void addCarRenderer(CarRenderer renderer) {
        carRenderers.add(renderer);
    }

    public void addWorkshopRenderer(WorkshopRenderer renderer) {
        workshopRenderers.add(renderer);
    }

    public void removeCar(Car car) {
        carRenderers.removeIf(renderer -> renderer.getCar() == car);
    }

    @Override
    public void update(){
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CarRenderer renderer : carRenderers) {
            renderer.updatePosition();
            renderer.draw(g);
        }
        for (WorkshopRenderer renderer : workshopRenderers) {
            renderer.draw(g);
        }
    }
}
