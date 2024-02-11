package graphics;
import javax.swing.*;
import java.awt.*;

public class Button extends Graphic_Element {
    JButton graph_button;
    String text;
    public Button(double x, double y, String text, Graphic_type type){
        super(x,y, type);
        graph_button = new JButton();
        this.text=text;
        graph_button.setText(text);
        graph_button.setSize(type.getSize());
        graph_button.setBackground(type.getFont_color());
        System.out.println(toString()+ "created");
    }

   /* public Button(double x, double y, String text){
        super(x,y);
         new Button(x,y,text, DEFAULT_SIZE);
    }*/
    public JButton getGraph_button(){
        return graph_button;
    }

    @Override
    public String toString() {
        return "Button "+text+" "+ position.toString();
    }
}
