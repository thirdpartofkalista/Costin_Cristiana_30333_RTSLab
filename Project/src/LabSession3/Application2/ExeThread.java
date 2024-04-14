package LabSession3.Application2;

public class ExeThread extends Thread{
    Integer monitor1;
    Integer monitor2;
    int minActivity;
    int maxActivity;
    int delay;
    public ExeThread(Integer monitor1, Integer monitor2, int minActivity, int maxActivity, int delay) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.delay = delay;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        synchronized (this.monitor1) {
            synchronized (this.monitor2) {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (this.maxActivity - this.minActivity) + this.minActivity);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                try {
                    Thread.sleep(Math.round(Math.random() * this.delay * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(this.getName() + " - STATE 3");
    }
}
