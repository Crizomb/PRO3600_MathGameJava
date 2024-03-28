package base;
import java.util.*;


class Tools{
    /**
     *
     * @param min of the possible random number
     * @param max of the possible random number
     * @param nb_element in the created inventory
     * @return a random inventory of number
     */
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

/**
 * Represents a Player
 */
public class Player {
    private static final int INVENTORY_MAX_SIZE = 9;

    private static int pv_max = 500;

    private int pv = pv_max;
    public ItemsStack stack;
    public ArrayList<Items> inventory; // 9 number by player, those number will be used to attack or defend
    private int defence;
    private int attack;

    /**
     * Create a new Player
     */
    public Player(){
        this.stack = new ItemsStack();
        this.inventory = Tools.getRandomInventory(1, 10, INVENTORY_MAX_SIZE);
        this.attack = 0;
        this.defence = 0;
    }

    /**
     * Reinitialize a Player
     */
    public void init() {
        this.inventory = Tools.getRandomInventory(1, 10, INVENTORY_MAX_SIZE);
        this.stack.clear();
        this.attack = 0;
        this.defence = 0;
    }

    /**
     * Evaluates and combines numerical elements on the stack.
     *
     * <p>This function assumes the following conditions:
     * <ul>
     *   <li>The stack is not empty.
     *   <li>The top element on the stack is an operator.
     *   <li>The two elements below the operator are numerical values represented as {@code Items}.
     * </ul>
     *
     * <p>It performs the following actions:
     * <ol>
     *   <li>Pops the operator and its two operands from the stack.
     *   <li>Evaluates the expression {@code num2 op num1} using the popped operator and operands.
     *   <li>Creates a new {@code Items} object to hold the result, associating it with the original operands.
     *   <li>Pushes the new {@code Items} object back onto the stack.
     * </ol>
     *
     * @throws IllegalStateException if the stack is empty or the top element is not an operator.
     */
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
    /**
     * Sets the defense value.
     *
     * <p>This function assumes the following conditions:
     * <ul>
     *   <li>The stack is not empty.
     *   <li>The top element on the stack is a numerical value.
     * </ul>
     *
     * <p>It performs the following actions:
     * <ol>
     *   <li>Pops the top element from the stack.
     *   <li>If the element is a numerical value, sets the defense value to the element's value.
     *   <li>Otherwise, prints an error message and pushes the element back onto the stack.
     * </ol>
     *
     * @throws IllegalStateException if the stack is empty or the top element is not a numerical value.
     */

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

    /**
     * Sets the attack value.
     *
     * <p>This function assumes the following conditions:
     * <ul>
     *   <li>The stack is not empty.
     *   <li>The top element on the stack is a numerical value.
     * </ul>
     *
     * <p>It performs the following actions:
     * <ol>
     *   <li>Pops the top element from the stack.
     *   <li>If the element is a numerical value, sets the attack value to the element's value.
     *   <li>Otherwise, prints an error message and pushes the element back onto the stack.
     * </ol>
     *
     * @throws IllegalStateException if the stack is empty or the top element is not a numerical value.
     */

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

    /**
     * Attacks another player.
     *
     * <p>This function checks if the attacker's attack value is greater than or equal to the defender's defense value.
     * It throws an exception if either the attacker's attack or defense value is zero or not initialized.
     *
     * @param other_player The player to be attacked.
     * @return {@code true} if the attack is successful, {@code false} otherwise.
     * @throws Exception if the attacker's attack or defense value is zero or not initialized.
     */

