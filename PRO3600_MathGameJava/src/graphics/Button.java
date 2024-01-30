package graphics;
import javax.swing.*;

public class Button extends Image {

*
    public Button(double x, double y){
        super(x,y);

    }

    @Override
    public String toString() {

        return "Bouton " + position.toString();
    }
}
