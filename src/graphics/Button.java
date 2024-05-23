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
     final int stepAnimation = 30;
     final int durationMillisAnimation = 300;

    private Boolean isMoving = false;
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
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setRolloverEnabled (false);


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
        setVisible(true);

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

        setBounds(x, y, getBounds().width, getBounds().height);

        System.out.println("new pos "+x+" "+y);
        getPanelParent().setComponentZOrder(this,0);
        Interface.actualizeFrame(panelParent);
    }

    public void slide(int x, int y){
        new Thread(){
            public void run(){
                if (isMoving){
                    return;
                }
                isMoving = true;

                slideRealSmooth(x,y);
                isMoving = false;

            }
        }.start();

    }

    public void slideRealSmooth(int x, int y)  {
        int decayx = -(int) Math.round(0.1 * Interface.getScreenSize().width);
        int decayy = -(int)Math.round(0.2 * Interface.getScreenSize().height);

        int destinationx = x;
        int destinationy = y;
        posx = getBounds().x;
        posy = getBounds().y;

        double dx = (destinationx - posx)/stepAnimation;
        double dy = (destinationy - posy)/stepAnimation;
        System.out.println("Button doit aller de "+ posx +" "+posy+ " à "+ destinationx+" "+destinationy+" avec un pas de "+dx+" "+dy);
        for (int i = 0; i < stepAnimation+1; i++) {
            setBounds(Math.toIntExact(Math.round(posx + dx * (i + 1))) , Math.toIntExact(Math.round(posy + dy * (i + 1))) , getBounds().width, getBounds().height);
            try{

                Thread.sleep(durationMillisAnimation/stepAnimation);
            }catch (Exception e){

            }
        }
        setBounds(x, y, getBounds().width, getBounds().height);

        System.out.println("new pos "+x+" "+y);
       // getPanelParent().setComponentZOrder(this,0);
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
