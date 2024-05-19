package base;

/**
 * Represents an Operator that can be used on the game Stack
 */
public enum Operator  {
    ADD, SUB, MUL, DIV;

    public String toString(){
        switch (this){
            case ADD:{
                return "+";
            }
            case SUB:{
                return "-";
            }
            case MUL:{
                return "*";
            }
            case DIV:{
                return "/";
            }
        }
       /* return switch (this) {
            case ADD -> "+";
            case SUB -> "-";
            case MUL -> "*";
            case DIV -> "/";
        };*/
        return "null";
    }

    public static Operator getOperator(String c){
        switch (c){
            case "+":{
                return ADD;
            }
            case "-":{
                return SUB;
            }
            case "*":{
                return MUL;
            }
            case "/":{
                return DIV;
            }
            default:{
                return null;
            }
        }}

    public int Evaluate(int num1, int num2){

        switch (this){
            case ADD:{
                return num1+num2;
            }
            case SUB:{
                return num1-num2;
            }
            case MUL:{
                return num1*num2;
            }
            case DIV:{
                return num1/num2;
            }
        }
        /*return switch (this){
            case ADD -> num1+num2;
            case SUB -> num1-num2;
            case MUL -> num1*num2;
            case DIV -> num1/num2;
        };*/
        return 0;
    }

}

