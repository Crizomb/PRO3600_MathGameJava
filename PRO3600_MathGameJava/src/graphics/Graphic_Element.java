package graphics;

public class Graphic_Element {
    public Vector position;

    public Graphic_Element(double x, double y){
        position = new Vector(x,y);
        setPosition(position);
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
