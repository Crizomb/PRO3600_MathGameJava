import base.GameEvents;
import base.GameplayVisual;
import graphics.Interface;

import java.awt.*;

public class MainWithGraphic {
    private static GameEvents gameEvents;
    private static GameplayVisual game_visual;
    private static Interface interf;
    public static void main(String[] args) throws AWTException {
        interf = new Interface(2000 , 1000);
        game_visual = new GameplayVisual();
        gameEvents = new GameEvents(interf, game_visual);

    }
}
