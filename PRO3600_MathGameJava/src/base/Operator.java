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

    public int Evaluate(int num1, int num2){
        return switch (this){
            case ADD -> num1+num2;
            case SUB -> num1-num2;
            case MUL -> num1*num2;
            case DIV -> num1/num2;
        };
    }

}

