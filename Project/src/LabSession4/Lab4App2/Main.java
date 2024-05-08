package LabSession4.Lab4App2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock monitor1 = new ReentrantLock();
        Lock monitor2 = new ReentrantLock();
        ExecutionThread t1 = new ExecutionThread(monitor1, monitor2, 2, 4, 4, 6, 4);
        ExecutionThread t2 = new ExecutionThread(monitor2, monitor1, 3, 5, 5, 7, 5);
        t1.start();
        t2.start();
    }
}

