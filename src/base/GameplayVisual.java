package base;
import graphics.Interface;

import java.util.Scanner;
import java.util.Stack;

public class GameplayVisual {
    int etat = 0; //représente la phase de jeu en cours. 0 phase de défense, 1 phase d'attaque
   // public Interface interf;
    Player j1,j2;
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
        int degat_max = 200;
        int new_pv = (int) Math.max(0, defenseur.getPv() - (degat_max*Math.exp(-Math.abs(attaquant.getAttack_value()-defenseur.getDefence_value()))) );
        defenseur.setPv(new_pv);
        return (old_pv-new_pv);
    }

    public int commencer_jeu()  {
        j1 = new Player(1);
        j2 = new Player(2);
        lstNumberRollback = new Stack<>();
        Scanner sc = new Scanner(System.in); //Pour pouvoir lire les commandes du joueur
        listJoueur[0]=j1;
        listJoueur[1]=j2;
        j1.init();
        j2.init();

        tourJoueur(joueurEnCours);
        /*
        System.out.print("\n\n***Début du jeu***\n\n");
        while ((j1.getPv()>0)&&(j2.getPv()>0)) {
            //Initialisation
            j1.init();
            j2.init();

            System.out.print("***Le j1 doit régler sa défense***\n\n");
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\ndefense: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j1.getDefence_value() == 0) {
                String reponse = sc.nextLine();
                if (reponse.equals("stop")) {
                    return 0;
                } else if (reponse.equals("etat")) {
                    System.out.print(j1.toString() + "\n");
                } else if (reponse.equals("ajout_nb")) {
                    System.out.print("Quel nombre ?\nVoici votre inventaire: " + j1.inventory.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInInventory(Integer.valueOf(reponse))) {
                        j1.pushNumberInStack(j1.numberInInventory(Integer.valueOf(reponse)));
                    } else {
                        System.out.print("le nombre n'est pas dans l'inventaire\n");
                    }
                } else if (reponse.equals("ajout_op")) {
                    System.out.print("Quel opérateur ?\nVous avez les choix entre +, - et *\n");
                    reponse = sc.nextLine();
                    if (reponse.equals("+")) {
                        j1.pushOperatorInStack(new Items(Operator.ADD));
                    } else if (reponse.equals("-")) {
                        j1.pushOperatorInStack(new Items(Operator.SUB));
                    } else if (reponse.equals("*")) {
                        j1.pushOperatorInStack(new Items(Operator.MUL));
                    } else {
                        System.out.print("Opérateur invalide.\n");
                    }
                } else if (reponse.equals("enlever")) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j1.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInStack(reponse)) {
                        j1.putObjectOutOfStack(j1.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("pile")) {
                    j1.createNewNumberStack();
                } else if (reponse.equals(("separer"))) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j1.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInStack(reponse)) {
                        j1.separateNumbers(j1.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("defense")) {
                    j1.setDefence();
                } else {
                    System.out.print("Instruction invalide");
                }
            }

            System.out.print("***Le j2 doit maintenant régler son attaque***\n");
            System.out.print("***Il doit atteindre la défense du j1: "+ j1.getDefence_value() + " ***\n\n");
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\nattaque: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j2.getAttack_value()==0) {
                String reponse = sc.nextLine();
                if (reponse.equals("stop")) {
                    return 0;
                } else if (reponse.equals("etat")) {
                    System.out.print(j2.toString() + "\n");
                } else if (reponse.equals("ajout_nb")) {
                    System.out.print("Quel nombre ?\nVoici votre inventaire: " + j2.inventory.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInInventory(Integer.valueOf(reponse))) {
                        j2.pushNumberInStack(j2.numberInInventory(Integer.valueOf(reponse)));
                    } else {
                        System.out.print("le nombre n'est pas dans l'inventaire\n");
                    }
                } else if (reponse.equals("ajout_op")) {
                    System.out.print("Quel opérateur ?\nVous avez les choix entre +, - et *\n");
                    reponse = sc.nextLine();
                    if (reponse.equals("+")) {
                        j2.pushOperatorInStack(new Items(Operator.ADD));
                    } else if (reponse.equals("-")) {
                        j2.pushOperatorInStack(new Items(Operator.SUB));
                    } else if (reponse.equals("*")) {
                        j2.pushOperatorInStack(new Items(Operator.MUL));
                    } else {
                        System.out.print("Opérateur invalide.\n");
                    }
                } else if (reponse.equals("enlever")) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j2.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInStack(reponse)) {
                        j2.putObjectOutOfStack(j2.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("pile")) {
                    j2.createNewNumberStack();
                } else if (reponse.equals(("separer"))) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j2.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInStack(reponse)) {
                        j2.separateNumbers(j2.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("attaque")) {
                    j2.setAttack();
                } else {
                    System.out.print("Instruction invalide");
                }
            }

            //Dégats j2 contre j1
            int degat = degats(j2, j1);
            System.out.print("j2 inflige " + degat + " de dégâts à j1.\n\n");

            //Réinitialisation
            j1.init();
            j2.init();

            System.out.print("***Le j2 doit régler sa défense***\n\n");
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\ndefense: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j2.getDefence_value()==0) {
                String reponse = sc.nextLine();
                if (reponse.equals("stop")) {
                    return 0;
                } else if (reponse.equals("etat")) {
                    System.out.print(j2.toString() + "\n");
                } else if (reponse.equals("ajout_nb")) {
                    System.out.print("Quel nombre ?\nVoici votre inventaire: " + j2.inventory.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInInventory(Integer.valueOf(reponse))) {
                        j2.pushNumberInStack(j2.numberInInventory(Integer.valueOf(reponse)));
                    } else {
                        System.out.print("le nombre n'est pas dans l'inventaire\n");
                    }
                } else if (reponse.equals("ajout_op")) {
                    System.out.print("Quel opérateur ?\nVous avez les choix entre +, - et *\n");
                    reponse = sc.nextLine();
                    if (reponse.equals("+")) {
                        j2.pushOperatorInStack(new Items(Operator.ADD));
                    } else if (reponse.equals("-")) {
                        j2.pushOperatorInStack(new Items(Operator.SUB));
                    } else if (reponse.equals("*")) {
                        j2.pushOperatorInStack(new Items(Operator.MUL));
                    } else {
                        System.out.print("Opérateur invalide.\n");createNewNumberStack
                    }
                } else if (reponse.equals("enlever")) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j2.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInStack(reponse)) {
                        j2.putObjectOutOfStack(j2.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("pile")) {
                    j2.createNewNumberStack();
                } else if (reponse.equals(("separer"))) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j2.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j2.isInStack(reponse)) {
                        j2.separateNumbers(j2.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("defense")) {
                    j2.setDefence();
                } else {
                    System.out.print("Instruction invalide");
                }
            }

            System.out.print("***Le j1 doit maintenant régler son attaque***\n");
            System.out.print("***Il doit atteindre la défense du j2: "+ j2.getDefence_value() + " ***\n\n");
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\nattaque: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j1.getAttack_value() == 0) {
                String reponse = sc.nextLine();
                if (reponse.equals("stop")) {
                    return 0;
                } else if (reponse.equals("etat")) {
                    System.out.print(j1.toString() + "\n");
                } else if (reponse.equals("ajout_nb")) {
                    System.out.print("Quel nombre ?\nVoici votre inventaire: " + j1.inventory.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInInventory(Integer.valueOf(reponse))) {
                        j1.pushNumberInStack(j1.numberInInventory(Integer.valueOf(reponse)));
                    } else {
                        System.out.print("le nombre n'est pas dans l'inventaire\n");
                    }
                } else if (reponse.equals("ajout_op")) {
                    System.out.print("Quel opérateur ?\nVous avez les choix entre +, - et *\n");
                    reponse = sc.nextLine();
                    if (reponse.equals("+")) {
                        j1.pushOperatorInStack(new Items(Operator.ADD));
                    } else if (reponse.equals("-")) {
                        j1.pushOperatorInStack(new Items(Operator.SUB));
                    } else if (reponse.equals("*")) {
                        j1.pushOperatorInStack(new Items(Operator.MUL));
                    } else {
                        System.out.print("Opérateur invalide.\n");
                    }
                } else if (reponse.equals("enlever")) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j1.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInStack(reponse)) {
                        j1.putObjectOutOfStack(j1.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("pile")) {
                    j1.createNewNumberStack();
                } else if (reponse.equals(("separer"))) {
                    System.out.print("Quel élément ?\nVoici votre pile: " + j1.stack.toString() + "\n");
                    reponse = sc.nextLine();
                    if (j1.isInStack(reponse)) {
                        j1.separateNumbers(j1.itemInStack(reponse));
                    } else {
                        System.out.print("l'élément' n'est pas dans la pile\n");
                    }
                } else if (reponse.equals("attaque")) {
                    j1.setAttack();
                } else {
                    System.out.print("Instruction invalide");
                }
            }

            //Dégats j1 contre j2
            degat = degats(j2, j1);
            System.out.print("j1 inflige " + degat + " de dégâts à j2.\n\n");
        }
        if ((j1.getPv()==0)&&(j2.getPv()==0)) {
            System.out.print("Il y égalité. Félicitation aux deux joueurs !\n\n");
        } else if (j1.getPv()==0) {
            System.out.print("Le j1 a gagné. Bravo j1 !\n\n");
        } else if (j2.getPv()==0) {
            System.out.print("Le j2 a gagné. Bravo j2 !\n\n");
        }*/
        return 0;
    }

    public void tourJoueur(Player p){
       /* if (nJoueur>1){
            System.out.println("error call numero joueur");
            return ;
        }*/
        setJoueurEnCours(j1);
        setPhase("Defense");

        //setJoueurEnCours(j1);   normalement on est censé mettre ca a la place de la ligne d'en dessous
        gameEvents.playerInventoryUpdated(joueurEnCours.getValuesItemPlayer());

    }
    private void putAllObjectsOutOfStack(){
        ItemsStack copyL = new ItemsStack();
        for (Items i:
                joueurEnCours.stack) {
            copyL.add(i);
        }
        for (Items i:
             copyL) {
            joueurEnCours.putObjectOutOfStack(i);
        }
    }

    public void getStackFromString(String T){
        putAllObjectsOutOfStack();
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
                joueurEnCours.pushNumberInStack(joueurEnCours.numberInInventory(Integer.valueOf(c)));

            }
        }

       /* System.out.println(joueurEnCours.toString());
        joueurEnCours.pushNumberInStack(joueurEnCours.numberInInventory(Integer.valueOf(chars[0])));
        joueurEnCours.pushNumberInStack(joueurEnCours.numberInInventory(Integer.valueOf(chars[1])));
        try{
            joueurEnCours.pushOperatorInStack(new Items(Operator.getOperator(chars[2])));

        } catch (Exception e){
            System.out.println("il y a une erreur de lecture de caractère");
        }*/

    }
    public void sendNewOperation(String T){
        getStackFromString(T);
        try{
            j1.createNewNumberStack();
        }catch (Exception e){
            System.out.println("Opération invalide");
            return;
        }


        System.out.println(j1.stack.get(0).getValue().getIntValue());


        System.out.println(joueurEnCours.stack.get(0).getValue().getIntValue());
        gameEvents.newValueInStack(joueurEnCours.stack.get(0).getValue().getIntValue());


                /*joueurEnCours.putObjectOutOfStack(joueurEnCours.stack.get(0));
        lstNumberRollback.push(joueurEnCours.stack.get(0));*/
        lstNumberRollback.push(joueurEnCours.stack.get(0));
        joueurEnCours.putObjectOutOfStack(joueurEnCours.stack.get(0));

    }
    public void etapeSuivante(){
        if(phase == "Attack" ){
            setPhase("Defense");
        }else{
            setPhase("Attack");
        }

        System.out.println("------------------------------------------------------------------        changement detape "+joueurEnCours.getId());

        if (joueurEnCours == j1){
            setJoueurEnCours(j2);
        }else if (joueurEnCours == j2) {
            setJoueurEnCours(j1);
        }

    }
    public void setJoueurEnCours(Player p){
        //pas besoin de mettre d'Update ici, car automatiquement on appelera setPhase
        joueurEnCours = p;
        System.out.println(" ----------------------------------------------------------------  la on est au joueur "+p.getId());
        gameEvents.playerInventoryUpdated(joueurEnCours.getValuesItemPlayer());

    }
    public void setPhase(String p){
        assert p == "Attack" || p == "Defense";
        phase=p;
        gameEvents.UpdateStepGame(p, joueurEnCours);
    }
    public void defense(String T) throws IllegalStateException{
        getStackFromString(T);
        joueurEnCours.setDefence();
        /*for(int i=0; i<2; i+=1) {
            if (!(listJoueur[i].equals(joueurEnCours))) {
                joueurEnCours = listJoueur[i];
                break;
            }
        }*/
        etapeSuivante();
    }

    public void attack(String T) throws IllegalStateException{
        getStackFromString(T);
        joueurEnCours.setAttack();
     /*   for(int i=0; i<2; i+=1) {
            if (!(listJoueur[i].equals(joueurEnCours))) {
                joueurEnCours = listJoueur[i];
                break;
            }
        }*/
        etapeSuivante();
    }

    public void rollback() throws NoSuchFieldException {
        if (!(lstNumberRollback.isEmpty())) {
            Items elem = lstNumberRollback.pop();
            joueurEnCours.separateNumbers(elem);
        }
    }


}
