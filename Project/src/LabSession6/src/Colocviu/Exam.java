package Colocviu;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataObjects.DataTransfer;
import DataOnly.SubPetri;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Exam {

    public static void main(String[] args) {
        // main ------------------------------------------
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "PN1";
        pn.NetworkPort = 1080;

        // subpetri net --------------------------------------
        PetriNet spn = new PetriNet();
        spn.PetriNetName = "PN2";

        // we start with the spn -------------------------

        DataFloat subConstantValue1 = new DataFloat();
        subConstantValue1.SetName("subConstantValue1");
        subConstantValue1.SetValue(0.02f);
        spn.ConstantPlaceList.add(subConstantValue1);

        DataFloat p11 = new DataFloat();
        p11.SetName("p11");
        p11.SetValue(1.0f);
        spn.PlaceList.add(p11);

        DataFloat p12 = new DataFloat();
        p12.SetName("p12");
        spn.PlaceList.add(p12);

        DataTransfer p13 = new DataTransfer();
        p13.SetName("p13");
        p13.Value = new TransferOperation("localhost", "1081", "p15");
        spn.PlaceList.add(p13);

        DataFloat p14 = new DataFloat();
        p14.SetName("p14");
        spn.PlaceList.add(p14);

        // T8 transition ---------------------------

        PetriTransition t8 = new PetriTransition(spn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("p11");

        Condition T8Ct1 = new Condition(t8, "p11", TransitionCondition.NotNull);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;

        ArrayList<String> lstInput = new ArrayList<String>();
        lstInput.add("p11");
        lstInput.add("subConstantValue1");
        grdT8.Activations.add(new Activation(t8, lstInput, TransitionOperation.Prod, "p12"));

        t8.GuardMappingList.add(grdT8);
        t8.Delay = 0;
        spn.Transitions.add(t8);

        // T9 transition -------------------------------

        PetriTransition t9 = new PetriTransition(spn);
        t9.TransitionName = "t9";
        t9.InputPlaceName.add("p12");

        Condition T9Ct1 = new Condition(t9, "p12", TransitionCondition.NotNull);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;

        grdT9.Activations.add(new Activation(t9,"p12", TransitionOperation.SendOverNetwork, "p13"));
        grdT9.Activations.add(new Activation(t9,"p12", TransitionOperation.Move, "p13"));
        grdT9.Activations.add(new Activation(t9,"p12", TransitionOperation.Move, "p14"));

        t9.GuardMappingList.add(grdT9);
        t9.Delay = 0;
        spn.Transitions.add(t9);

        // T10 ----------------------------------------------------
        PetriTransition t10 = new PetriTransition(spn);
        t10.TransitionName = "t10";
        t10.InputPlaceName.add("p14");

        Condition T10Ct1 = new Condition(t10, "p14", TransitionCondition.NotNull);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;
        grdT10.Activations.add(new Activation(t10,"", TransitionOperation.StopPetriNet, ""));
        grdT10.Activations.add(new Activation(t10,"p14", TransitionOperation.Move, "p4"));

        t10.GuardMappingList.add(grdT10);
        t10.Delay = 0;
        spn.Transitions.add(t10);

        spn.Delay = 3000;

        // transfer ---------------------------------
        DataSubPetriNet subPetriNet = new DataSubPetriNet();
        subPetriNet.SetName("SubPetri2");
        SubPetri sptr= new SubPetri(spn);
        subPetriNet.SetValue(sptr);
        pn.ConstantPlaceList.add(subPetriNet);

        // main petri net -----------------------------------------------------

        DataSubPetriNet p15 = new DataSubPetriNet();
        p15.SetName("p15");
        pn.PlaceList.add(p15);

        DataFloat p16 = new DataFloat();
        p16.SetName("p16");
        pn.PlaceList.add(p16);

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        pn.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        pn.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataFloat p3 = new DataFloat();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataSubPetriNet p4 = new DataSubPetriNet();
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataFloat p5 = new DataFloat();
        p5.SetName("p5");
        pn.PlaceList.add(p5);

        DataFloat p6 = new DataFloat();
        p6.SetName("p6");
        pn.PlaceList.add(p6);

        DataFloat p10 = new DataFloat();
        p10.SetName("p10");
        pn.PlaceList.add(p10);

        // T0 transition -----------------------------

        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");

        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p1"));
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p2"));

        t0.GuardMappingList.add(grdT0);
        t0.Delay = 0;
        pn.Transitions.add(t0);

        // T1 transition

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.Move, "p3"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T3 transition ---------------------------------------------

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p15");
        t3.InputPlaceName.add("p3");

        Condition T3Ct1 = new Condition(t3, "p15", TransitionCondition.NotNull);
        Condition T3Ct2 = new Condition(t3, "p3", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "p15", TransitionOperation.Copy, "p5"));
        grdT3.Activations.add(new Activation(t3, "p3", TransitionOperation.Move, "p16"));

        t3.GuardMappingList.add(grdT0);
        t3.Delay = 0;
        pn.Transitions.add(t0);

        // T5 - delay ------------------------------------------------

        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p5");

        Condition T5Ct1 = new Condition(t5, "p5", TransitionCondition.NotNull);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "p5", TransitionOperation.Move, "p1"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 3000;
        pn.Transitions.add(t5);

        //T6 transition -----------------------------------------------------

        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p6");

        Condition T6Ct1 = new Condition(t6, "p6", TransitionCondition.NotNull);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(t6, "p6", TransitionOperation.Move, "p2"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn.Transitions.add(t6);

        //T2 -------------------------------------------------

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");
        t2.InputPlaceName.add("p10");

        Condition T2Ct1 = new Condition(t2, "p10", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "p2", TransitionCondition.NotNull);
        T0Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(t2, "SubPetri2", TransitionOperation.Move, "p4"));
        grdT2.Activations.add(new Activation(t2, "p10", TransitionOperation.Move, "p4-p11"));
        grdT2.Activations.add(new Activation(t2, "p4", TransitionOperation.ActivateSubPetri, ""));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T4 --------------------------------------------

        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p4");

        Condition T4Ct1 = new Condition(t4, "p4", TransitionCondition.NotNull);
        Condition T4Ct2 = new Condition(t4, "p4", TransitionCondition.SubPetriStopped);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(t4, "p4-p11", TransitionOperation.Copy, "p6"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);


        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);


    }
}
