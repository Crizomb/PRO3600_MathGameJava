package graphics;

public class Bullet extends Button{
    public int value;
    public Bullet(double x, double y, int value, Frame_Panel fpanel){
        super(String.valueOf(value), Graphic_type.Ball_Number, 0, fpanel);
        this.value = value;
    }
}
