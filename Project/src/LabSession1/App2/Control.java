package LabSession1.App2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Control implements Observer {
    private Model model;
    private View view;

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        view.updateProgressBars((ArrayList<Integer>) arg);
    }
}
