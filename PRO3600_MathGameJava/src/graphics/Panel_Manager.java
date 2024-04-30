package graphics;

import java.util.ArrayList;
import java.util.List;

public class Panel_Manager {


    private List<Frame_Panel> panel_list;
    private Frame_Panel actual_Groupe_panel;

    public Panel_Manager(Frame_Panel... p){
        panel_list = new ArrayList<>();
        addPanel(Panel_State.DEFAULT);
        actual_Groupe_panel = null;
    }

    public Frame_Panel addPanel(Panel_State pstate){
        int id = panel_list.size();
        Frame_Panel p = new Frame_Panel(pstate, id);
        panel_list.add(p);
        if (actual_Groupe_panel==null){
            actual_Groupe_panel = p;
        }
        return p;
    }

    public String toString(){
        String t = "";
        for (Frame_Panel p :
                panel_list) {
            t+= p.getState()+"; ";
        }
        return t;
    }

    public void initialize(){
        for (Frame_Panel p:panel_list) {
            p._setVisible(false);
        }
    }

    public Frame_Panel getPanelFromState(Panel_State state_searched)  {
        for (Frame_Panel panel :
             panel_list) {
            if (panel.getState() == state_searched){
                return panel;
            }
        }
        if (state_searched != Panel_State.DEFAULT){
            return getPanelFromState(Panel_State.DEFAULT);
        }
       // throw new PanelStateNotLinked();
        return null;
    }

    public void changePanel(Panel_State p_state) {
        for (Frame_Panel gp :
                panel_list) {
            gp._setVisible(false);
        }
        try {
            actual_Groupe_panel = getPanelFromState(p_state);
            actual_Groupe_panel._setVisible();
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

    /*public void changePanel(Panel_State p_state) {
        System.out.println("Présent dans l'ancien panel "+actual_Groupe_panel.toString());
        System.out.println(p_state);
        actual_Groupe_panel._setVisible(false);
        try {
            actual_Groupe_panel = getPanelFromState(p_state);
            actual_Groupe_panel._setVisible();
            System.out.println("Présent dans le nouveau panel "+actual_Groupe_panel.toString());
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }*/


}
