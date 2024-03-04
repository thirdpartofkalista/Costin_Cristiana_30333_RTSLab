package LabSession1.App2;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    private ArrayList<Integer> progressValues;

    public Model(int nrThreads) {
        progressValues = new ArrayList<>();
        for (int i = 0; i < nrThreads; i++) {
            progressValues.add(0);
        }
    }

    public void setProgressValue(int id, int val) {
        progressValues.set(id, val);
        setChanged();
        notifyObservers(progressValues);
    }
}
