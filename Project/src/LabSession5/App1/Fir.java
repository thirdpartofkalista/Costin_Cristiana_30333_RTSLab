package LabSession5.App1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Fir extends Thread {

    public Semaphore P9;
    public Semaphore P10;

    public int minActivityP1;
    public int maxActivityP1;

    public int minActivityP2;
    public int maxActivityP2;

    public int delay;
    public CyclicBarrier T8;


    public Fir(Semaphore P9, Semaphore P10, CyclicBarrier T8, int minActivityP1, int maxActivityP1, int minActivityP2, int maxActivityP2, int delay) {
        this.P9 = P9;
        this.P10 = P10;
        this.T8 = T8;
        this.minActivityP1 = minActivityP1;
        this.maxActivityP1 = maxActivityP1;
        this.minActivityP2 = minActivityP1;
        this.maxActivityP2 = maxActivityP2;
        this.delay = delay;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            try {
                this.P9.acquire(1);
                this.P10.acquire(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (this.maxActivityP1 - this.minActivityP1) + this.minActivityP1);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }

            System.out.println(this.getName() + " - STATE 3");

            try {
                Thread.sleep(Math.round(Math.random() * this.delay * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.P9.release(1);
            this.P10.release(1);

            System.out.println(this.getName() + " - STATE 4");

            try {
                this.T8.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }


    }
}
