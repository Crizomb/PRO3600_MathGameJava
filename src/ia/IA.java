package ia;
import java.util.*;
import base.*;

// Sense : Regarder la liste de nombres à disposition de l'IA.
// Think : Produire un nombre difficilement atteignable selon une heuristique à choisir.
// Act : Appeler UpdateDefense ou UpdateAttack pour mettre à jour la défense ou l'attaque.

/**
 * @author Youssef
 * @version 1 Implémentation de l'I.A. pour le jeu. <br>
 */

public class IA extends Player {


    public IA(){ super(); }

    public static double relativeDistance(int target, int x){
        // Renvoie la distance entre x et la cible, relativement à la cible.
        return (double) Math.abs(x - target) / target;
    }

    public int findClosestZeroOp(int target){
        // Renvoie le nombre de l'inventaire le plus proche de la cible.
    	double proximity1 = target / 20.0;
    	ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int best = Integer.MAX_VALUE;
        int pos_best = -1;
        for (int i = 0; i < n; i++) {
        	int k = data_copy.get(i);
        	if (relativeDistance(target, k) < relativeDistance(target, best)) {
        		best = k;
        		pos_best = i;
        	}
        }
        if (relativeDistance(target, best) < proximity1) {
        	data.remove(pos_best);
        	this.setAttackHard(best);
        }
        return best;
    }

    private static int closestPossibilityOneOp(int num1, int num2, int target) {
        /* On se restreint ici à l'addition et la multiplication comme opérateurs.
        * On cherche donc qui est le plus proche de target entre num1 + num2 et num1 * num2. */
    	int poss1 = num1 + num2;
    	int poss2 = num1 * num2;
    	if (relativeDistance(target, poss1) < relativeDistance(target, poss2)) return poss1;
    	else return poss2;
    }
    
    public int findClosestOneOp(int target) {
        /* Algorithme brute force qui renvoie le nombre le plus proche de la cible
        * en utilisant un unique opérateur, ici + ou x .
        * On fait d'abord une conversion, puis on teste toutes les possibilités avec une
        * boucle for. Enfin, on enlève les deux nombres à l'origine du résultat. */
    	double proximity1 = target / 10.0;
    	ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int best = Integer.MAX_VALUE;
        int pos_best1 = -1;
        int pos_best2 = -1;
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {
                int num1 = data_copy.get(i);
                int num2 = data_copy.get(j);
                int num = closestPossibilityOneOp(num1, num2, target);
                if (relativeDistance(target, num) < relativeDistance(target, best)) {
                    best = num;
                    pos_best1 = i;
                    pos_best2 = j;
                }
            }
        }
        if (relativeDistance(target, best) < proximity1) {
        	data.remove(pos_best1);
        	data.remove(pos_best2);
        	this.setAttackHard(best);
        }
        return best;
    }

    private static int closestPossibilityTwoOp(int num1, int num2, int num3, int target) {
        /* Même principe que OneOp, mais avec deux opérateurs. Afin de simplifier, les possibilités
        * sont mises dans un tableau, et on prend le meilleur résultat. */
    	int poss1 = num1 + num2 + num3;
    	int poss2 = num1 + num2 * num3;
    	int poss3 = (num1 + num2) * num3;
    	int poss4 = num1 * num2 + num3;
    	int poss5 = num1 * (num2 + num3);
    	int poss6 = num1 * num2 * num3;
    	int[] array = { poss1, poss2, poss3, poss4, poss5, poss6 };
    	int best = poss1;
        for (int i = 1; i < 6; i++) {
        	int k = array[i];
        	if (relativeDistance(target, k) < relativeDistance(target, best)) best = k;
        }
    	return best;
    }
    
    public int findClosestTwoOp(int target) {
        /* Même chose, mais avec une triple boucle et trois nombres cette fois. */
    	ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int best = Integer.MAX_VALUE;
        int pos_best1 = -1;
        int pos_best2 = -1;
        int pos_best3 = -1;
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++)
        		for (int k = j + 1; k < n; k++) {
		        	int num1 = data_copy.get(i);
	        		int num2 = data_copy.get(j);
	        		int num3 = data_copy.get(k);
	        		int num = closestPossibilityTwoOp(num1, num2, num3, target);
		        	if (relativeDistance(target, num) < relativeDistance(target, best)) {
		                best = num;
		                pos_best1 = i;
		                pos_best2 = j;
                        pos_best3 = k;
		        }
        	}
        }
        data.remove(pos_best1);
        data.remove(pos_best2);
        data.remove(pos_best3);
        this.setAttackHard(best);
        return best;
    }

    public void findAttack(int target){
        /* On utilise les fonctions précédentes, et on fait attention selon la quantité de nombres
        * dans l'inventaire du joueur. */
    	int n = this.inventory.size();
        if (n == 0) return;
        if (n == 1) this.findClosestZeroOp(target);
        if (n == 2) {
        	this.findClosestZeroOp(target);
        	this.findClosestOneOp(target);
        }
        if (n >= 3) {
        	this.findClosestZeroOp(target);
        	this.findClosestOneOp(target);
        	this.findClosestTwoOp(target);
        }
    }
    
    public void findDefence(){
        /* On multiplie trois nombres au hasard entre eux pour obtenir la défense. */
    	int nb_produits = 3;
        ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int res = 1;
        Random rand = new Random();
        while (n >= 0) {
        	int randInt = rand.nextInt(n);
            res *= data_copy.get(randInt);
            data_copy.remove(randInt);
            n--;
        }
        this.setDefenceHard(res);
    }
}

