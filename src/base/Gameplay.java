package base;

public class Gameplay {
    void degats(Player p1, Player p2) {
        int degat_max = 200;
        int new_pv1 = (int) Math.max(0, p1.getPv() - (degat_max*(1-Math.sqrt(Math.abs(p2.getAttack()-p1.getDefence()))/p1.getDefence())) );
        int new_pv2 = (int) Math.max(0, p2.getPv() - (degat_max*(1-Math.sqrt(Math.abs(p1.getAttack()-p2.getDefence()))/p2.getDefence())) );
        p1.setPv(new_pv1);
        p2.setPv(new_pv2);
    }
}
