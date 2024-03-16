package LabSession1.App2;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable implements Runnable {
    private ArrayList<Integer> progressValues;
    public int c = 0;
    public int id;
    public int priority;
    View win;
    Thread thread;
    public int processorLoad;

    public Model(int id, int priority, int processorLoad, View win) {
        this.id = id;
        thread = new Thread(this);
        this.thread.setPriority(priority);
        this.processorLoad = processorLoad;
        this.win = win;

    }

    public void setProgressValue(int id, int val) {
        progressValues.set(id, val);
        setChanged();
        notifyObservers(progressValues);
    }
    public void start() {
        if (this.thread != null) {
            thread.start();
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setProcessorLoad(int processorLoad) {
        this.processorLoad = processorLoad;
    }

    public void setT(Thread thread) {
        this.thread = thread;
    }

    public int getId() {
        return id;
    }

    public int getProcessLoad() {
        return processorLoad;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (this.c < 1000) {
            for(int j=0;j<this.processorLoad;j++){
                j++;
            }
            this.c++;
            this.setChanged();
            this.notifyObservers();
        }
    }
}
