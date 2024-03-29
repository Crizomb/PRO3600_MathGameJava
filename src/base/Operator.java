package base;

/**
 * Represents an Operator that can be used on the game Stack
 */
public enum Operator  {
    ADD, SUB, MUL, DIV;

    public String toString(){
        return switch (this) {
            case ADD -> "+";
            case SUB -> "-";
            case MUL -> "*";
            case DIV -> "/";
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

