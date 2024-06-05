package LabSession4.Lab5App4;

import java.util.Random;

public class Car implements Runnable {
    private final CarQueue[] carQueues;
    private final int[] generationRates;

    public Car(CarQueue[] carQueues, int[] generationRates) {
        this.carQueues = carQueues;
        this.generationRates = generationRates;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                for (int i = 0; i < carQueues.length; i++) {
                    if (random.nextInt(10) < generationRates[i]) {
                        carQueues[i].addCar();
                        System.out.println("Car added to " + carQueues[i].getDirection() + " queue. Total: " + carQueues[i].getCarCount());
                    }
                }
                Thread.sleep(1000); // Check every second
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
