
package LabSession1.App2;

public class MainApp2 {
    private static final int noOfThreads = 6;
    private static final int processorLoad = 1000000;

    public static void main(String[] args) {
        Model []model = new Model[noOfThreads];
        View view = new View(noOfThreads);

        for (int i = 0; i < noOfThreads; i++) {
          model[i] = new Model(i,i + 2, processorLoad, view);
          model[i].addObserver(view);
          model[i].start();
        }
    }
}