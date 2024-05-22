package base;

import graphics.Interface;

public class GameEvents {

    private Interface interface_joueur;
    private GameplayVisual gameplayVisual;



    public GameEvents(Interface interface_joueur, GameplayVisual gameplayVisual){
        this.interface_joueur = interface_joueur;
        this.gameplayVisual = gameplayVisual;
        System.out.println("--------------------------------------set-------------------------------------- gameEvent1");

        interface_joueur.setGameEvents(this);
        gameplayVisual.setGameEvents(this);
        System.out.println("--------------------------------------set-------------------------------------- gameEvent2");

    }

    public void ButtonLaunchGamePressed(){
        gameplayVisual.commencer_jeu();
    }

    public void ButtonEqualOperationPressed(String formulaInStack){
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


}