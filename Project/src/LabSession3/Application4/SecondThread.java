package LabSession3.Application4;

public class SecondThread extends Thread{
    int minActivity;
    int maxActivity;
    int delay;

    Integer monitor;
    ExecutionThread th1;
    public SecondThread(Integer monitor, int minActivity, int maxActivity, int delay, ExecutionThread th1){
        this.monitor = monitor;
        this.maxActivity = maxActivity;
        this.minActivity = minActivity;
        this.delay = delay;
        this.th1 = th1;
    }

    public void run(){
        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor){
            try{
                monitor.wait(); //WAIT
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + " STATE - 2");
        int k = (int)Math.round(Math.random() * (this.maxActivity-this.minActivity)+this.minActivity);

        for (int i = 0; i<k* 100000; i++){
            i++; i--;
        }
        System.out.println(this.getName() + " STATE - 3");
        try {
            th1.join();
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("Final state");
    }

}
