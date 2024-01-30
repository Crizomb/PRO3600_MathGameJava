package graphics;

import java.util.ArrayList;
import java.util.List;

public class Panel_Manager {
    private List<Panel> panel_list;
    private int actual_panel;

    public Panel_Manager(){
        panel_list = new ArrayList<>();
        actual_panel = 0;
    }

    public void initialize(){
        for (Panel p:panel_list) {
            p.setVisible(false);
        }
    }

    public void change_panel(int i){
        panel_list.get(actual_panel).setVisible(false);
        panel_list.get(i).setVisible();  //TODO changer p au panneau actuel
    }

}
