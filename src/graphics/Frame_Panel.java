package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame_Panel extends JPanel{
    private List<JComponent> element_panel;
    private Panel_State state;
    private int panel_id;

    //public JPanel linked_panel;

    public Panel_State getState(){
        return state;
    }

    public void addElementToPanel(JComponent e){
        element_panel.add(e);
        add(e);
        this.add(e);

    }


    public void addElementToPanel(Graphic_Element[] liste_e){
        for (Graphic_Element e:
             liste_e) {
                addElementToPanel(e);
        }
    }


    //si aucun panel_state est associé, le panel est toujours visible
    public Frame_Panel(Panel_State p_state){
        new Frame_Panel(p_state, 0);
    }

    public Frame_Panel(Panel_State p_state, int panel_id){
        this.panel_id=panel_id;
        this.state = p_state;
        element_panel = new ArrayList<>();
        this.setLayout(null);
        Interface.fenetre.add(this);
        this.setOpaque(true);
        System.out.println(Interface.getScreenSize());
        this.setBounds(0,0,Interface.getScreenSize().width, Interface.getScreenSize().height);
        this.setBackground(Color.green);
    }

    public Frame_Panel(Frame_Panel panel_parent, int panel_id){
        this.panel_id=panel_id;
        panel_parent.addElementToPanel(this);
        Interface.fenetre.add(this);
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

        setVisible(visible);

        for (JComponent jc:
            element_panel ) {
            jc.setVisible(visible);
            jc.setEnabled(visible);
        }

        //parcourt les elements pour les rendre visible ou non
    }


}
