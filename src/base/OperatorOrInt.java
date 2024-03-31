package base;

import java.util.Objects;

/**
 * Union class representing an Operator or an Int
 */
public class OperatorOrInt {
    private Operator operator;
    private int intValue;

    /**
     * Create OperatorOrInt from an operator
     */
    public OperatorOrInt(Operator operator) {
        this.operator = operator;
    }

    /**
     * Create OperatorOrInt from an Int
     */
    public OperatorOrInt(int intValue) {
        this.intValue = intValue;
    }

    /**
     *
     * @return true if representing an Operator else false
     */
    public boolean isOperator() {
        return operator != null;
    }

    /**
     *
     * @return true if representing an Int else false
     */

    public boolean isInt() {
        return intValue != 0;
    }

    /**
     *
     * @return the operator, if representing an Operator, else throw IllegalStateException
     */
    public Operator getOperator() {
        if (!isOperator()) {
            throw new IllegalStateException("Not an Operator");
        }
        return operator;
    }
    /**
     *
     * @return the int, if representing an Int, else throw IllegalStateException
     */
    public int getIntValue() {
        if (!isInt()) {
            throw new IllegalStateException("Not an int value");
        }
        return intValue;
    }

    public String toString(){
        if (isInt()){
            return String.format("%s", intValue);
        }
        return String.format("%s", operator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatorOrInt that = (OperatorOrInt) o;
        return intValue == that.intValue && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, intValue);
    }
}
