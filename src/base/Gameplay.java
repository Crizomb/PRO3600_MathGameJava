package base;
import java.util.Timer;
import java.util.TimerTask;

public class Gameplay {
    int etat = 0; //représente la phase de jeu en cours. 0 phase de défense, 1 phase d'attaque
    static void degats(Player p1, Player p2) {
        int degat_max = 200;
        int new_pv1 = (int) Math.max(0, p1.getPv() - (degat_max*(1-Math.sqrt(Math.abs(p2.getAttack()-p1.getDefence()))/p1.getDefence())) );
        int new_pv2 = (int) Math.max(0, p2.getPv() - (degat_max*(1-Math.sqrt(Math.abs(p1.getAttack()-p2.getDefence()))/p2.getDefence())) );
        p1.setPv(new_pv1);
        p2.setPv(new_pv2);
    }
    private class passageDefenseAttaque extends TimerTask {
        public void run() {
            etat = 1;
        }
    }
    private class compteAReboursDefense { //duree est en secondes //pas finis
        int duree = 30; //en secondes
        Timer timer = new Timer();
        TimerTask task = new passageDefenseAttaque();
    }
}
