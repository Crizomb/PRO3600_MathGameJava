package graphics;

import javax.swing.*;
import java.awt.*;

public class AnchorPoint {
    Dimension pos;
    private Bullet ElementContained = null;
    public AnchorPurpose purpose;
    public AnchorManager anchorManager;
    int posx, posy;


    public AnchorPoint(float ratioPosX , float ratioPosY , AnchorPurpose purpose , AnchorManager anchorManager) {
        this(Interface.ratiow(ratioPosX) , Interface.ratioh(ratioPosY) , purpose , anchorManager);
    }

    public AnchorPoint(int posx , int posy , AnchorPurpose purpose , AnchorManager anchorManager) {
        this.posx = posx;
        this.posy = posy;
        this.anchorManager = anchorManager;
        this.purpose = purpose;
        //System.out.println("AnchorPoint set in x "+ posx+" y :" + posy);
        //System.out.println(anchorManager.allAnchorPoint);
       // System.out.println(this);

        anchorManager.allAnchorPoint.add(this);
    }

    public Bullet getElementContained() {
        return ElementContained;
    }

    public void setElementContained(Bullet a){
        ElementContained = a;
    }

    public void breakLinkWithElemeent(){
        if(HasNoElement()){
            return;
        }
        ElementContained.anchoredPoint = null;
        setElementContained(null);
    }

    public boolean HasNoElement(){
        return ElementContained == null;
    }


}

