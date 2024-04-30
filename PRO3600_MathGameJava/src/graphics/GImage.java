package graphics;

public class GImage extends Graphic_Element{
    public GImage(double x, double y, Graphic_type type){
        super(x,y, type);
    }

    @Override
    public String toString() {
        return "Image:"+position.toString();
    }
}
