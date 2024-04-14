package LabSession3.Application3;

public class ExecutionThread extends Thread{
    Integer monitor1;
    Integer monitor2;
    int minActivity1;
    int maxActivity1;
    int minActivity2;
    int maxActivity2;

    int delay;

    public ExecutionThread(Integer monitor1, Integer monitor2, int minActivity1, int maxActivity1, int minActivity2, int maxActivity2, int delay) {
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

        synchronized (monitor1) {
            System.out.println(this.getName() + " STATE - 2");
            k = (int) Math.round(Math.random() * (this.maxActivity2 - this.minActivity2) + this.minActivity2);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
        }
        synchronized (monitor2) {
            System.out.println(this.getName() + " STATE - 3");
            try {
                Thread.sleep(Math.round(Math.random() * this.delay * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + " STATE - 4");
    }

}
