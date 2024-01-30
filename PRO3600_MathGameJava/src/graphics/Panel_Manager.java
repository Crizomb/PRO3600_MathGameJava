package graphics;

import java.util.ArrayList;
import java.util.List;

public class Panel_Manager {


    private List<Panel> panel_list;
    private Panel actual_panel;

    public Panel_Manager(Panel... p){
        panel_list = new ArrayList<>();
        actual_panel = panel_list.get(0);
    }

    public String toString(){
        String t = "";
        for (Panel p :
                panel_list) {
            t+= p.getState()+"; ";
        }
        return t;
    }

    public void initialize(){
        for (Panel p:panel_list) {
            p.setVisible(false);
        }
    }

    public Panel getPanelFromState(Panel_State state_searched) throws PanelStateNotLinked {
        for (Panel panel :
             panel_list) {
            if (panel.getState() == state_searched){
                return panel;
            }
        }
        throw new PanelStateNotLinked();
    }

    public void changePanel(Panel_State p_state) {
        actual_panel.setVisible(false);
        try {
            actual_panel = getPanelFromState(p_state);
            actual_panel.setVisible();
        }
        catch(PanelStateNotLinked e){
            System.out.println("pas de nouveau panneaux activée");
        }
    }

}
