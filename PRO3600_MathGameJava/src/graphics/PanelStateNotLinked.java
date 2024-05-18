package graphics;

public class PanelStateNotLinked extends Exception{
    public PanelStateNotLinked(){
        super();
        System.out.println("Le panneau n'existe pas");
    }
}
