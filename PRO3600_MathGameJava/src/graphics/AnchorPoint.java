package graphics;

import javax.swing.*;
import java.awt.*;

public class AnchorPoint {
    Dimension pos;
    private Bullet ElementContained = null;
    public String purpose;
    public AnchorManager anchorManager;
    int posx, posy;


    public AnchorPoint(float ratioPosX, float ratioPosY, String purpose, AnchorManager anchorManager){
        this(Interface.ratiow(ratioPosX),Interface.ratioh(ratioPosY), purpose, anchorManager);
    }

    public AnchorPoint(int posx, int posy, String purpose, AnchorManager anchorManager){
        this.posx =posx;
        this.posy =posy;
        this.anchorManager = anchorManager;
        System.out.println("AnchorPoint set in x "+ posx+" y :" + posy);
        System.out.println(anchorManager.allAnchorPoint);
        System.out.println(this);

        anchorManager.allAnchorPoint.add(this);
    }

    public Bullet getElementContained() {
        return ElementContained;
    }

    public void setElementContained(Bullet elementContained) {
        ElementContained = elementContained;
    }

    public boolean HasElement(){
        return !(ElementContained == null);
    }
}
