package graphics;

import java.awt.*;
import java.util.*;
import java.util.List;

public class AnchorManager {
    public List<AnchorPoint> allAnchorPoint;
    public List<Bullet> allBullets = new ArrayList<>();
    private Map<AnchorPurpose, AnchorPoint[]> sorted_anchorPoint;

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
            if (a.purpose == AnchorPurpose.Number_jar && !(a.HasNoElement())){
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

    public void relocateAllBulletsInNumberReserve(){
        for (Bullet b:
             allBullets) {
            b.relocateToNextAnchorPoint(AnchorPurpose.Number_Reserve);
        }
    }

    public void relocateAllBulletsInPile(){
        for(Bullet b :  allBullets){
            if (b.anchoredPoint != null){
                b.relocateToNextAnchorPoint(b.anchoredPoint.purpose);
            }else{
                b.relocateToNearestAnchorPoint();
            }
        }
    }

    public void removeOperator(){
        List<Bullet> L = new ArrayList<>();
        for (Bullet b:
             allBullets) {
            if(b.isOperator){
                b.getPanelParent().remove(b);
            }else{
                L.add(b);
            }
        }
        allBullets = L;
    }


    
    public void spawnNewOperator(Bullet b){
       //Bullet newB = new Bullet(0,0,0,b.getPanelParent(),this, true);
    }

    public AnchorPoint getNearestFreeAnchorPoint(Bullet target){
        AnchorPoint selectedAnchor = null;
        double distance = Double.POSITIVE_INFINITY, testdistance ;
        for (AnchorPoint a:
            allAnchorPoint ) {
            testdistance = distance(a.posx, a.posy, target.posx, target.posy);
          //  System.out.println("distance "+ distance+ "testdistance "+testdistance);
            if (testdistance <= distance && (a.HasNoElement())){
                if((target.isOperator && (a.purpose == AnchorPurpose.Operator_jar || a.purpose == AnchorPurpose.Operator_reserve)
                     || (!(target.isOperator) && (a.purpose == AnchorPurpose.Number_Reserve || a.purpose == AnchorPurpose.Number_jar)))){

                    selectedAnchor = a;
                    distance = testdistance;
                }
            }
        }
        return selectedAnchor;
    }

    public AnchorPoint getNextAnchorPointFromList(List<AnchorPoint> L){
        AnchorPoint selectedAnchor = L.get(0);
        for (AnchorPoint a:
                L ) {
            //  System.out.println("distance "+ distance+ "testdistance "+testdistance);
            if (a.posx <= selectedAnchor.posx){
                selectedAnchor = a;
            }
        }
        return selectedAnchor;
    }


    public AnchorPoint getFirstAnchorPointWithPurpose(Bullet target, AnchorPurpose purpose){
        AnchorPoint selected_anchors[] = sorted_anchorPoint.get(purpose);
        for (int i = 0; i < selected_anchors.length; i++) {
            if(selected_anchors[i].HasNoElement()){
                System.out.println("found an anchor for bullet "+target.value +" at "+ target.posx + " in "+selected_anchors[i].posx);
                return selected_anchors[i];
            }
        }
        System.out.println("NON");
        return null;
    }
    
    public void setSorted_anchorPoint(){
        sorted_anchorPoint = new HashMap<AnchorPurpose, AnchorPoint[]>();
        for (AnchorPurpose purpose :
                AnchorPurpose.values()) {
            List<AnchorPoint> L = getListAnchorsWithPurpose(purpose);
            AnchorPoint allAnchorsSelectedPurpose[] = new AnchorPoint[L.size()] ;
            for (int i = 0; i < allAnchorsSelectedPurpose.length; i++) {
                AnchorPoint a = getNextAnchorPointFromList(L);
                allAnchorsSelectedPurpose[i] =a ;
                L.remove(a);
            }

            sorted_anchorPoint.put(purpose,allAnchorsSelectedPurpose);

        }
    }



    public List<AnchorPoint> getListAnchorsWithPurpose(AnchorPurpose purpose) {
        List<AnchorPoint> L = new ArrayList<>();
        for (AnchorPoint a :
                allAnchorPoint) {
            if (a.purpose == purpose) {
                L.add(a);
            }
        }
        return L;

    }

        public double distance(int posX, int posY, int targetX, int targetY){
        return Math.sqrt(carré(posX-targetX) + carré(posY-targetY));
    }

    public int carré(int a){
        return a*a;
    }

}
