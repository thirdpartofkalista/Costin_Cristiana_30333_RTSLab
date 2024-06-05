package App4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutionThread t1 = new ExecutionThread(semaphore, 3, 6, 5);
        ExecutionThread t2 = new ExecutionThread(semaphore, 4, 7, 3);
        ExecutionThread t3 = new ExecutionThread(semaphore, 5, 7, 6);

        t1.start();
        t2.start();
        t3.start();

    }
}