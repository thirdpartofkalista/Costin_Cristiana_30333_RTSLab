package LabSession3.Application4;

public class ExecutionThread extends Thread{

    Integer monitor1;
    Integer monitor2;
    int minActivity;
    int maxActivity;
    int delay;

    public ExecutionThread (Integer monitor1, Integer monitor2,  int minActivity, int maxActivity, int delay) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.delay = delay;
    }

    public void run() {
            System.out.println(this.getName() + " STATE - 1");
            try {
                Thread.sleep(Math.round(Math.random() * this.delay * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " STATE - 2");
            int k = (int)Math.round(Math.random() * (this.maxActivity-this.minActivity)+this.minActivity);

            for (int i = 0; i<k* 100000; i++){
                i++; i--;
            }
            synchronized (monitor1){
                monitor1.notifyAll();
            }
            synchronized (monitor2){
                monitor2.notifyAll();
            }
            System.out.println(this.getName() + " STATE - 3");

    }
}
