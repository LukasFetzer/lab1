package src;

import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Direction direction;
    private double x, y;
    private int weight;

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

    public void gas(int gas){
        if (gas < 0){
            gas = 0;
        } else if (gas > 1){
            gas = 1;
        }
        incrementSpeed(gas);
    }

    public void breaks(int breaks){
        if (breaks < 0){
            breaks = 0;
        } else if (breaks > 1){
            breaks = 1;
        }
        decrementSpeed(breaks);
    }


    protected abstract double speedFactor();

    public void incrementSpeed(double amount){
        if (currentSpeed < enginePower) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        } else {currentSpeed = enginePower;}
    }

    public void decrementSpeed(double amount){
        if (currentSpeed > 0) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        } else {currentSpeed = 0;}

    }

    @Override
    public void move(){
        switch (direction){
            case EAST -> x += currentSpeed;
            case WEST -> x -= currentSpeed;
            case NORTH -> y += currentSpeed;
            case SOUTH -> y -= currentSpeed;
        }
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
