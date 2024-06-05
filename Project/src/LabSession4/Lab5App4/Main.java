package LabSession4.Lab5App4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int MAX_CARS = 10;
        Semaphore semaphore = new Semaphore(MAX_CARS, true);

        CarQueue northQueue = new CarQueue("NORTH");
        CarQueue southQueue = new CarQueue("SOUTH");
        CarQueue eastQueue = new CarQueue("EAST");
        CarQueue westQueue = new CarQueue("WEST");
        CarQueue[] carQueues = {northQueue, southQueue, eastQueue, westQueue};

        int[] generationRates = new int[4];
        try {
            Files.lines(Paths.get("src/LabSession4/Lab5App4/configuration.txt")).forEach(line -> {
                String[] parts = line.split("=");
                switch (parts[0]) {
                    case "NORTH":
                        generationRates[0] = Integer.parseInt(parts[1]);
                        break;
                    case "SOUTH":
                        generationRates[1] = Integer.parseInt(parts[1]);
                        break;
                    case "EAST":
                        generationRates[2] = Integer.parseInt(parts[1]);
                        break;
                    case "WEST":
                        generationRates[3] = Integer.parseInt(parts[1]);
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Car carGenerator = new Car(carQueues, generationRates);
        new Thread(carGenerator).start();

        new Thread(new TrafficLight(semaphore, northQueue, "NORTH")).start();
        new Thread(new TrafficLight(semaphore, southQueue, "SOUTH")).start();
        new Thread(new TrafficLight(semaphore, eastQueue, "EAST")).start();
        new Thread(new TrafficLight(semaphore, westQueue, "WEST")).start();
    }
}