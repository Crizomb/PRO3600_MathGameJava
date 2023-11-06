package base;

public class OperatorOrInt {
    private Operator operator;
    private int intValue;

    public OperatorOrInt(Operator operator) {
        this.operator = operator;
    }

    public OperatorOrInt(int intValue) {
        this.intValue = intValue;
    }

    public boolean isOperator() {
        return operator != null;
    }

    public boolean isInt() {
        return intValue != 0;
    }

    public Operator getOperator() {
        if (!isOperator()) {
            throw new IllegalStateException("Not an Operator");
        }
        return operator;
    }

    public int getIntValue() {
        if (!isInt()) {
            throw new IllegalStateException("Not an int value");
        }
        return intValue;
    }
}
