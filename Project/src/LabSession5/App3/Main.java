package LabSession5.App3;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String []args) {
        Object P6 = new Object();
        Object P10 = new Object();
        CountDownLatch T11 = new CountDownLatch(3);

        ExeThread t1 = new ExeThread(2, 3, 7, P6, P10, T11);
        ExeThread2 t2 = new ExeThread2(3, 5, 2, P6, T11);
        ExeThread2 t3 = new ExeThread2(3, 5, 5, P6, T11);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Transition");
    }

}
