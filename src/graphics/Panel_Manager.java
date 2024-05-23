package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Panel_Manager {


    private List<Frame_Panel> panel_list;
    private Frame_Panel actual_Groupe_panel;

    private JLayeredPane layer_manager;

    public Panel_Manager(){
        panel_list = new ArrayList<>();
        layer_manager = new JLayeredPane();
        layer_manager.setOpaque(false);
        layer_manager.setLayout(null);
        layer_manager.setBounds(0,0,2000, 1000);
        //layer_manager.setPreferredSize(new Dimension(200,0  ));
        addPanel(Panel_State.DEFAULT);
        actual_Groupe_panel = null;
        Interface.fenetre.add(layer_manager);
    }

    public Frame_Panel addPanel(Panel_State pstate){
        int id = panel_list.size();
        Frame_Panel p = new Frame_Panel(pstate, id);
       // p.setLayout(new LayeredPaneLayout(layer_manager));
        panel_list.add(p);
        if (actual_Groupe_panel==null){
            actual_Groupe_panel = p;
        }

        if(pstate == Panel_State.background){
            layer_manager.add(p,0);
           // Interface.fenetre.setComponentZOrder(p,0);

        }else{

            layer_manager.add(p,5);
           // Interface.fenetre.getContentPane().setComponentZOrder(p,4);

        }
        //p.setBounds(0,0, Interface.getScreenSize().width, Interface.getScreenSize().height);


        //layer_manager.moveToFront(p);

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
            if(gp.getState().getLayer()==layer && gp.getState()!=Panel_State.background) {
                layer_manager.setLayer(gp, 2);
                gp._setVisible(false);
            }

        }
    }

    public void changePanel(Panel_State p_state) {
       HideAllPanels();
        //System.out.println("on a tout masqué");
        try {
            actual_Groupe_panel = getPanelFromState(p_state);
            actual_Groupe_panel._setVisible();
            layer_manager.setLayer(actual_Groupe_panel,3);

            //System.out.println("testThread-----------------------------------_è_è");

        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

    public void displayPanelsLayers(){
        for (Frame_Panel fp :
             panel_list) {
            System.out.println("-------------Panel "+ fp.getState()+ " is at layer "+layer_manager.getLayer(fp));
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
            actual_Groupe_panel._setVisible();
            layer_manager.setLayer(actual_Groupe_panel,4);
        }
        catch(NullPointerException e){
            System.out.println("pas de nouveau panneaux activée");
        }
        //System.out.println("fin de l'opération changePanelWithSide");
displayPanelsLayers();
    }

    public void changeSide(Panel_State side_state){
        if(side_state.getLayer()==0){
            System.out.println("Invalide side panel");
            return;
        }
        HidePanelsWithLayer(side_state.getLayer());
        try {
            actual_Groupe_panel = getPanelFromState(side_state);
            layer_manager.moveToFront(actual_Groupe_panel);
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
