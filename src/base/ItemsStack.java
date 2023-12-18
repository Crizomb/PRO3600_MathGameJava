package base;

public class ItemsStack extends java.util.Stack<Items> {

    public ItemsStack(){
        super();
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
        ItemsStack newStack = new ItemsStack();

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
            newStack.push(tempStack.pop());
        }

        // Clear the current stack and add all elements from newStack
        this.clear();
        while (!newStack.isEmpty()) {
            this.push(newStack.pop());
        }
    }
}
