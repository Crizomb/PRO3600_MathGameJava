package graphics;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Button extends JButton {
    //JButton graph_button;
    String text;
    public Button(double x, double y, String text, Graphic_type type){
        super();
        //graph_button = new JButton();
        this.text=text;
        setText(text);
        //graph_button.setBounds((int)x,(int)y,type.getSize().width, type.getSize().height);
        setPreferredSize(type.getSize());
        setBackground(type.getFont_color());
        System.out.println(toString()+ "created");

        if(type.getURL() != ""){
            System.out.println(type.getURL());
            try {
                //Image img = Image.read(getClass().getResource("resources/water.bmp"));
                //TODO : resoudre le probleme d'acces pour les images situes dans le dossier texture, et y mettre les images
                setIcon(new ImageIcon(Button.class.getResource(type.getURL())));
                System.out.println("success");
                setOpaque(false);

            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("---------error----------");
            }
        }


    }

   /* public Button(double x, double y, String text){
        super(x,y);
         new Button(x,y,text, DEFAULT_SIZE);
    }*/
    public JButton getGraph_button(){
        return null;
    }

  /*  @Override
    public String toString() {
        return "Button "+text+" "+ position.toString();
    }*/
}
