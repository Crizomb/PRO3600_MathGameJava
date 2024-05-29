package base;
import graphics.Interface;

import java.util.Scanner;
import java.util.Stack;

public class GameplayVisual {
    int etat = 0; //représente la phase de jeu en cours. 0 phase de défense, 1 phase d'attaque
   // public Interface interf;
    Player j1,j2;
    final int degat_max = 200;

    public Player[] listJoueur= new Player[2];

    private Player joueurEnCours = j1;

    public Stack<Items> lstNumberRollback;
    public String phase = "Defense";
    private GameEvents gameEvents;

    public void setGameEvents(GameEvents gameEvents){
this.gameEvents = gameEvents;
        System.out.println("--------------------------------------set-------------------------------------- gameVisu");

    }

    public int degats(Player attaquant, Player defenseur) {
        int old_pv = defenseur.getPv();
        int new_pv = (int) Math.max(0, defenseur.getPv() - (degat_max*Math.exp(-Math.abs(attaquant.getAttack_value()-defenseur.getDefence_value()))) );
        // defenseur.setPv(new_pv);
        return (old_pv-new_pv);
    }

    public void initializePlayers(){
        j1.init();
        j2.init();
        gameEvents.statsPlayersUpdated();
        //setJoueurEnCours(j1);   normalement on est censé mettre ca a la place de la ligne d'en dessous
        gameEvents.playerInventoryUpdated(joueurEnCours.getValuesItemPlayer());
    }

    public int commencer_jeu()  {
        j1 = new Player(1);
        j2 = new Player(2);
        lstNumberRollback = new Stack<>();
        Scanner sc = new Scanner(System.in); //Pour pouvoir lire les commandes du joueur
        listJoueur[0]=j1;
        listJoueur[1]=j2;

       // tourJoueur(joueurEnCours);
        setJoueurEnCours(j1);
        setPhase("Defense");
        initializePlayers();


        return 0;
    }

    private void putAllObjectsOutOfStack(){
        ItemsStack copyL = new ItemsStack();
        for (Items i: joueurEnCours.stack) {
            copyL.add(i);
        }
        for (Items i: copyL) {
            joueurEnCours.putObjectOutOfStack(i);
        }
    }

    public void getStackFromString(String T){
        putAllObjectsOutOfStack();
        System.out.println(T+" fdqsfdqfdsqfdsqfdsqf" + joueurEnCours.stack);
        String chars[] = T.split(" ");
        for (String c:
             chars) {
            Operator op = Operator.getOperator(c);
            if(op != null){
                try {
                    joueurEnCours.pushOperatorInStack(new Items(Operator.getOperator(c)));
                }catch (Exception e){
                    System.out.println(e.toString() + " opération annulée");
                }
            }else{
                System.out.println(c+" :khlkgkjyfjhgjrhgjhhhd");
                joueurEnCours.pushNumberInStack(joueurEnCours.numberInInventory(Integer.valueOf(c)));

            }
        }

    }
    public void sendNewOperation(String T){
        System.out.println("staaaack vide ?"+joueurEnCours.stack.isEmpty());
        getStackFromString(T);
        try{
            joueurEnCours.createNewNumberStack();
        }catch (Exception e){
            System.out.println("Opération invalide");
            putAllObjectsOutOfStack();
            gameEvents.errorMustBeSentToPlayer(e.getMessage());
            e= null;

            return;
        }


        System.out.println(joueurEnCours.stack.get(0).getValue().getIntValue());


        System.out.println(joueurEnCours.stack.get(0).getValue().getIntValue());
        gameEvents.newValueInStack(joueurEnCours.stack.get(0).getValue().getIntValue());


                /*joueurEnCours.putObjectOutOfStack(joueurEnCours.stack.get(0));
        lstNumberRollback.push(joueurEnCours.stack.get(0));*/
        lstNumberRollback.push(joueurEnCours.stack.get(0));
        joueurEnCours.putObjectOutOfStack(joueurEnCours.stack.get(0));

    }

    public Player playerNotPlayingCurrently(){
        if (joueurEnCours == j1){
            return j2;
        }
        return j1;
    }
    public void etapeSuivante(){
        if(phase == "Attack" ){
            if(joueurEnCours == j1){
                initializePlayers();
            }
            setPhase("Defense");

        }else{
            setPhase("Attack");
           setJoueurEnCours(playerNotPlayingCurrently());
        }
        System.out.println("------------------------------------------------------------------        changement detape "+joueurEnCours.getId());



        gameEvents.statsPlayersUpdated();


    }
    public void setJoueurEnCours(Player p){
        //pas besoin de mettre d'Update ici, car automatiquement on appelera setPhase
        joueurEnCours = p;
        System.out.println(" ----------------------------------------------------------------  la on est au joueur "+p.getId());
        gameEvents.playerInventoryUpdated(joueurEnCours.getValuesItemPlayer());
        gameEvents.UpdateStepGame(phase, joueurEnCours);

    }
    public void setPhase(String p){
        assert p == "Attack" || p == "Defense";
        phase=p;
        gameEvents.UpdateStepGame(p, joueurEnCours);
    }
    public void defense(String T) throws IllegalStateException{
        getStackFromString(T);
        joueurEnCours.setDefence();
        etapeSuivante();
    }

    public void attack(String T) throws IllegalStateException{
        getStackFromString(T);
        joueurEnCours.setAttack();
        int degat_done = degats(joueurEnCours, playerNotPlayingCurrently());
        if (degat_done > joueurEnCours.getPv()){
            System.out.println("Un joueur a gagné");
            playerNotPlayingCurrently().setPv(0);
            etapeSuivante();
        }
        else{
            playerNotPlayingCurrently().setPv(playerNotPlayingCurrently().getPv() - degat_done);
            etapeSuivante();
        }
    }

    public void rollback() throws NoSuchFieldException {
        if (!(lstNumberRollback.isEmpty())) {
            Items elem = lstNumberRollback.pop();
            joueurEnCours.separateNumbers(elem);
        }
    }


}
