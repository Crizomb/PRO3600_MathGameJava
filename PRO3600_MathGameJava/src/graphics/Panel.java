package graphics;

import java.util.List;

public class Panel {
    private List<Graphic_Element> element_panel;
    private Panel_State state;
    private int panel_id;

    public Panel_State getState(){
        return state;
    }


    //si aucun panel_state est associé, le panel est toujours visible
    public Panel(int panel_id){
        this.panel_id=panel_id;
    }

    public Panel(int panel_id, Panel_State p_state){
        this.panel_id=panel_id;
        this.state = p_state;
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
