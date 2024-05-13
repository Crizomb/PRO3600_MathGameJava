package graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Button extends JButton {
    //JButton graph_button;
    String text;
    public Button(double x, double y, String text, Graphic_type type, int sizeModifier){
        super();
        //graph_button = new JButton();
        this.text=text;
        setText(text);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        //graph_button.setBounds((int)x,(int)y,type.getSize().width, type.getSize().height);
        setSize(type.getSize());
        setPreferredSize(type.getSize());
        setBackground(type.getFont_color());

        if(type.getURL() != ""){
            setImage(type.getImage_icon());
        }
        if(type.getFont() != null){
            Font f = type.getFont();

            setFont(new Font(f.getFontName(), f.getStyle(), Graphic_type.tresholdSize + sizeModifier));
            revalidate();
        }
          /*  System.out.println("test" + text.length());
            Font f = type.getFont();
            FontMetrics font_metrics = getFontMetrics(getFont());
            System.out.println(getMaximumSize().width+" a "+font_metrics.stringWidth(text));

            //setFont(f);
            //System.out.println(text + is_truncated_swing(text));
            setFont(new Font(f.getFontName(), f.getStyle(), (int)adjustSizeFont(text)));
             font_metrics = getFontMetrics(getFont());
            System.out.println(getMaximumSize().width+" b "+font_metrics.stringWidth(text));


            //setFont(new FontUIResource(type.getFont()));*             */





    }

    public void  setImage(ImageIcon imgic){
        setIcon(imgic);
        setOpaque(false);
        setLayout(new BorderLayout());
    }

    public static int getTextWidth(Font font, String text) {
        FontMetrics metrics = new FontMetrics(font) {
            private static final long serialVersionUID = 345265L;
        };
        Rectangle2D bounds = metrics.getStringBounds(text, null);
        return (int) bounds.getWidth();
    }

    private boolean is_truncated_swing(String text){
        FontMetrics font_metrics = getFontMetrics(getFont());
        System.out.println(getMaximumSize().width+" "+font_metrics.stringWidth(text));
        System.out.println(getMaximumSize().width - font_metrics.stringWidth(text));
            return this.getWidth() <= font_metrics.stringWidth(text);
    }
    private double adjustSizeFont(String text){
        System.out.println(Graphic_type.tresholdSize+" "+Math.pow(text.length(), 1.5));
        //return Graphic_type.tresholdSize - Math.pow(text.length(), 1.27);
        return Graphic_type.tresholdSize/(Math.max(8, 0.5*text.length()) - 7);

    }

    public void changeAction(ActionListener action){
        for (ActionListener al :
                getActionListeners()) {
            removeActionListener(al);
        }
        addActionListener(action);
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


    /*
        finally {
            this.size = _size;
        }

                    //Image img = Image.read(getClass().getResource("resources/water.bmp"));
*/
}
