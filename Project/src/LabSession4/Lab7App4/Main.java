package LabSession4.Lab7App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String []args) {
        Semaphore semaphore = new Semaphore(2);
        new ExeThread(3, 6, 5, semaphore).start();
        new ExeThread(5, 7, 6, semaphore).start();
        new ExeThread(4, 7, 3, semaphore).start();
    }
}
