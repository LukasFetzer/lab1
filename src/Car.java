package src;

import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private Direction direction;
    private double x, y;
    private int weight;
    private CarState state;

    public Car(int nrDoors, double enginePower, Color color, String modelName, int weight) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.color = color;
        this.modelName = modelName;
        this.direction = Direction.NORTH;
        this.x = 0;
        this.y = 0;
        this.weight = weight;
        this.state = new DefaultState();
    }
    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public int getWeight(){return weight;}

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public double getX(){
        return x;
    }
    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public String getModelName() {return modelName;}

    public void setState(CarState state){
        this.state = state;
    }

    public void gas(int gas){
        if (gas < 0){
            gas = 0;
        } else if (gas > 1){
            gas = 1;
        }
        incrementSpeed(gas);
        state.applyState(this);
    }

    public void breaks(int breaks){
        if (breaks < 0){
            breaks = 0;
        } else if (breaks > 1){
            breaks = 1;
        }
        decrementSpeed(breaks);
        state.applyState(this);
    }


    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);

    }

    @Override
    public void move(){
        if (x < 0 || x > 500 || y < 0 || y > 500) {
            switch (direction){
                case NORTH -> direction = Direction.SOUTH;
                case SOUTH -> direction = Direction.NORTH;
                case EAST -> direction = Direction.WEST;
                case WEST -> direction = Direction.EAST;
            }
            stopEngine();
            startEngine();
            x = Math.min(Math.max(x, 0), 500);
            y = Math.min(Math.max(y, 0), 500);
        }
        switch (direction){
            case EAST -> x += currentSpeed;
            case WEST -> x -= currentSpeed;
            case NORTH -> y += currentSpeed;
            case SOUTH -> y -= currentSpeed;
        }
        state.applyState(this);
    }
    @Override
    public void turnLeft() {
        switch (direction){
            case NORTH -> direction = Direction.WEST;
            case SOUTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.NORTH;
            case WEST -> direction = Direction.SOUTH;
        }
    }

    @Override
    public void turnRight() {
        switch (direction){
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }
}
