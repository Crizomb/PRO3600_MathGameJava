package graphics;

public enum AnchorPurpose {
    Number_Reserve, Operator_reserve, Operator_jar, Number_jar, Body_left_player, Body_right_player;

    // la destination d'un objet situé a purpose si le joueur clique rapidement dessus
    public AnchorPurpose mirror(){
        switch (this){
           /* case Number_jar -> {
                return Number_Reserve;
            }*/
            case Number_Reserve :
            {
                return Number_jar;
            }
            case Operator_jar :{
                return Operator_reserve;
            }
            case Operator_reserve :{
                return Operator_jar;
            }
            /*case Body_left_player -> {
                return Number_Reserve;
            }*/

            default : {
                return Number_Reserve;
            }
        }
    }
}
