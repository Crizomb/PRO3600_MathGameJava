package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Bullet extends Button {
    public int value;
    public boolean isSelected = false;
    private Thread moveButtonAction;
    private final int DELAY_MOVE_BUTTON=50;
    public AnchorManager anchorManager;
    public AnchorPoint anchoredPoint;

    public Bullet(double x, double y, int value, Frame_Panel fpanel, AnchorManager anchorManager){
        super(String.valueOf(value), Graphic_type.Ball_Number, 0, fpanel);
        this.value = value;
        this.anchorManager = anchorManager;
      refreshThread();

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                int modifiers = mouseEvent.getModifiersEx();
                if ((modifiers & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
                    // Mask may not be set properly prior to Java 2
                    // See SwingUtilities.isLeftMouseButton() for workaround
                    System.out.println("Left button pressed.");
                     isSelected = true;
                     if (anchoredPoint != null){
                         anchoredPoint.setElementContained(null);
                     }
                     refreshThread();

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
                    System.out.println("Left button released.");
                    isSelected = false;
                    relocateToNearestAnchorPoint();

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
    }

    public void relocateToNearestAnchorPoint(){
        anchoredPoint = anchorManager.getNearestFreeAnchorPoint(this);
        if (anchoredPoint == null) {
            return;
        }
        anchoredPoint.setElementContained(this);
        setPos(anchoredPoint.posx, anchoredPoint.posy);
    }

    public void refreshThread(){
        moveButtonAction = new Thread(){
            public void run(){
                while(isSelected){
                    Dimension d = AnchorManager.getMousePosition();
                    setPos(d.width, d.height);
                    try {
                        Thread.sleep(DELAY_MOVE_BUTTON);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }

}
