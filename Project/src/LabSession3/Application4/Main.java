package LabSession3.Application4;

public class Main {
    public static void main(String []args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(1);
        ExecutionThread t1 = new ExecutionThread(monitor1, monitor2, 2, 3, 7);
        SecondThread t2 = new SecondThread(monitor1, 3, 5, 5, t1);
        SecondThread t3 = new SecondThread(monitor1, 4, 7, 3, t1);
        t1.start();
        t2.start();
        t3.start();

    }
}