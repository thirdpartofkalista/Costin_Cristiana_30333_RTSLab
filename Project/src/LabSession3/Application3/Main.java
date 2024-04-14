package LabSession3.Application3;

public class Main {
    public static void main(String []args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(1);
        new ExecutionThread(monitor1, monitor2, 2, 4, 4, 6, 4).start();
        new ExecutionThread(monitor2, monitor1, 3, 5, 5, 7, 5).start();
    }
}