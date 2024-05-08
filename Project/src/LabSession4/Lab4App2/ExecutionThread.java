package LabSession4.Lab4App2;

import java.util.concurrent.locks.Lock;

public class ExecutionThread extends Thread{
    Lock monitor1;
    Lock monitor2;
    int minActivity1;
    int maxActivity1;
    int minActivity2;
    int maxActivity2;

    int delay;

    public ExecutionThread(Lock monitor1, Lock monitor2, int minActivity1, int maxActivity1, int minActivity2, int maxActivity2, int delay) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.minActivity1 = minActivity1;
        this.maxActivity1 = maxActivity1;
        this.minActivity2 = minActivity2;
        this.maxActivity2 = maxActivity2;
        this.delay = delay;
    }
    public void run() {
        System.out.println(this.getName() + " STATE - 1");
        int k = (int) Math.round(Math.random() * (this.maxActivity1 - this.minActivity1) + this.minActivity1);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        if (monitor1.tryLock()) {
            try {
                System.out.println(this.getName() + " STATE - 2");
                k = (int) Math.round(Math.random() * (this.maxActivity2 - this.minActivity2) + this.minActivity2);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                if (monitor2.tryLock()) {
                    try {
                        System.out.println(this.getName() + " STATE - 3");
                        Thread.sleep(this.delay * 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        monitor2.unlock();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                monitor1.unlock();
            }
            System.out.println(this.getName() + " STATE - 4 ");
        }
    }
}
