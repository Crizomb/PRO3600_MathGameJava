package graphics;

public class Bullet extends Button{
    public int value;
    public Bullet(double x, double y, int value){
        super(x,y, String.valueOf(value), Graphic_type.Ball_Number, 0);
        this.value = value;
    }
}
