package LabSession3.Application4;

public class ExecutionThread extends Thread{
    Integer monitor;
    int minActivity;
    int maxActivity;
    int delay;

    public ExecutionThread (Integer monitor, int minActivity, int maxActivity, int delay) {
        this.monitor = monitor;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.delay = delay;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " STATE - 1");
            synchronized (monitor) {
                System.out.println(this.getName() + " STATE - 2");
                int k = (int) Math.round(Math.random() * (this.maxActivity - this.minActivity) + this.minActivity);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
            }
            System.out.println(this.getName() + " STATE - 3");
            try {
                Thread.sleep(Math.round(Math.random() * this.delay * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " STATE - 4");
        }
    }
}
