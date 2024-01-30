package graphics;

public class Graphic_Element {
    Vector position = new Vector(0,0);

    public Graphic_Element(double x, double y){
        position.x=x;
        position.y=y;
        setPosition(position);
    }

    public Vector getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "graph_element";
    }

    void setPosition(Vector position){
        return;
    }
}
