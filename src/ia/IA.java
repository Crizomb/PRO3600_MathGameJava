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

    public int findDefence(){
        ArrayList<Items> data = this.getInventory(); // À vérifier si le transtype fonctionne
        ArrayList<Items> sortedData = data.reverseOrder();
        // Produit de "grands" nombres premiers (sauf 2) ?
        // TODO : Multiplier les deux plus grands nombres premiers.
    }

}
