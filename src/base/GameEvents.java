package base;

import graphics.Interface;

import java.awt.*;
import java.time.Duration;

public class GameEvents {

    private Interface interface_joueur;
    private GameplayVisual gameplayVisual;



    public GameEvents(Interface interface_joueur, GameplayVisual gameplayVisual){
        this.interface_joueur = interface_joueur;
        this.gameplayVisual = gameplayVisual;
        interface_joueur.setGameEvents(this);
        gameplayVisual.setGameEvents(this);
    }

    public void ButtonLaunchGamePressed(){
        gameplayVisual.commencer_jeu();
    }

    public void ButtonEqualOperationPressed(String formulaInStack){
        System.out.println(formulaInStack);
        gameplayVisual.sendNewOperation(formulaInStack);
    }

    public void ButtonAttackPressed(String formulaInStack){
        gameplayVisual.attack(formulaInStack);
    }
    public void ButtonDefensePressed(String formulaInStack){
        gameplayVisual.defense(formulaInStack);
    }

    public void UpdateStepGame(String phase, Player current_player){
        String combined = phase+String.valueOf(current_player.getId());
        //TODO manque l'appel de gameVisual
        interface_joueur.UpdatePanelStepGame(combined);
    }

    public void playerInventoryUpdated(int[] player_bullets_values){
        interface_joueur.setPlayerInventoryPanel(player_bullets_values);
    }

    public void newValueInStack(int value){
        interface_joueur.UpdateStack(value);
    }

    public void statsPlayersUpdated(){
        interface_joueur.updatePlayerStats( gameplayVisual.j1.getPv(),gameplayVisual.j2.getPv(), gameplayVisual.j1.getDefence_value(), gameplayVisual.j2.getDefence_value());
    }

    public void errorMustBeSentToPlayer(String s){
        interface_joueur.send_error_message_temporary(s);
    }

}
