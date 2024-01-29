package base;
import java.util.*;


class Tools{

    public static ArrayList<Items> getRandomInventory(int min, int max, int nb_element){
        Random rand = new Random();
        ArrayList<Items> returnArray = new ArrayList<>();
        for (int i = 0; i<nb_element; i++){
            int randomInt = rand.nextInt(max - min + 1) + min;
            returnArray.add(new Items(randomInt));
        }
        return returnArray;

    }
}

public class Player {
    private static final int INVENTORY_MAX_SIZE = 9;

    private static int pv_max = 500;

    private int pv = pv_max;
    public ItemsStack stack;
    public ArrayList<Items> inventory; // 9 number by player, those number will be used to attack or defend
    private int defence;
    private int attack;

    public Player(){
        this.stack = new ItemsStack();
        this.inventory = Tools.getRandomInventory(1, 10, INVENTORY_MAX_SIZE);
        this.attack = 0;
        this.defence = 0;
    }

    public void init() {
        this.inventory = Tools.getRandomInventory(1, 10, INVENTORY_MAX_SIZE);
        this.stack.clear();
        this.attack = 0;
        this.defence = 0;
    }

    public void create_new_number_stack() {
        if ((!stack.isEmpty())&&(stack.firstElem().getValue().isOperator())) {
            Operator op = this.stack.pop().getValue().getOperator();
            Items element1 = this.stack.pop();
            Items element2 = this.stack.pop();
            int num1 = element1.getValue().getIntValue();
            int num2 = element2.getValue().getIntValue();
            int new_val = op.Evaluate(num1, num2);
            // Defence is put back on the stack, in the form of Items, with its two parents (see Items class)
            this.stack.push(new Items(new_val, element1, element2));
        }
    }

    public void setDefence() {
        if (stack.size()==1) {
            Items elem = stack.pop();
            if (elem.getValue().isInt()) {
                this.defence = elem.getValue().getIntValue();
            } else {
                System.out.print("L'élément est un opérateur et ne peut pas être défini comme la défense.\n");
                stack.push(elem);
            }
        } else {
            System.out.print("La pile est vide\n");
        }
    }

    public void setDefenceHard(int def) {
        this.defence = def;
    }

    public void setAttackHard(int att) {
        this.attack = att;
    }
    public void setAttack() {
        if (stack.size()==1) {
            Items elem = stack.pop();
            if (elem.getValue().isInt()) {
                this.attack = elem.getValue().getIntValue();
            } else {
                System.out.print("L'élément est un opérateur et ne peut pas être défini comme la défense.\n");
                stack.push(elem);
            }
        } else {
            System.out.print("La pile est vide\n");
        }
    }

    public int getDefence() {
        return this.defence;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getPv() {
        return this.pv;
    }

    public void setPv(int new_pv) {
        this.pv = new_pv;
    }
    public boolean Attack(Player other_player) throws Exception {
        //
        if (this.attack == 0 || this.defence == 0){
            throw new Exception("Attack or defence is equal to 0 or is not initialized (call getAttack and getDefense)");
        }
        return this.attack >= other_player.defence;

    }

    public Items nbDansInventaire(int n) {
        //Pour un nombre donné, on renvoie l'Item correspondant se trouvant dans l'inventaire
        for(Items i: inventory) {
            if (i.getValue().getIntValue()==n) {
                return i;
            }
        }
        return null;
    }

    public boolean estDansInventaire(int n) {
        for(Items i: inventory) {
            if (i.getValue().getIntValue()==n) {
                return true;
            }
        }
        return false;
    }

    public Items elemDansPile(String s) {
        //Pour un nombre donné, on renvoie l'Item correspondant se trouvant dans la pile
        for(Items i: stack) {
            if (i.getValue().toString().equals(s)) {
                return i;
            }
        }
        return null;
    }

    public boolean estDansPile(String s) {
        for(Items i: stack) {
            if (i.getValue().toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void push_number_in_stack(Items n) {
        //Mets un nombre de l'inventaire dans la pile
        if (inventory.contains(n)) {
            inventory.remove(n);
            stack.push(n);
        } else {
            System.out.print("Le nombre n'est pas dans l'inventaire.\n");
        }
    }

    public void push_operator_in_stack(Items op) throws Exception {
        //met un opérateur dans la pile, La pile doit contenir au moins deux éléments, et les deux éléments en haut de la pile doivent être des nombres
        if ( (op.getValue().isOperator())&&(stack.size() >= 2)&&(stack.twoFirstElementAreNumber()) ) {
            stack.push(op);
        }
        else
        {
            System.out.println("La pile doit contenir au moins deux éléments, et les deux éléments en haut de la pile doivent être des nombres \n" +
                    "fais attention aux references aussi mon reuf. Si t'ajoute deux fois la ref ça marche pas");
            throw new Exception("Can't push operator in this stack");
        }
    }

    void put_object_out_of_stack(Items elem) {
        //enlève un élément de la pile, le remet dans l'inventaire si c'est un nombre
        stack.popOnValue(elem);
        if (elem.getValue().isInt()) {
            inventory.add(elem);
        }
    }

    void separate_numbers(Items n) throws NoSuchFieldException {
        //Sépare un nombre en les deux autres nombres qui lui ont donné naissance s'il y en a
        if (n.getHaveParent()) {
            Items p1 = n.getParent1();
            Items p2 = n.getParent2();
            this.stack.popOnValue(n);
            inventory.add(p1);
            inventory.add(p2);
        } else {
            System.out.print("L'élément choisi n'est pas issu de la combinaison de deux nombres\n");
        }
    }

    public String toString(){
        return String.format("Player \n inventory %s \n Stack %s \n PV %s", inventory, stack, pv);
    }



}
