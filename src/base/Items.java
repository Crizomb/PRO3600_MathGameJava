package base;

/**
 * Represents an item on the stack with optional parent tracking.
 *
 * <p>This class is immutable and encapsulates a value along with optional references to its parent items (if applicable).
 * It is used to track the history of combined values on the stack.</p>
 *
 *
 * <p>For example, consider a player performing the calculation 2 + 7 = 9. In this scenario:</p>
 * <ul>
 *   <li>An `Items` object with value 9, `hasParent` set to `true`, and references to the parent items (2 and 7) would be created to represent the result (9).</li>
 *   <li>Later, the player could use methods provided by this class to retrieve the original values (2 and 7) for further manipulation.</li>
 * </ul>
 */
public class Items {

    private OperatorOrInt value;
    private final boolean have_parent;
    private Items parent1;
    private Items parent2;

    /**
     * Create an Item from an Int
     */
    public Items(int value){
        this.value = new OperatorOrInt(value);
        this.have_parent = false;

    }
    /**
     * Create an Item from an Operator
     */
    public Items(Operator value){
        this.value = new OperatorOrInt(value);
        this.have_parent = false;

    }
    /**
     * Create an Item from an 'Int', and two 'Items' parents
     */
    public Items(int value, Items parent1, Items parent2){
        this.value = new OperatorOrInt(value);
        this.have_parent = true;
        this.parent1 = parent1;
        this.parent2 = parent2;

    }

    /**
     *
     * @return true if this Item have parent
     */
    public boolean getHaveParent() {
        return this.have_parent;
    }

    /**
     *
     * @return this.value
     */
    public OperatorOrInt getValue(){
        return this.value;
    }

    /**
     *
     * @return this.parent1 if it exists, else
     */
    public Items getParent1() throws NoSuchFieldException {

        if (this.have_parent){
            return this.parent1;
        }
        else{
            throw new NoSuchFieldException("This Item doesn't have parents");
        }

    }
    /**
     *
     * @return this.parent2 if it exists, else
     */
    public Items getParent2() throws NoSuchFieldException {

        if (this.have_parent){
            return this.parent2;
        }
        else{
            throw new NoSuchFieldException("This Item doesn't have parents");
        }

    }

    public String toString(){
        if (this.have_parent) {
            return String.format("Items(value: %s, parent1: %s, parent2: %s)", value, parent1, parent2);
        }
        else {
            return String.format("Items(value: %s)", value);
        }
    }

}
