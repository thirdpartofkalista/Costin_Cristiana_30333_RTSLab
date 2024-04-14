package LabSession3.Application2;

public class Main {
    public static void main(String[] args) {
        Integer monitor = new Integer(1);
        Integer monitor1 = new Integer(1);
        new ExecutionThread(monitor, 4, 2, 4).start();
        new ExecutionThread(monitor1, 3, 3, 6).start();
        new ExeThread(monitor, monitor1, 3, 6, 3).start();

    }
}
