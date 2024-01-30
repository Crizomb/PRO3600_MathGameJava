package graphics;

public class Image extends Graphic_Element{

    public Image(double x, double y){
        super(x,y);
    }

    @Override
    public String toString() {
        return "Image:"+position.toString();
    }
}
