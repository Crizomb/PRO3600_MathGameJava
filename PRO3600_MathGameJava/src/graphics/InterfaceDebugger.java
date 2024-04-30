package graphics;

import java.awt.*;

public class InterfaceDebugger {
    public void getMousePos(int number_pos) throws AWTException {
        Robot r = new Robot();


        for (int i = 0; i < number_pos; i++) {
            try {
                Thread.sleep(5000);
                System.out.println("wait");
            } catch (InterruptedException e) {
                System.out.println("echec ---------------------");
                throw new RuntimeException(e);
            }
            Point a = MouseInfo.getPointerInfo().getLocation();
            int x =  (int) a.getX();
            int y = (int) a.getY();
            System.out.println("Xpos : "+ x + " ;YPos : "+ y +";");
           // try {
                r.mouseMove(0 , 0);
                r.mouseMove(4,500);

            /*}catch (Exception e){
                System.out.println("non robot");
            }*/
        }
    }


    public void getMousePos() throws AWTException{
        getMousePos(1);
    }

}
