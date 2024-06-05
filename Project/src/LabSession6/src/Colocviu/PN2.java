package Colocviu;

import Components.*;
import DataObjects.DataFloat;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class PN2 {
    public static void main(String[] args) {
        PetriNet pn2 = new PetriNet();
        pn2.PetriNetName = "PN2";
        pn2.NetworkPort = 1082;

        //places
        DataFloat p4 = new DataFloat();
        p4.SetName("p_4");
        p4.SetValue(1.0f);
        pn2.PlaceList.add(p4);

        DataFloat p7 = new DataFloat();
        p7.SetName("p_7");
        pn2.PlaceList.add(p7);

        DataFloat p5 = new DataFloat();
        p5.SetName("p_5");
        pn2.PlaceList.add(p5);

        DataFloat p6 = new DataFloat();
        p6.SetName("p_6");
        pn2.PlaceList.add(p6);

        //transitions
        //T3
        PetriTransition t3 = new PetriTransition(pn2);
        t3.TransitionName = "t_3";
        t3.InputPlaceName.add("p_4");
        t3.InputPlaceName.add("p_7");

        Condition t3c1 = new Condition(t3, "p_4", TransitionCondition.NotNull);
        Condition t3c2 = new Condition(t3, "p_7", TransitionCondition.NotNull);

        t3c1.SetNextCondition(LogicConnector.AND, t3c2);

        GuardMapping g1 = new GuardMapping();
        g1.condition = t3c1;

        g1.Activations.add(new Activation(t3, "p_7", TransitionOperation.Move, "p_5"));

        t3.GuardMappingList.add(g1);
        t3.Delay = 0;
        pn2.Transitions.add(t3);

        //T4
        PetriTransition t4 = new PetriTransition(pn2);
        t4.TransitionName = "t_4";
        t4.InputPlaceName.add("p_5");

        Condition t4c1 = new Condition(t4, "p_5", TransitionCondition.NotNull);

        GuardMapping g2 = new GuardMapping();
        g2.condition = t4c1;

        g2.Activations.add(new Activation(t4, "p_5", TransitionOperation.Move, "p_6"));

        t4.GuardMappingList.add(g2);
        t4.Delay = 0;
        pn2.Transitions.add(t4);

        //T5
        PetriTransition t5 = new PetriTransition(pn2);
        t5.TransitionName = "t_5";
        t5.InputPlaceName.add("p_6");

        Condition t5c1 = new Condition(t5, "p_6", TransitionCondition.NotNull);

        GuardMapping g3 = new GuardMapping();
        g3.condition = t5c1;

        g3.Activations.add(new Activation(t5, "p_6", TransitionOperation.Move, "p_4"));

        t5.GuardMappingList.add(g3);
        t5.Delay = 0;
        pn2.Transitions.add(t5);

        System.out.println("Exp1 started \n ------------------------------");
        pn2.Delay = 3000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn2;
        frame.setVisible(true);
    }
}
