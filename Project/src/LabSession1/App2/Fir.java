package LabSession1.App2;
import LabSession1.App1.Window;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
public class Fir extends Observable implements Runnable {

    int id;
    Thread t = new Thread();

    Window win;

    int processorLoad;

    Fir(int id,int priority,Window win, int procLoad){

        this.id=id;

        this.win=win;

        this.processorLoad=procLoad;

       // this.setPriority(priority);

    }

    public void run(){

        int c=0;

        while(c<1000){

            for(int j=0;j<this.processorLoad;j++){

                j++;//j--;

            }
            c++; this.win.setProgressValue(id, c); } } }