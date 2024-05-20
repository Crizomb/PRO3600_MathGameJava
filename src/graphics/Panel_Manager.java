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
        HideAllPanels();
    }

    public Frame_Panel getPanelFromState(Panel_State state_searched)  {
        for (Frame_Panel panel :
             panel_list) {
            if (panel.getState() == state_searched){
                return panel;
            }
        }
        if (state_searched != Panel_State.DEFAULT){
            System.out.println("oui cest le defaut");
            return getPanelFromState(Panel_State.DEFAULT);
        }
       // throw new PanelStateNotLinked();
        return null;
    }

    private void HideAllPanels(){
        for (int i = 0; i < 2; i++) {
            HidePanelsWithLayer(i);
        }
    }

    private void HidePanelsWithLayer(int layer){
        for (Frame_Panel gp :
                panel_list) {
            if(gp.getState().getLayer()==layer)
            gp._setVisible(false);
        }
    }

    public void changePanel(Panel_State p_state) {
       HideAllPanels();
        System.out.println("on a tout masqué");
        try {
            actual_Groupe_panel = getPanelFromState(p_state);
            actual_Groupe_panel._setVisible();
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

    public void changePanelWithSide(Panel_State p_state, Panel_State side_panel) {
        if(side_panel.getLayer()==0){
            System.out.println("Invalide side panel");
            return;
        }
         changePanel(p_state);
        try {
            actual_Groupe_panel = getPanelFromState(side_panel);
            System.out.println(actual_Groupe_panel.getState().toString()+ " is the pstate-------------------");
            actual_Groupe_panel._setVisible();
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

    /*public void changeSide(Panel_State side_state){
        if(side_state.getLayer()==0){
            System.out.println("Invalide side panel");
            return;
        }
        HidePanelsWithLayer(side_state.getLayer());
        try {
            actual_Groupe_panel = getPanelFromState(side_state);
            actual_Groupe_panel._setVisible();
            System.out.println("pannel trouvé");
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }*/






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
