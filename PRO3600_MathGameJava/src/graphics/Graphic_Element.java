package graphics;

import javax.swing.*;
import java.awt.*;

public class Graphic_Element extends JComponent {
    protected final Graphic_type type;
    protected Dimension size;

    protected Color font_color;
    protected Vector position;
    public Dimension DEFAULT_SIZE = new Dimension(50,50);

    public Graphic_Element(double x, double y, Graphic_type type){
        this.type=type;
        getData();
        position = new Vector(x,y);
        setPosition(position);


    }

    public void getData(){
        font_color = type.getFont_color();
        size = type.getSize();
    }

    public Vector getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "graph_element:"+position.toString();
    }

    public void setPosition(Vector position){
return;
    }
}
