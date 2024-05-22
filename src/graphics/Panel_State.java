package graphics;

public enum Panel_State {

    //les différents panneaux
    temporary,
    MENU, DEFAULT, game_settings, gameplay,
    player_1_attack(1), player_2_attack(1), player_1_defense(1), player_2_defense(1);


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

    public static Panel_State getPanelModePlayer(String text){
        switch (text){
            case "Defense1":{
                return player_1_defense;
            }
            case "Defense2":{
                return player_2_defense;
            }
            case "Attack1":{
                return player_1_attack;
            }
            case "Attack2":{
                return player_2_attack;
            }
        }
        assert 1==0;
        System.out.println("aie pas de pstate trouvée");
        return null;
    }


}
