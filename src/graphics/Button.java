package graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Button extends JButton  {
    //JButton graph_button;
    String text;
    int width, heigth;
    int posx,  posy;
    Graphic_type type;
    private Frame_Panel panelParent;
    public Button(String text, Graphic_type type, int sizeModifier, Frame_Panel panelParent){
        super();
        width=getBounds().width;
        heigth=getBounds().height;
        this.panelParent = panelParent;
        this.type = type;
        //System.out.println(width * heigth);
        //graph_button = new JButton();
        this.text=text;
        setText(text);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        //graph_button.setBounds((int)x,(int)y,type.getSize().width, type.getSize().height);
        //setSize(type.getSize());
      //  setPreferredSize(type.getSize());
        setBackground(type.getFont_color());


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



        Interface.actualizeFrame(panelParent);
        panelParent.addElementToPanel(this);
        Interface.fenetre.revalidate();

    }

    public void refreshImage(){
        if(type.getURL() != ""){
            setImageAndRescale(type.getImage_icon());
        }
    }

    public void  setImage(ImageIcon imgic){
        setIcon(imgic);
        setOpaque(false);
        setLayout(null);
        //setBounds();
    }

    public void setImageAndRescale(ImageIcon imgic){

        Image image = imgic.getImage(); // transform it
        float ratioImage = image.getWidth(null) / image.getHeight(null);

        Image newimg = image.getScaledInstance(getBounds().width,  Math.round(getBounds().width*ratioImage),  Image.SCALE_REPLICATE); // scale it the smooth way
        setBounds(getBounds().x, getBounds().y, getBounds().width,  Math.round(getBounds().width*ratioImage));
        imgic = new ImageIcon(newimg);  // transform it back
        setImage(imgic);

    }

    public void setPos(int x, int y){

        posx = x;
        posy = y;
        setBounds(x, y, getBounds().width, getBounds().height);
        System.out.println("new pos "+x+" "+y);
        Interface.actualizeFrame(panelParent);

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

            return this.getWidth() <= font_metrics.stringWidth(text);
    }
    private double adjustSizeFont(String text){

        return Graphic_type.tresholdSize/(Math.max(8, 0.5*text.length()) - 7);

    }

    public void changeAction(ActionListener action){
        for (ActionListener al :
                getActionListeners()) {
            removeActionListener(al);
        }
        addActionListener(action);
    }

    public JButton getGraph_button(){
        return null;
    }

    public Frame_Panel getPanelParent() {
        return panelParent;
    }


    /*
        finally {
            this.size = _size;
        }

                    //Image img = Image.read(getClass().getResource("resources/water.bmp"));
*/
}
