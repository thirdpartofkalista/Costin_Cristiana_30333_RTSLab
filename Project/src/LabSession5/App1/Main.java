package LabSession5.App1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore P9 = new Semaphore(1);
        Semaphore P10 = new Semaphore(1);

        CyclicBarrier T8 = new CyclicBarrier(2);

        Fir t1 = new Fir(P9, P10, T8, 2, 4, 4, 6, 4);
        Fir t2 = new Fir(P9, P10, T8, 3, 5, 5, 6, 5);

        t1.start();
        t2.start();
    }
}
