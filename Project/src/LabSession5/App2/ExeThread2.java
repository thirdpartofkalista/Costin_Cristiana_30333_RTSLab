package LabSession5.App2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

// countdown
public class ExeThread2 extends Thread{
    public int minActivity;
    public int maxActivity;
    public int delay;

    public Lock P9;
    public Lock P10;
    public CountDownLatch T8;

    public ExeThread2(Lock P9,Lock P10, CountDownLatch T8, int minActivity, int maxActivity, int delay) {
        this.P9 = P9;
        this.P10 = P10;
        this.T8 = T8;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.delay = delay;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(this.getName() + " - STATE 1");

            this.P9.lock();
            this.P10.lock();

            System.out.println(this.getName() + " - STATE 2");

            int k = (int) Math.round(Math.random() * (this.maxActivity - this.minActivity) + this.minActivity);
            try {
                Thread.sleep(Math.round(Math.random() * this.delay * 500)); // simulate activity
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.P9.unlock();
            this.P10.unlock();

            System.out.println(this.getName() + " - STATE 3");

            T8.countDown();

            try {
                T8.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
