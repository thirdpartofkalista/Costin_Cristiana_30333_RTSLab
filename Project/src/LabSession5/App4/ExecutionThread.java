package App4;

import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread{
    Semaphore semaphore;
    int activity_min, activity_max, sleep;

    public ExecutionThread(Semaphore semaphore, int activity_min, int activity_max, int sleep){
        this.semaphore = semaphore;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println(this.getName() + "State 1");
            try {
                semaphore.acquire(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.getName() +"State 2");
            // activity for activity_min to activity_max seconds
            int k = (int) ((long) (Math.random() * (activity_max - activity_min) + activity_min) * 1000);
            for (int i = 0; i < k; i++) {
                i++;
                i--;
            }
            semaphore.release(2);
            System.out.println(this.getName() +"State 3");
            try {
                Thread.sleep(sleep*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.getName() +"State 4");

        }

    }
}
