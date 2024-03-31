package graphics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Groupe_Panel {
    private List<JComponent> element_panel;
    private Panel_State state;
    private int panel_id;

    public JPanel linked_panel;

    public Panel_State getState(){
        return state;
    }

    public void addElementToPanel(JComponent e){
        element_panel.add(e);
        linked_panel.add(e);
    }

    public void addElementToPanel(Graphic_Element[] liste_e){
        for (Graphic_Element e:
             liste_e) {
                addElementToPanel(e);
        }
    }


    //si aucun panel_state est associé, le panel est toujours visible
    public Groupe_Panel(Panel_State p_state){
        new Groupe_Panel(p_state, 0);
    }

    public Groupe_Panel( Panel_State p_state, int panel_id){
        this.panel_id=panel_id;
        this.state = p_state;
        element_panel = new ArrayList<>();
        linked_panel = new JPanel();
        Interface.fenetre.add(linked_panel);

    }


    public String toString(){
        String t = "";
        for (JComponent g :
                element_panel) {
            t+= g.toString()+"; ";
        }
        return t;
    }

    public void _setVisible() {
        _setVisible(true);
    }

    public void _setVisible(boolean visible){
        linked_panel.setVisible(visible);
    //parcourt les elements pour les rendre visible ou non
    }
}
