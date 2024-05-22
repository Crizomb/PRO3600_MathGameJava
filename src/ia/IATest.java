package ia;
import base.Items;
import base.Player;
import ia.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static ia.IA.relativeDistance;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Youssef
 * @version 1 Tests pour l'I.A. <br>
 * Testing {@link ia.IA}.
 */

class IATest {

    /**
     * Test 1: 3 est plus proche de 4 que 2.
     */
    @Test
    @DisplayName("Test 1: 3 est plus proche de 4 que 2.")
    void testRelativeDistance() {
        Assertions.assertTrue(relativeDistance(2,4) > relativeDistance(3,4));
    }

    /**
     * Test 2: ClosestZeroOp renvoie le max du tableau si target est plus grand que 10.
     */
    @Test
    @DisplayName("Test 2 : findClosestZeroOp")
    void findClosestZeroOp() {
        IA ia = new IA();
        System.out.println(ia);
        int target = 15;
        ArrayList<Items> data = ia.inventory;
        int n = data.size();
        ArrayList<Integer> data_copy = new ArrayList<Integer>();
        for (Items datum : data) {
            data_copy.add(datum.getValue().getIntValue());
        }
        int best = -1;
        for (int i = 0; i < n; i++) {
            int k = data_copy.get(i);
            if (k > best) best = k;
        }
        assertEquals(ia.findClosestZeroOp(target), best);
    }

    @Test
    void findClosestOneOp() {
    }

    @Test
    void findClosestTwoOp() {
    }

    @Test
    void findAttack() {
    }

    @Test
    void findDefence() {
    }
}