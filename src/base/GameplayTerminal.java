package base;
import graphics.Interface;
import java.util.Scanner;
import ia.IA;
import java.util.Timer;
import java.util.TimerTask;

public class GameplayTerminal {
    int etat = 0; //représente la phase de jeu en cours. 0 phase de défense, 1 phase d'attaque
    public int degats(Player attaquant, Player defenseur) {
        int old_pv = defenseur.getPv();
        int degat_max = 200;
        int new_pv = (int) Math.max(0, defenseur.getPv() - (degat_max*Math.exp(-Math.abs(attaquant.getAttack_value()-defenseur.getDefence_value()))) );
        defenseur.setPv(new_pv);
        return (old_pv-new_pv);
    }



    public int jeu_terminal_de_commande() throws Exception {
        Scanner sc = new Scanner(System.in); //Pour pouvoir lire les commandes du joueur
        System.out.print("\n\n***Début du jeu***\n\n");
        Player j1 = new Player();
        System.out.print("***Souhaitez-vous jouez contre l'ordinateur ?***\n\n");
        boolean ordi;
        String reponse = sc.nextLine();
        while (!(reponse.equals("oui") || reponse.equals("non"))){
            System.out.print("***Réponses attendues : 'oui' ou 'non'. ***\n\n");
            reponse = sc.nextLine();
        }
        ordi = reponse.equals("oui");
        Player j2 = new Player();
        IA cpu = new IA();

        while ((j1.getPv()>0)&&(j2.getPv()>0)) {
            //Initialisation
            j1.init();
            j2.init();
            cpu.init();

            System.out.print("***Le j1 doit régler sa défense***\n\n");
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\ndefense: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j1.getDefence_value() == 0) {
                reponse = sc.nextLine();
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
                    System.out.print("Instruction invalide. \n");
                }
            }
            if (!ordi) {
                System.out.print("***Le j2 doit maintenant régler son attaque***\n");
                System.out.print("***Il doit atteindre la défense du j1: " + j1.getDefence_value() + " ***\n\n");
                System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\nattaque: prendre le dernier élément de la pile pour en faire sa défense");
                System.out.print("\n\n");

                while (j2.getAttack_value() == 0) {
                    reponse = sc.nextLine();
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
            } else {
                cpu.findAttack(j1.getDefence_value());
            }

            //Dégats j2 contre j1
            int degat;
            if (ordi) degat = degats(cpu,j1);
            else degat = degats(j2, j1);
            System.out.print("j2 inflige " + degat + " de dégâts à j1.\n\n");

            //Réinitialisation
            j1.init();
            j2.init();
            cpu.init();

            if (!ordi) {
                System.out.print("***Le j2 doit régler sa défense***\n\n");
                System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\ndefense: prendre le dernier élément de la pile pour en faire sa défense");
                System.out.print("\n\n");

                while (j2.getDefence_value() == 0) {
                    reponse = sc.nextLine();
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
                    } else if (reponse.equals("defense")) {
                        j2.setDefence();
                    } else {
                        System.out.print("Instruction invalide");
                    }
                }
            } else {
                cpu.findDefence();
            }


            System.out.print("***Le j1 doit maintenant régler son attaque***\n");
            if (!ordi){
                System.out.print("***Il doit atteindre la défense du j2: " + j2.getDefence_value() + " ***\n\n");
            } else {
                System.out.print("***Il doit atteindre la défense du j2: " + cpu.getDefence_value() + " ***\n\n");
            }
            System.out.print("Commandes:\n\nstop: arrêter le jeu\netat: afficher l'inventaire, la pile et les pv\najout_nb: ajoute un nombre de l'inventaire à la pile\najout_op: ajouter un opérateur à la pile\nenlever: enlever un élément de la pile\npile: utiliser les 3 premiers éléments de la piles pour faire un nouveau nombre\nseparer: sépare un élément de la pile pour retrouver les nombres qui ont permis de le créer\nattaque: prendre le dernier élément de la pile pour en faire sa défense");
            System.out.print("\n\n");

            while (j1.getAttack_value() == 0) {
                reponse = sc.nextLine();
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
            if (ordi) degat = degats(j1,cpu);
            else degat = degats(j1, j2);
            System.out.print("j1 inflige " + degat + " de dégâts à j2.\n\n");
        }
        if ((j1.getPv()==0)&&(j2.getPv()==0)) {
            if (ordi) System.out.print("Il y a égalité avec l'ordinateur. C'était une partie très serrée !\n\n");
            else System.out.print("Il y a égalité. Félicitation aux deux joueurs !\n\n");
        } else if (j1.getPv()==0) {
            if (ordi) System.out.print("L'ordinateur a gagné. Tu feras mieux la prochaine fois !\n\n");
            else System.out.print("Le j2 a gagné. Bravo j2 !\n\n");
        } else if (j2.getPv()==0) {
            System.out.print("Le j1 a gagné. Bravo j1 !\n\n");
        }
        return 0;
    }
}
