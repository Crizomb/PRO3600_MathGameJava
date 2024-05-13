package ia;
import java.util.*;
import base.*;

public class IA extends Player{

    // Sense : Regarder la liste de nombres à disposition de l'IA.
    // Think : Produire un nombre difficilement atteignable selon une heuristique à choisir.
    // Act : Appeler UpdateDefense ou UpdateAttack pour mettre à jour la défense ou l'attaque.

    public IA(List<Items> number_inventory){ super(number_inventory); }

    public static double relativeDistance(int target, int x){
        return Math.abs(x - target) / target;
    }

    public Items findClosestZeroOp(int target){
        int mini = Integer.MAX_VALUE;
        Items item = NULL;
        Iterator<Items> iter =  this.inventory.iterator();
        while(iter.hasNext()){
            if (iter.next().getValue().isInt()) {
                Items candidate = iter.next();
                if (candidate.getValue().getIntValue() < mini) {
                    mini = candidate.getValue().getIntValue();
                    item = candidate; 
                }
            }
        }
        return item;
    }

    public Items findClosestOneOp(int target);

    public Items findClosestTwoOp(int target);

    public Items findClosestThreeOp(int target);

    public int findAttack(int aim){
        double proximity1 = aim / 10;
        double proximity2 = aim / 20;
        int nb_operations = 0;
        int res = 1;
        int n = this.inventory.get(0);
        while (nb_operations <= 3 && Player.relativeDistance(n, res) >= 0.05)
        // Essayer de minimiser le nombre d'opérations nécessaires pour être en dessous
        // d'un seuil de distance "acceptable", qui rétrécit lorsqu'on augmente le nombre d'opérations
        // TODO : Prendre le nombre le plus proche au bout de deux opérations si proche de 10%, ou
        // trois opérations si proche de 5%.
    }

    static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;
 
        // Check from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
 
        return true;
    }
    
    public int findDefence(){
    	int nb_produits = 3;
        ArrayList<Items> data = this.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
        	data_copy.add(data.get(i).getValue().getIntValue());
        }
        int i = 0, res = 0;
        Random rand = new Random();
        while (i < n && i < nb_produits) {
        	int randInt = rand.nextInt(n);
            int res = data_copy.get(randInt);
            data_copy.remove(randInt);
            i++;
        }
        return res;
        // data_copy.sort(null);
        // int n1 = 0;
        // int n2 = 0;
        // while (n1 = 0 || n2 = 0) {
        	
        }
        // Idée 1 : Produit de trois nombres au hasard.
        // Idée 2 : Produit de "grands" nombres premiers (sauf 2)
        // TODO : Multiplier les deux plus grands nombres premiers.
    }

}
