package graphics;

import java.util.ArrayList;
import java.util.List;

public class Groupe_Panel {
    private List<Graphic_Element> element_panel;
    private Panel_State state;
    private int panel_id;

    public Panel_State getState(){
        return state;
    }

    public void addElementToPanel(Graphic_Element e){
        element_panel.add(e);
    }

    public void addElementToPanel(Graphic_Element[] liste_e){
        for (Graphic_Element e:
             liste_e) {
                addElementToPanel(e);
        }
    }


    //si aucun panel_state est associé, le panel est toujours visible
    public Groupe_Panel(Panel_State p_state){
        this.state = p_state;
        element_panel = new ArrayList<>();
    }

    public Groupe_Panel( Panel_State p_state, int panel_id){
        this.panel_id=panel_id;
        this.state = p_state;
        element_panel = new ArrayList<>();

    }


    public String toString(){
        String t = "";
        for (Graphic_Element g :
                element_panel) {
            t+= g.toString()+"; ";
        }
        return t;
    }

    public void setVisible() {
        setVisible(true);
    }

    public void setVisible(boolean visible){
        //parcourt les elements pour les rendre visible ou non
    }
}
