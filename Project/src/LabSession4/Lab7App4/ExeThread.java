package LabSession4.Lab7App4;

import java.util.concurrent.Semaphore;

public class ExeThread extends Thread{
    int minActivity;
    int maxActivity;
    int delay;

    Semaphore semaphore;

    public ExeThread(int minActivity, int maxActivity, int delay, Semaphore semaphore) {
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.delay = delay;
        this.semaphore = semaphore;
    }
    public void run() {
        while (true) {
            try {
                System.out.println(this.getName() + " STATE - 1");
                this.semaphore.acquire(2);
                System.out.println(this.getName() + " STATE - 2");
                int k = (int) Math.round(Math.random() * (this.maxActivity - this.minActivity) + this.minActivity);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                this.semaphore.release(2);
                System.out.println(this.getName() + " STATE - 3");
                Thread.sleep(delay * 500);
                System.out.println(this.getName() + " STATE - 4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}