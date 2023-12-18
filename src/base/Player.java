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
    private boolean[] can_be_used;
    // say if the number at the index i can be used.
    // (If a number in inventory at index j is used, it is not removed from inventory but can_be_used[j] is set to False)
    private int defence;
    private int attack;
    // If false it's the AttackPhase
    private boolean DefensePhase;

    public Player(){
        this.stack = new ItemsStack();
        this.inventory = Tools.getRandomInventory(1, 10, INVENTORY_MAX_SIZE);
    }

    public void updateDefense(){
        Operator op = this.stack.pop().getValue().getOperator();
        Items element1 = this.stack.pop();
        Items element2 = this.stack.pop();
        int num1 = element1.getValue().getIntValue();
        int num2 = element2.getValue().getIntValue();
        this.defence = op.Evaluate(num1, num2);
        // Defence is put back on the stack, in the form of Items, with its two parents (see Items class)
        this.stack.push(new Items(this.defence, element1, element2));
    }

    public void updateAttack(){
        Operator op = this.stack.pop().getValue().getOperator();
        Items element1 = this.stack.pop();
        Items element2 = this.stack.pop();
        int num1 = element1.getValue().getIntValue();
        int num2 = element2.getValue().getIntValue();
        this.attack = op.Evaluate(num1, num2);
        // Defence is put back on the stack, in the form of Items, with its two parents (see Items class)
        this.stack.push(new Items(this.attack, element1, element2));
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


    public void push_number_in_stack(Items n) {
        //Mets un nombre de l'inventaire dans la pile
        if (inventory.contains(n)) {
            inventory.remove(n);
            stack.push(n);
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
        }
    }

    public String toString(){
        return String.format("Player \n attack : %s \n defence : %s \n inventory %s", attack, defence, inventory);
    }



}
