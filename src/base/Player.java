package base;
import java.util.List;

public class Player {
    private static final int INVENTORY_SIZE = 9;
    public Stack stack;
    private List inventory; // 9 number by player, those number will be used to attack or defend
    private boolean[] can_be_used;
    // say if the number at the index i can be used.
    // (If a number in inventory at index j is used, it is not removed from inventory but can_be_used[j] is set to False)
    private int defence;
    private int attack;
    // If false it's the AttackPhase
    private boolean DefensePhase;

    public Player(List number_inventory){
        this.stack = new Stack();
        this.inventory = number_inventory;
    }

    public void UpdateDefense(){
        Operator op = this.stack.Pop().getValue().getOperator();
        Items element1 = this.stack.Pop();
        Items element2 = this.stack.Pop();
        int num1 = element1.getValue().getIntValue();
        int num2 = element2.getValue().getIntValue();
        this.defence = op.Evaluate(num1, num2);
        // Defence is put back on the stack, in the form of Items, with its two parents (see Items class)
        this.stack.Push(new Items(this.defence, element1, element2));
    }

    public void UpdateAttack(){
        Operator op = this.stack.Pop().getValue().getOperator();
        Items element1 = this.stack.Pop();
        Items element2 = this.stack.Pop();
        int num1 = element1.getValue().getIntValue();
        int num2 = element2.getValue().getIntValue();
        this.attack = op.Evaluate(num1, num2);
        // Defence is put back on the stack, in the form of Items, with its two parents (see Items class)
        this.stack.Push(new Items(this.attack, element1, element2));
    }

    public boolean Attack(Player other_player) throws Exception {
        //
        if (this.attack == 0 || this.defence == 0){
            throw new Exception("Attack or defence is equal to 0 or is not initialized (call getAttack and getDefense)");
        }
        return this.attack >= other_player.defence;

    }

    void number_in_stack(Items n) {

    }

    void separate_numbers(Items n) throws NoSuchFieldException {
        if (n.getHaveParent()) {
            Items p1 = n.getParent1();
            Items p2 = n.getParent2();
            Items elem_deleted = this.stack.Pop(n);
            inventory.add(p1);
            inventory.add(p2);
        }
    }



}
