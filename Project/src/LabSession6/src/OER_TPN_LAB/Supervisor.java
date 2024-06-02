package OER_TPN_LAB;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataInteger;
import DataObjects.DataREL;
import DataObjects.DataRELQueue;
import DataObjects.DataTransfer;
import DataOnly.RELQueue;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;


public class Supervisor {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Supervisor Petri";
        pn.NetworkPort = 1082;

        DataREL ps_i1 = new DataREL();
        ps_i1.SetName("ps_i1");
        pn.PlaceList.add(ps_i1);

        DataRELQueue ps_1 = new DataRELQueue();
        ps_1.SetName("ps_1");
        pn.PlaceList.add(ps_1);



        DataInteger ps_2 = new DataInteger();
        ps_2.SetName("ps_2");
        ps_2.SetValue(1);
        pn.PlaceList.add(ps_2);

        DataInteger ps_3 = new DataInteger();
        ps_3.SetName("ps_3");
        ps_3.SetValue(1);
        pn.PlaceList.add(ps_3);

        DataTransfer ps_o1 = new DataTransfer();
        ps_o1.SetName("ps_o1");
        ps_o1.Value = new TransferOperation("localhost", "1080", "p_i1");
        pn.PlaceList.add(ps_o1);

        DataInteger ps_o2 = new DataInteger();
        ps_o2.SetName("ps_o2");
        pn.PlaceList.add(ps_o2);

        DataInteger ps_i2 = new DataInteger();
        ps_i2.SetName("ps_i2");
        pn.PlaceList.add(ps_i2);

        // ts_1

        PetriTransition ts_1 = new PetriTransition(pn);
        ts_1.TransitionName = "ts_1";
        ts_1.InputPlaceName.add("ps_i1");
        ts_1.InputPlaceName.add("ps_1");


        Condition TS1Ct1 = new Condition(ts_1, "ps_i1", TransitionCondition.NotNull);
        Condition TS1Ct2 = new Condition(ts_1, "ps_1", TransitionCondition.NotNull);
        TS1Ct1.SetNextCondition(LogicConnector.AND, TS1Ct2);

        GuardMapping grdTS1 = new GuardMapping();
        grdTS1.condition = TS1Ct1;

        grdTS1.Activations.add(new Activation(ts_1, "ps_i1", TransitionOperation.AddElement, "ps_1"));
        ts_1.GuardMappingList.add(grdTS1);
        ts_1.Delay = 0;
        pn.Transitions.add(ts_1);

        // ts_2
        PetriTransition ts_2 = new PetriTransition(pn);
        ts_2.TransitionName = "ts_2";
        ts_2.InputPlaceName.add("ps_1");
        ts_2.InputPlaceName.add("ps_2");
        ts_2.InputPlaceName.add("ps_3");

        Condition TS2Ct1 = new Condition(ts_2, "ps_1", TransitionCondition.HaveREL);
        Condition TS2Ct2 = new Condition(ts_2, "ps_2", TransitionCondition.NotNull);
        Condition TS2Ct3 = new Condition(ts_2, "ps_3", TransitionCondition.NotNull);

        TS2Ct2.SetNextCondition(LogicConnector.AND, TS2Ct3);
        TS2Ct1.SetNextCondition(LogicConnector.AND, TS2Ct2);


        GuardMapping grdTS2 = new GuardMapping();
        grdTS2.condition = TS2Ct1;

        ArrayList<String> ts2Output = new ArrayList<String>();
        ts2Output.add("ps_o1");
        ts2Output.add("ps_3");
        ts2Output.add("ps_1");
        grdTS2.Activations.add((new Activation(ts_2, "ps_1", TransitionOperation.SendROverNetwork, "ps_o1")));
        grdTS2.Activations.add(new Activation(ts_2,"ps_1", TransitionOperation.PopElement_R_E,  ts2Output));


        ts_2.GuardMappingList.add(grdTS2);
        ts_2.Delay  = 0;
        pn.Transitions.add(ts_2);

        // ts_3
        PetriTransition ts_3 = new PetriTransition(pn);
        ts_3.TransitionName = "ts_3";
        ts_3.InputPlaceName.add("ps_i2");

        Condition TS3Ct1 = new Condition(ts_3, "ps_i2", TransitionCondition.NotNull);
        GuardMapping grdTS3 = new GuardMapping();
        grdTS3.condition = TS3Ct1;

        grdTS3.Activations.add(new Activation(ts_2, "ps_i2", TransitionOperation.Copy, "ps_2"));
        grdTS3.Activations.add(new Activation(ts_2, "ps_i2", TransitionOperation.Move, "ps_o2"));

        ts_3.GuardMappingList.add(grdTS3);
        ts_3.Delay = 0;
        pn.Transitions.add(ts_3);

