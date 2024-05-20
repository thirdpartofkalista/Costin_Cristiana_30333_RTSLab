package LabSession5.App2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock P9 = new ReentrantLock();
        Lock P10 = new ReentrantLock();
        CountDownLatch T8 = new CountDownLatch(3);

        ExeThread t1 = new ExeThread(P9, T8, 2, 4, 4);
        ExeThread t3 = new ExeThread(P10, T8, 2, 5, 5);
        ExeThread2 t2 = new ExeThread2(P9, P10, T8, 3, 6, 3);

        t1.start();
        t2.start();
        t3.start();
    }
}
