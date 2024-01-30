package graphics;

import java.util.List;

public class Panel {
    private List<Graphic_Element> element_panel;
    private int panel_id;

    public Panel(int panel_id){
        this.panel_id=panel_id;
    }

    public void setVisible() {
        setVisible(true);
    }

    public void setVisible(boolean visible){
        //parcourt les elements pour les rendre visible ou non
    }
}