        pn.Delay = 2000;
        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet  = pn;
        frame.setVisible(true);
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//        PetriNet pn = new PetriNet();
//        pn.PetriNetName = "Supervisor Petri";
//        pn.NetworkPort = 1082;
//
//        DataREL ps_i1 = new DataREL();
//        ps_i1.SetName("ps_i1");
//        pn.PlaceList.add(ps_i1);
//
//        DataRELQueue ps_1 = new DataRELQueue();
//        ps_1.SetName("ps_1");
//        pn.PlaceList.add(ps_1);
//
//        DataInteger ps_2 = new DataInteger();
//        ps_2.SetName("ps_2");
//        ps_2.SetValue(1);
//        pn.PlaceList.add(ps_2);
//
//        DataInteger ps_3 = new DataInteger();
//        ps_3.SetName("ps_3");
//        ps_3.SetValue(1);
//        pn.PlaceList.add(ps_3);
//
//        DataInteger ps_o2 = new DataInteger();
//        ps_o2.SetName("ps_o2");
//        pn.PlaceList.add(ps_o2);
//
//        DataInteger ps_i2 = new DataInteger();
//        ps_i2.SetName("ps_i2");
//        pn.PlaceList.add(ps_i2);
//
//        DataTransfer ps_o1 = new DataTransfer();
//        ps_o1.SetName("ps_o1");
//        ps_o1.Value = new TransferOperation("localhost", "1080", "p_i1");
//        pn.PlaceList.add(ps_o1);
//
//        PetriTransition ts_1 = new PetriTransition(pn);
//        ts_1.TransitionName = "ts_1";
//        ts_1.InputPlaceName.add("ps_i1");
//        ts_1.InputPlaceName.add("ps_1");
//
//
//        Condition Ts1Cts1 = new Condition(ts_1, "ps_i1", TransitionCondition.NotNull);
//        Condition Ts1Cts2 = new Condition(ts_1, "ps_1", TransitionCondition.NotNull);
//        Ts1Cts1.SetNextCondition(LogicConnector.AND, Ts1Cts2);
//
//        GuardMapping grdT1 = new GuardMapping();
//        grdT1.condition = Ts1Cts1;
//
//        grdT1.Activations.add(new Activation(ts_1, "ps_i1", TransitionOperation.AddElement, "ps_1"));
//        ts_1.GuardMappingList.add(grdT1);
//        ts_1.Delay = 0;
//        pn.Transitions.add(ts_1);
//
//        PetriTransition ts_2 = new PetriTransition(pn);
//        ts_2.TransitionName = "ts_2";
//        ts_2.InputPlaceName.add("ps_1");
//
//        Condition T2Ct1 = new Condition(ts_2, "ps_1", TransitionCondition.HaveREL);
//        Condition T2Ct2 = new Condition(ts_2, "ps_2", TransitionCondition.NotNull);
//        Condition T2Ct3 = new Condition(ts_2, "ps_3", TransitionCondition.NotNull);
//        T2Ct2.SetNextCondition(LogicConnector.AND, T2Ct3);
//        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);
//
//        GuardMapping grdT2 = new GuardMapping();
//        grdT2.condition = T2Ct1;
//
//        ArrayList<String> outLoc = new ArrayList<String>();
//        outLoc.add("ps_o1");
//        outLoc.add("ps_3");
//        grdT2.Activations.add(new Activation(ts_2, "ps_1", TransitionOperation.PopElement_R_E, outLoc));
//        grdT2.Activations.add(new Activation(ts_2, "ps_1", TransitionOperation.SendROverNetwork, "ps_o1"));
//
//        ts_2.GuardMappingList.add(grdT2);
//        ts_2.Delay = 0;
//        pn.Transitions.add(ts_2);
//
//        PetriTransition ts_3 = new PetriTransition(pn);
//        ts_3.TransitionName = "ts_3";
//        ts_3.InputPlaceName.add("ps_i2");
//
//        Condition T3Ct1 = new Condition(ts_3, "ps_i2", TransitionCondition.NotNull);
//        GuardMapping grdT3 = new GuardMapping();
//        grdT3.condition = T3Ct1;
//
//        grdT3.Activations.add(new Activation(ts_2, "ps_i2", TransitionOperation.Copy, "ps_2"));
//        grdT3.Activations.add(new Activation(ts_2, "ps_i2", TransitionOperation.Move, "ps_o2"));
//
//        ts_3.GuardMappingList.add(grdT3);
//        ts_3.Delay = 0;
//        pn.Transitions.add(ts_3);
//
//
//        pn.Delay = 3000;
//        PetriNetWindow frame = new PetriNetWindow(false);
//        frame.petriNet = pn;
//        frame.setVisible(true);
    }

}
