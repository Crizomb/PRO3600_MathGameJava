package graphics;

public enum Panel_State {

        //les différents panneaux
        menu_1, menu_2, game_attack_1, game_attack_2;

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