    public boolean Attack(Player other_player) throws Exception {
        //
        if (this.attack == 0 || this.defence == 0){
            throw new Exception("Attack or defence is equal to 0 or is not initialized (call getAttack and getDefense)");
        }
        return this.attack >= other_player.defence;
    }
    /**
     * Finds an item with the specified numerical value in the inventory.
     *
     * <p>This function iterates through the inventory and returns the first {@code Items} object whose value matches the provided integer.
     *
     * @param n The numerical value to search for.
     * @return The {@code Items} object with the matching value, or {@code null} if no such item is found.
     */
    public Items NumberInInventory(int n) {
        for(Items i: inventory) {
            if (i.getValue().getIntValue()==n) {
                return i;
            }
        }
        return null;
    }
    /**
     * Checks if an item with the specified numerical value exists in the inventory.
     *
     * <p>This function iterates through the inventory and returns {@code true} if it finds an {@code Items} object whose value matches the provided integer. Otherwise, it returns {@code false}.
     *
     * @param n The numerical value to search for.
     * @return {@code true} if the item exists in the inventory, {@code false} otherwise.
     */
    public boolean isInInventory(int n) {
        for(Items i: inventory) {
            if (i.getValue().getIntValue()==n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an item in the stack with a matching string representation.
     *
     * <p>This function iterates through the stack and returns the first {@code Items} object whose value's string representation is equal to the provided string.
     *
     * @param s The string to compare with the item's string representation.
     * @return The {@code Items} object with the matching string representation, or {@code null} if no such item is found.
     */

    public Items ItemInStack(String s) {
        for(Items i: stack) {
            if (i.getValue().toString().equals(s)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Checks if an item with a matching string representation exists in the stack.
     *
     * <p>This function iterates through the stack and returns {@code true} if it finds an {@code Items} object whose value's string representation is equal to the provided string. Otherwise, it returns {@code false}.
     *
     * @param s The string to compare with the item's string representation.
     * @return {@code true} if the item exists in the stack, {@code false} otherwise.
     */

    public boolean isInStack(String s) {
        for(Items i: stack) {
            if (i.getValue().toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pushes an item from the inventory onto the stack.
     *
     * <p>This function attempts to push the provided {@code Items} object onto the stack. It performs the following actions:
     * <ol>
     *   <li>Checks if the item exists in the inventory.
     *   <li>If the item is found in the inventory:
     *       <ul>
     *           <li>Removes the item from the inventory.
     *           <li>Pushes the item onto the stack.
     *       </ul>
     *   <li>Otherwise, prints an error message indicating that the item is not in the inventory.
     * </ol>
     *
     * @param n The {@code Items} object to be pushed onto the stack.
     */
    public void pushNumberInStack(Items n) {
        if (inventory.contains(n)) {
            inventory.remove(n);
            stack.push(n);
        } else {
            System.out.print("Le nombre n'est pas dans l'inventaire.\n");
        }
    }

    /**
     * Pushes an operator onto the stack.
     *
     * <p>This function attempts to push the provided {@code Items} object onto the stack if the stack contains at least two elements and the top two elements are numbers. Otherwise, it prints an error message and throws an exception.
     *
     * @param op The {@code Items} object to be pushed onto the stack.
     * @throws Exception if the stack does not contain at least two elements or the top two elements are not numbers.
     */

    public void pushOperatorInStack(Items op) throws Exception {
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

    /**
     * Removes an item from the stack and potentially adds it to the inventory.
     *
     * <p>This function performs the following actions:
     * <ol>
     *   <li>Removes the specified {@code Items} object from the stack, using a method assumed to be named {@code popOnValue}.
     *   <li>If the removed item's value is an integer, adds the item to the inventory.
     * </ol>
     *
     * @param elem The {@code Items} object to be removed from the stack.
     */

    void putObjectOutOfStack(Items elem) {
        stack.popOnValue(elem);
        if (elem.getValue().isInt()) {
            inventory.add(elem);
        }
    }

    /**
     * Separates a combined number into its original components and adds them to the inventory.
     *
     * <p>This function attempts to separate the provided {@code Items} object if it was created by combining two numbers. It performs the following actions:
     * <ol>
     *   <li>Checks if the item has parent elements (indicating it was a combined number).
     *   <li>If the item has parents:
     *       <ul>
     *           <li>Retrieves the parent elements using {@code getParent1()} and {@code getParent2()}.
     *           <li>Removes the combined item from the stack using a method assumed to be named {@code popOnValue}.
     *           <li>Adds both parent elements (the original numbers) to the inventory.
     *       </ul>
     *   <li>Otherwise, prints an error message indicating that the item is not a combined number.
     * </ol>
     *
     * @param n The {@code Items} object to be separated.
     * @throws NoSuchFieldException if the {@code Items} class does not have the expected getter methods for parent elements.
     */

    void separateNumbers(Items n) throws NoSuchFieldException {
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
