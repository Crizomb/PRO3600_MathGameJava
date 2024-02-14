package graphics;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Button extends Graphic_Element {
    JButton graph_button;
    String text;
    public Button(double x, double y, String text, Graphic_type type){
        super(x,y, type);
        graph_button = new JButton();
        this.text=text;
        graph_button.setText(text);
        //graph_button.setBounds((int)x,(int)y,type.getSize().width, type.getSize().height);
        graph_button.setPreferredSize(type.getSize());
        graph_button.setBackground(type.getFont_color());
        System.out.println(toString()+ "created");

        if(type.getURL() != ""){
            try {
                //Image img = Image.read(getClass().getResource("resources/water.bmp"));
                graph_button.setIcon(new ImageIcon(Class.class.getResource("/texture/"+ type.getURL())));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }


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
