package LabSession4.Lab5App4;

import java.util.concurrent.Semaphore;


public class TrafficLight implements Runnable {
    private final Semaphore semaphore;
    private final CarQueue carQueue;
    private final String direction;

    public TrafficLight(Semaphore semaphore, CarQueue carQueue, String direction) {
        this.semaphore = semaphore;
        this.carQueue = carQueue;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cars = carQueue.getCarCount();
                if (cars >= 2) {
                    int permits = Math.min(cars, 10); // Limit to 10 cars
                    semaphore.acquire(permits);
                    System.out.println(direction + " light green for " + permits + " seconds. Releasing " + permits + " cars.");
                    Thread.sleep(permits * 1000); // Simulate cars passing
                    carQueue.releaseCars(permits);
                    semaphore.release(permits);
                }
                Thread.sleep(1000); // Check every second
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}