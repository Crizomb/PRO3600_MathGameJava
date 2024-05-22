package graphics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Panel_Manager {


    private List<Frame_Panel> panel_list;
    private Frame_Panel actual_Groupe_panel;

   // private JLayeredPane layer_manager;

    public Panel_Manager(){
        panel_list = new ArrayList<>();
     //   layer_manager = new JLayeredPane();
       // layer_manager.setOpaque(false);
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
       // layer_manager.add(p,0);
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
        /*if (state_searched != Panel_State.DEFAULT){
            System.out.println("oui cest le defaut");
            return getPanelFromState(Panel_State.DEFAULT);
        }*/
        System.out.println(state_searched+  " is the situation");
        assert 1 == 0;
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
       //     layer_manager.moveToBack(gp);

        }
    }

    public void changePanel(Panel_State p_state) {
       HideAllPanels();
        //System.out.println("on a tout masqué");
        try {
            actual_Groupe_panel = getPanelFromState(p_state);
            actual_Groupe_panel._setVisible();
            //System.out.println("testThread-----------------------------------_è_è");

        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

    public void changePanelWithSide(Panel_State p_state, Panel_State side_panel) {
        if(side_panel.getLayer()==0){
            System.out.println("Invalide side panel");
            assert 1==0;
            return;
        }
         changePanel(p_state);
        //System.out.println("pour être sur ((((((((((((((((((((((((((((((((((((((((((((");
        try {
            actual_Groupe_panel = getPanelFromState(side_panel);
            System.out.println(actual_Groupe_panel.getState().toString()+ " is the pstate-------------------");
           // layer_manager.moveToFront(actual_Groupe_panel);
            actual_Groupe_panel._setVisible();
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
        //System.out.println("fin de l'opération changePanelWithSide");

    }

    public void changeSide(Panel_State side_state){
        if(side_state.getLayer()==0){
            System.out.println("Invalide side panel");
            return;
        }
        HidePanelsWithLayer(side_state.getLayer());
        try {
            actual_Groupe_panel = getPanelFromState(side_state);
        //    layer_manager.moveToFront(actual_Groupe_panel);
            actual_Groupe_panel._setVisible();
            System.out.println("pannel trouvé");
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
