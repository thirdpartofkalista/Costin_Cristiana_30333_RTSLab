
package LabSession1.App2;
import java.util.Observable;
import java.util.Observer;
public class MainApp2 {
    private static final int noOfThreads = 6;
    private static final int processorLoad = 1000000;

    public static void main(String[] args) {
        Model model = new Model(noOfThreads);
        View view = new View(noOfThreads);
        Control controller = new Control(model, view);

        for (int i = 0; i < noOfThreads; i++) {
            new Fir(i, i + 2, model, processorLoad).start();
        }
    }
}