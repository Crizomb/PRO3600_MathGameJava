package base;

public enum Operator {
    ADD, SUB, MUL, DIV;

    public String toString(){
        switch (this){
            default:{
                return "null";
            }

            case ADD : {
                return "ADD";
            }
            case SUB : {
                return "SUB";
            }
            case MUL : {
                return "MUL";
            }
            case DIV : {
                return "DIV";
            }

        }
        /*return switch (this) {
            case ADD -> "Add";
            case SUB -> "Sub";
            case MUL -> "Mul";
            case DIV -> "Div";
        };*/
    }

    public int Evaluate(int num1, int num2){
        //TODO Remettre le code 2
        switch (this){
            default:{
                return 0;
            }

            case ADD : {
                return num1+num2;
            }
            case SUB : {
                return num1-num2;
            }
            case MUL : {
                return num1*num2;
            }
            case DIV : {
                return num1/num2;
            }

        }/*        return switch (this){
            case ADD -> num1+num2;
            case SUB -> num1-num2;
            case MUL -> num1*num2;
            case DIV -> num1/num2;
        };*/
    }

}

