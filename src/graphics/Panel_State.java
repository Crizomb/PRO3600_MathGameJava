package graphics;

public enum Panel_State {

    //les différents panneaux
    MENU, DEFAULT, game_settings, gameplay,
    player_1_attack(1), player_2_attack(2), player_1_defense(1), player_2_defense(1);

    private int layer;

     Panel_State(int layer){
        this.layer = layer;
    }

    Panel_State(){
         this(0);
    }

    public int getLayer(){
         return layer;
    }


}
