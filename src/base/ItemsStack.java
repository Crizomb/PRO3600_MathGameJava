package base;

/**
 * Class representing the Stack where operators and numbers are put in the game
 */
public class ItemsStack extends java.util.Stack<Items> {


    public ItemsStack(){
        super();
    }

    /**
     * Get the first element of the stack and put it back on the stack.
     * @return the first element of the stack or null if the stack is null
     */
    public Items firstElem() {

        if (!this.isEmpty()) {
            Items first_elem = this.pop();
            this.push(first_elem);
            return first_elem;
        } else {
            System.out.print("La pile est vide.\n");
            return null;
        }
    }

    /**
     * Check if the two first element of stack are number (useful before doing operations on the stack)
     * @return true if the two first element of stack are representing number, else false
     */

    public boolean twoFirstElementAreNumber(){
        if (this.size() >= 2){
            Items top = this.get(this.size() - 1);
            Items second = this.get(this.size() - 2);
            return top.getValue().isInt() && second.getValue().isInt();
        }
        return false;
    }

    /**
     * Remove the first element of the stack equals to value, without returning it
     * @param value, the value to remove
     */

    public void popOnValue(Items value) {

        ItemsStack tempStack = new ItemsStack();
        boolean found = false;

        // Transfer elements to tempStack, skipping the target value
        while (!this.isEmpty()) {
            Items currentItem = this.pop();
            if (!currentItem.equals(value) || found) {
                tempStack.push(currentItem);
            }
            else{
                found = true;
            }
        }

        // Transfer back to newStack to preserve order
        while (!tempStack.isEmpty()) {
            this.push(tempStack.pop());
        }
    }
}
