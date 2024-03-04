package LabSession1.App2;

import javax.swing.*;
import java.util.ArrayList;

public class View {
    private JFrame frame;
    private ArrayList<JProgressBar> progressBars;

    public View(int nrThreads) {
        frame = new JFrame("Progress Bars");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        progressBars = new ArrayList<>();
        for (int i = 0; i < nrThreads; i++) {
            JProgressBar progressBar = new JProgressBar();
            progressBar.setMaximum(1000);
            progressBar.setValue(0);
            panel.add(progressBar);
            progressBars.add(progressBar);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateProgressBars(ArrayList<Integer> progressValues) {
        for (int i = 0; i < progressBars.size(); i++) {
            progressBars.get(i).setValue(progressValues.get(i));
        }
    }
}
