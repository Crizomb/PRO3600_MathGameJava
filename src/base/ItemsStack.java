package base;

public class ItemsStack extends java.util.Stack<Items> {

    public ItemsStack(){
        super();
    }

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
    public boolean twoFirstElementAreNumber(){
        if (this.size() >= 2){
            Items top = this.get(this.size() - 1);
            Items second = this.get(this.size() - 2);
            return top.getValue().isInt() && second.getValue().isInt();
        }
        return false;
    }

    public void popOnValue(Items value) {
        ItemsStack tempStack = new ItemsStack();

        // Transfer elements to tempStack, skipping the target value
        while (!this.isEmpty()) {
            Items currentItem = this.pop();
            if (!currentItem.equals(value)) {
                tempStack.push(currentItem);
            } else {
                break; // Stop once the target value is found and popped
            }
        }

        // Transfer back to newStack to preserve order
        while (!tempStack.isEmpty()) {
            this.push(tempStack.pop());
        }
    }
}
