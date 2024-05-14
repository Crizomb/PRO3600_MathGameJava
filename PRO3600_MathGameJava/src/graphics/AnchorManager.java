package graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnchorManager {
    public List<AnchorPoint> allAnchorPoint;

    public AnchorManager(){
        allAnchorPoint = new ArrayList<>();
    }
    public static Dimension getMousePosition(){
        Point a = MouseInfo.getPointerInfo().getLocation();
        return new Dimension((int)a.getX(), (int)a.getY());
    }

    public String getFormulaPile(){
        String T = "";
        for (AnchorPoint a:
             allAnchorPoint) {
            if (a.purpose == "number"&& a.HasElement()){
                T+=a.getElementContained().value;
            }
        }
        return T;
    }

    public void toggleDebugBox(boolean b){
        for (AnchorPoint a:
             allAnchorPoint) {

        }
    }

    public AnchorPoint getNearestFreeAnchorPoint(Bullet target){
        AnchorPoint selectedAnchor = null;
        double distance = Double.POSITIVE_INFINITY, testdistance ;
        for (AnchorPoint a:
            allAnchorPoint ) {
            testdistance = distance(a.posx, a.posy, target.posx, target.posy);
            System.out.println("distance "+ distance+ "testdistance "+testdistance);
            if (testdistance <= distance && !(a.HasElement())){
                selectedAnchor = a;
                distance = testdistance;
            }
        }
        return selectedAnchor;
    }

    public double distance(int posX, int posY, int targetX, int targetY){
        System.out.println(posX+" posy : "+ posY+" targetX :"+targetX+" target y :"+targetY);
        return Math.sqrt(carré(posX-targetX) + carré(posY-targetY));
    }

    public int carré(int a){
        return a*a;
    }

}
