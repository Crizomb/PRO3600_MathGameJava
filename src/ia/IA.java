package ia;
import java.util.*;
import base.*;

// Sense : Regarder la liste de nombres à disposition de l'IA.
// Think : Produire un nombre difficilement atteignable selon une heuristique à choisir.
// Act : Appeler UpdateDefense ou UpdateAttack pour mettre à jour la défense ou l'attaque.

public class IA extends Player {

    public IA(){ super(); }

    public static double relativeDistance(int target, int x){
        return (double) Math.abs(x - target) / target;
    }

    public void findClosestZeroOp(int target){
    	double proximity1 = target / 10.0;
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
    }

    private static int closestPossibilityOneOp(int num1, int num2, int target) {
    	int poss1 = num1 + num2;
    	int poss2 = num1 * num2;
    	if (relativeDistance(target, poss1) < relativeDistance(target, poss2)) return poss1;
    	else return poss2;
    }
    
    public void findClosestOneOp(int target) {
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
    }

    private static int closestPossibilityTwoOp(int num1, int num2, int num3, int target) {
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
    
    public void findClosestTwoOp(int target) {
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
    }

    public void findAttack(int target){
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
        // Essayer de minimiser le nombre d'opérations nécessaires pour être en dessous
        // d'un seuil de distance "acceptable", qui rétrécit lorsqu'on augmente le nombre d'opérations
        // TODO : Prendre le nombre le plus proche au bout de deux opérations si proche de 10%, ou
        // trois opérations si proche de 5%.
    }

//    static boolean isPrime(int n)
//    {
//        // Corner case
//        if (n <= 1)
//            return false;
//
//        // Check from 2 to sqrt(n)
//        for (int i = 2; i <= Math.sqrt(n); i++)
//            if (n % i == 0)
//                return false;
//
//        return true;
//    }
    
    public void findDefence(){
    	int nb_produits = 3;
        ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int i = 0, res = 1;
        Random rand = new Random();
        while (i < n && i < nb_produits) {
        	int randInt = rand.nextInt(n);
            res *= data_copy.get(randInt);
            data_copy.remove(randInt);
            i++;
        }
        this.setDefenceHard(res);

        }
        // Idée 1 : Produit de trois nombres au hasard.
        // Idée 2 : Produit de "grands" nombres premiers (sauf 2)
        // TODO : Multiplier les deux plus grands nombres premiers.
    }

}