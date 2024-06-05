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

public class PN1_And_3 {
    public static void main(String[] args) {
        PetriNet pn1 = new PetriNet();
        pn1.PetriNetName = "PN1";
        pn1.NetworkPort = 1080;

        //Subpetri PN3
        PetriNet pn3 = new PetriNet();
        pn3.PetriNetName = "PN3";

        //places of subpetri
        DataFloat p8 = new DataFloat();
        p8.SetName("p_8");
        pn3.PlaceList.add(p8);

        DataFloat percentagePlace = new DataFloat();
        percentagePlace.SetName("percentagePlace");
        percentagePlace.SetValue(0.01f);
        pn3.ConstantPlaceList.add(percentagePlace);

        DataFloat p9 = new DataFloat();
        p9.SetName("p_9");
        pn3.PlaceList.add(p9);

        DataFloat p10 = new DataFloat();
        p10.SetName("p_10");
        pn3.PlaceList.add(p10);

        DataTransfer p11 = new DataTransfer();
        p11.SetName("p_11");
        p11.Value = new TransferOperation("localhost", "1082", "p_7");
        pn3.PlaceList.add(p11);

        //transitions of subpetri PN3
        //T6
        PetriTransition t6 = new PetriTransition(pn3);
        t6.InputPlaceName.add("p_8");

        Condition t6c1 = new Condition(t6, "p_8", TransitionCondition.NotNull);

        GuardMapping g1 = new GuardMapping();
        g1.condition = t6c1;
        ArrayList<String> multiplyPlaces = new ArrayList<>();
        multiplyPlaces.add("p_8");
        multiplyPlaces.add("percentagePlace");

        g1.Activations.add(new Activation(t6, multiplyPlaces, TransitionOperation.Prod, "p_9"));

        t6.GuardMappingList.add(g1);
        t6.Delay = 0;
        pn3.Transitions.add(t6);

        //T7
        PetriTransition t7 = new PetriTransition(pn3);
        t7.InputPlaceName.add("p_9");

        Condition t7c1 = new Condition(t7, "p_9", TransitionCondition.NotNull);

        GuardMapping g2 = new GuardMapping();
        g2.condition=t7c1;
        g2.Activations.add(new Activation(t7, "p_9", TransitionOperation.Copy, "p_10"));
        g2.Activations.add(new Activation(t7, "p_9", TransitionOperation.SendOverNetwork, "p_11"));

        t7.GuardMappingList.add(g2);
        t7.Delay = 0;
        pn3.Transitions.add(t7);

        //T8
        PetriTransition t8 = new PetriTransition(pn3);
        t8.InputPlaceName.add("p_10");

        Condition t8c1 = new Condition(t8, "p_10", TransitionCondition.NotNull);

        GuardMapping g3 = new GuardMapping();
        g3.condition = t8c1;
        g3.Activations.add(new Activation(t8, "", TransitionOperation.StopPetriNet, ""));

        t8.GuardMappingList.add(g3);
        t8.Delay = 0;
        pn3.Transitions.add(t8);

        pn3.Delay = 3000;

        //places of Parent PN1
        DataSubPetriNet subPetriNet = new DataSubPetriNet();
        subPetriNet.SetName("SubPetri");
        SubPetri sp = new SubPetri(pn3);
        subPetriNet.SetValue(sp);
        pn1.ConstantPlaceList.add(subPetriNet);

        DataFloat p0 = new DataFloat();
        p0.SetName("p_0");
        p0.SetValue(1.0f);
        pn1.PlaceList.add(p0);

        DataFloat p3 = new DataFloat();
        p3.SetName("p_3");
        pn1.PlaceList.add(p3);

        DataSubPetriNet p1 = new DataSubPetriNet();
        p1.SetName("p_1");
        pn1.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p_2");
        pn1.PlaceList.add(p2);

        //transitions of parent pn PN1
        //T0
        PetriTransition t0 = new PetriTransition(pn1);
        t0.InputPlaceName.add("p_0");
        t0.InputPlaceName.add("p_3");

        Condition t0c1 = new Condition(t0, "p_0", TransitionCondition.NotNull);
        Condition t0c2 = new Condition(t0, "p_3", TransitionCondition.NotNull);

        t0c1.SetNextCondition(LogicConnector.AND, t0c2);

        GuardMapping g4 = new GuardMapping();
        g4.condition = t0c1;
        g4.Activations.add(new Activation(t0, "SubPetri", TransitionOperation.Move, "p_1"));
        g4.Activations.add(new Activation(t0, "p_3", TransitionOperation.Move, "p_1-p_8"));
        g4.Activations.add(new Activation(t0, "p_1", TransitionOperation.ActivateSubPetri, ""));

        t0.GuardMappingList.add(g4);
        t0.Delay = 0;
        pn3.Transitions.add(t7);

        //T1
        PetriTransition t1 = new PetriTransition(pn1);
        t1.TransitionName = "t_1";
        t1.InputPlaceName.add("p_1");

        Condition t1c1 = new Condition(t1, "p_1", TransitionCondition.NotNull);
        Condition t1c2 = new Condition(t1, "p_1", TransitionCondition.SubPetriStopped);

        t1c1.SetNextCondition(LogicConnector.AND, t1c2);

        GuardMapping g5 = new GuardMapping();
        g5.condition = t1c1;
        g5.Activations.add(new Activation(t1, "p_1-p_10", TransitionOperation.Copy, "p_2"));

        t1.GuardMappingList.add(g5);
        t1.Delay = 0;
        pn1.Transitions.add(t1);

        //T2
        PetriTransition t2 = new PetriTransition(pn1);
        t2.TransitionName = "t_2";
        t2.InputPlaceName.add("p_2");

        Condition t2c1 = new Condition(t1, "p_2", TransitionCondition.NotNull);

        GuardMapping g6 = new GuardMapping();
        g6.condition = t2c1;
        g6.Activations.add(new Activation(t2, "p_2", TransitionOperation.Move, "p_0"));

        t2.GuardMappingList.add(g6);
        t2.Delay = 3;
        pn1.Transitions.add(t2);

        System.out.println("Exp1 started \n ------------------------------");
        pn1.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn1;
        frame.setVisible(true);
    }
}
