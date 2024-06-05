package LabSession4.Lab5App4;

public class CarQueue {
    private int carCount = 0;
    private final String direction;

    public CarQueue(String direction) {
        this.direction = direction;
    }

    public synchronized void addCar() {
        carCount++;
    }

    public synchronized int getCarCount() {
        return carCount;
    }

    public synchronized void releaseCars(int count) {
        carCount = Math.max(0, carCount - count);
    }

    public String getDirection() {
        return direction;
    }
}
