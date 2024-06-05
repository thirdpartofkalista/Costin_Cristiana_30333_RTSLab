package Colocviu;
import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataObjects.DataTransfer;
import DataOnly.SubPetri;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;
public class Colocviu30323B {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1080;

        // ----------------------- sub petri ------------------------------------
        PetriNet spn = new PetriNet();
        spn.PetriNetName = "Sub Petri";

        DataFloat subConstantValue1 = new DataFloat();
        subConstantValue1.SetName("subConstantValue1");
        subConstantValue1.SetValue(1.0f);
        spn.ConstantPlaceList.add(subConstantValue1);

        DataFloat subConstantValue2 = new DataFloat();
        subConstantValue2.SetName("subConstantValue2");
        subConstantValue2.SetValue(10.0f);
        spn.ConstantPlaceList.add(subConstantValue2);

        DataFloat p10 = new DataFloat();
        p10.SetName("p10");
        spn.PlaceList.add(p10);

        DataFloat p13 = new DataFloat();
        p13.SetName("p13");
        spn.PlaceList.add(p13);

        DataFloat p11 = new DataFloat();
        p11.SetName("p11");
        spn.PlaceList.add(p11);

        DataTransfer p12 = new DataTransfer();
        p12.SetName("p12");
        p12.Value = new TransferOperation("localhost", "1080", "p7");
        spn.PlaceList.add(p12);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(spn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p10");
        t6.InputPlaceName.add("p13");

        Condition T6Ct1 = new Condition(t6, "p10", TransitionCondition.IsNull);
        Condition T6Ct2 = new Condition(t6, "p13", TransitionCondition.NotNull);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;

        ArrayList<String> lstInput = new ArrayList<String>();
        lstInput.add("p13");
        lstInput.add("subConstantValue1");
        grdT6.Activations.add(new Activation(t6, lstInput, TransitionOperation.Add, "p11"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        spn.Transitions.add(t6);

        // T7 ========================================================

        PetriTransition t7 = new PetriTransition(spn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("p11");

        Condition T7Ct1 = new Condition(t7, "p11", TransitionCondition.NotNull);
        Condition T7Ct2 = new Condition(t7, "p11", TransitionCondition.LessThanOrEqual, "subConstantValue2");
        T7Ct1.SetNextCondition(LogicConnector.AND, T7Ct2);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;

        grdT7.Activations.add(new Activation(t7, "p11", TransitionOperation.SendOverNetwork, "p12"));
        grdT7.Activations.add(new Activation(t7, "p11", TransitionOperation.Copy, "p13"));
        grdT7.Activations.add(new Activation(t7, "p11", TransitionOperation.Move, "p12"));

        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        spn.Transitions.add(t7);

        // T8 ===========================================================
        PetriTransition t8 = new PetriTransition(spn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("p11");

        Condition T8Ct1 = new Condition(t8, "p11", TransitionCondition.NotNull);
        Condition T8Ct2 = new Condition(t8, "p11", TransitionCondition.MoreThan, "subConstantValue2");
        T8Ct1.SetNextCondition(LogicConnector.AND, T8Ct2);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;

        grdT8.Activations.add(new Activation(t8, "", TransitionOperation.StopPetriNet, ""));

        t8.GuardMappingList.add(grdT7);
        t8.Delay = 0;
        spn.Transitions.add(t8);

        spn.Delay = 3000;

        // Main Petri ================================================
        DataSubPetriNet subPetriNet = new DataSubPetriNet();
        subPetriNet.SetName("SubPetri2");
        SubPetri sptr = new SubPetri(spn);
        subPetriNet.SetValue(sptr);
        pn.ConstantPlaceList.add(subPetriNet);

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        pn.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        pn.PlaceList.add(p1);

        DataSubPetriNet p2 = new DataSubPetriNet();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataFloat p3 = new DataFloat();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataFloat p4 = new DataFloat();
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataFloat p5 = new DataFloat();
        p5.SetName("p5");
        p5.SetValue(1.0f);
        pn.PlaceList.add(p5);

        DataFloat p6 = new DataFloat();
        p6.SetName("p6");
        pn.PlaceList.add(p6);

        DataFloat p7 = new DataFloat();
        p7.SetName("p7");
        pn.PlaceList.add(p7);

        DataFloat p8 = new DataFloat();
        p8.SetName("p8");
        pn.PlaceList.add(p8);

        DataFloat p9 = new DataFloat();
        p9.SetName("p9");
        pn.PlaceList.add(p9);

        // T0 ========================================================
        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");
        t0.InputPlaceName.add("p5");

        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "p5", TransitionCondition.NotNull);
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;

        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p1"));
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p4"));
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p6"));

        t0.GuardMappingList.add(grdT0);
        t0.Delay = 0;
        pn.Transitions.add(t0);

        // T1 =============================================================
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t0, "p1", TransitionCondition.NotNull);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "SubPetri2", TransitionOperation.Move, "p2"));
        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.Move, "p2-p13"));
        grdT1.Activations.add(new Activation(t1, "p2", TransitionOperation.ActivateSubPetri, ""));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2 ===================================================================
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(t2, "p2", TransitionCondition.SubPetriStopped);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        //ask
        grdT2.Activations.add(new Activation(t2, "p2-p12", TransitionOperation.Copy, "p3"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3 ===============================================
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p3");

        Condition T3Ct1 = new Condition(t3, "p3", TransitionCondition.NotNull);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "p3", TransitionOperation.Move, "p5"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4 ====================================================

        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p7");
        t4.InputPlaceName.add("p6");

        Condition T4Ct1 = new Condition(t4, "p7", TransitionCondition.NotNull);
        Condition T4Ct2 = new Condition(t4, "p6", TransitionCondition.NotNull);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;

        grdT4.Activations.add(new Activation(t4, "p7", TransitionOperation.Move, "p8"));
        grdT4.Activations.add(new Activation(t4, "p6", TransitionOperation.Move, "p9"));


        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5 ==============================================================
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p8");

        Condition T5Ct1 = new Condition(t5, "p8", TransitionCondition.NotNull);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(t5, "p8", TransitionOperation.Move, "p6"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 0;
        pn.Transitions.add(t5);

        PetriNetWindow frame = new PetriNetWindow(false);
        pn.Delay = 3000;
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
