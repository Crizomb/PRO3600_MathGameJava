package base;

public enum Operator {
    ADD, SUB, MUL, DIV;

    public String getString(){
        return switch (this) {
            case ADD -> "Add";
            case SUB -> "Sub";
            case MUL -> "Mul";
            case DIV -> "Div";
        };
    }

}

