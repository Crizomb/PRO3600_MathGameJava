package graphics;

public class Image extends Graphic_Element{

    public Image(double x, double y, Graphic_type type){
        super(x,y, type);
    }

    @Override
    public String toString() {
        return "Image:"+position.toString();
    }
}
