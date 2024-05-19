package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.Instant;

public class Bullet extends Button {
    public String value;
    public boolean isOperator;

    public boolean isSelected = false;
    private Thread moveButtonAction;
    private final int DELAY_MOVE_BUTTON=50;
    private final int DELAY_SHORT_CLICK = 50;

    public final float RATIO_SIZE_BULLET = 0.05f;

    public AnchorManager anchorManager;
    public AnchorPoint anchoredPoint;

    private Instant catchtime;


    public static final Dimension MOUSE_OFFSET = new  Dimension(50,100);
    public Bullet(double x, double y, String value, Frame_Panel fpanel, AnchorManager anchorManager, Boolean isOperator){
        this(x,y,value,fpanel, anchorManager);
        this.isOperator = isOperator;
    }
    public Bullet(double x, double y, String value, Frame_Panel fpanel, AnchorManager anchorManager){
        super((value), Graphic_type.Ball_Number, 0, fpanel);
        isOperator = false;
        this.value = value;
        this.anchorManager = anchorManager;
      refreshThread();

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                int modifiers = mouseEvent.getModifiersEx();
                if ((modifiers & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
                    // Mask may not be set properly prior to Java 2
                    // See SwingUtilities.isLeftMouseButton() for workaround
                  //  System.out.println("Left button pressed.");
                    if (!isSelected){
                        catchtime = Instant.now();
                    }
                     isSelected = true;

                     refreshThread();
                    if (isOperator){
                        // createNewOperator();
                        // anchorManager.spawnNewOperator();
                    }
                    moveButtonAction.start();

                }
                if ((modifiers & InputEvent.BUTTON2_DOWN_MASK) == InputEvent.BUTTON2_DOWN_MASK) {
                    System.out.println("Middle button pressed.");
                }
                if ((modifiers & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK) {
                    System.out.println("Right button pressed.");
                }
            }

            public void mouseReleased(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    isSelected = false;
                    if( (int) Duration.between(catchtime, Instant.now()).getSeconds()<DELAY_SHORT_CLICK){
                        relocateToChangePile();
                    }else{
                        relocateToNearestAnchorPoint();
                    }
                    anchorManager.relocateAllBulletsInPile();


                }
                if (SwingUtilities.isMiddleMouseButton(mouseEvent)) {
                    System.out.println("Middle button released.");
                }
                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    System.out.println("Right button released.");
                }
                System.out.println();
            }
        };
        this.addMouseListener(mouseListener);
       // relocateToNextAnchorPoint(AnchorPurpose.Number_Reserve);
        anchorManager.allBullets.add(this);


    }

    public void relocateToNearestAnchorPoint(){
        breakLinkWithAnchor();
        anchoredPoint = anchorManager.getNearestFreeAnchorPoint(this);
        if (anchoredPoint == null) {
            return;
        }
        anchoredPoint.setElementContained(this);
        setPos(anchoredPoint.posx, anchoredPoint.posy);
        anchorManager.relocateAllBulletsInPile();
    }

    public void createNewOperator(){
        anchorManager.spawnNewOperator(this);
    }

    public void relocateToChangePile(){
        if(isOperator && anchoredPoint !=null){
            if (anchoredPoint.purpose == AnchorPurpose.Operator_jar){
                anchorManager.removeOperator();
            }
        }
        if(anchoredPoint != null){

            relocateToNextAnchorPoint(anchoredPoint.purpose.mirror());
        }
    }

    public void relocateToNextAnchorPoint(AnchorPurpose purpose){
        breakLinkWithAnchor();
        anchoredPoint = anchorManager.getFirstAnchorPointWithPurpose(this, purpose);
        if (anchoredPoint == null) {
            System.out.println("echec trouvé anchor");
            return;
        }
        getAnchoredToNewAnchor(anchoredPoint);
    }

    public void getAnchoredToNewAnchor(AnchorPoint a){
        breakLinkWithAnchor();
        a.setElementContained(this);
        setPos(a.posx, a.posy);
       // if(anchoredPoint.purpose == AnchorPurpose.Number_Reserve || anchoredPoint.purpose == AnchorPurpose.Number_jar){
        if(isOperator){

           // anchorManager.requestTestFormulaInPile();
        }
    }

    public void breakLinkWithAnchor(){
        if (anchoredPoint != null){
            anchoredPoint.breakLinkWithElemeent();
        }
    }

    public void refreshThread(){
        moveButtonAction = new Thread(){
            public void run(){
                while(isSelected){
                    Dimension d = substract(AnchorManager.getMousePosition(),MOUSE_OFFSET) ;
                    setPos(d.width, d.height);
                    Dimension screen = Interface.getScreenSize();
                    System.out.println(d+" "+ screen+ " en ratio " + (float)d.width/screen.width  + " ; "+ (float)d.height/screen.height);

                    try {
                        Thread.sleep(DELAY_MOVE_BUTTON);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }

    public Dimension substract(Dimension d1, Dimension d2){
        return new Dimension(d1.width-d2.width, d1.height-d2.height);
    }

}
