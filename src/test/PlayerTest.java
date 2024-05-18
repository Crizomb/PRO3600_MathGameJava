package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import base.Player;
import base.Items;
import base.ItemsStack;
import base.Operator;

import java.util.Random;

class PlayerTest {

    @Test
    void createNewNumberStack() throws IllegalStateException {
        Player player = new Player();
        ItemsStack stack = player.stack;

        stack.push(new Items(4));
        stack.push(new Items(5));
        stack.push(new Items(Operator.ADD));

        player.createNewNumberStack();

        assertEquals(1, stack.size());
        assertEquals(9, stack.firstElem().getValue().getIntValue());
    }

    @Test
    void setDefence() throws IllegalStateException {
        Player player = new Player();
        player.stack.push(new Items(5));

        player.setDefence();

        assertEquals(5, player.getDefence_value());
        assertTrue(player.stack.isEmpty());
    }

    @Test
    void setDefenceHard() {
        Player player = new Player();
        player.setDefenceHard(10);
        assertEquals(10, player.getDefence_value());
    }

    @Test
    void setAttackHard() {
        Player player = new Player();
        player.setAttackHard(20);
        assertEquals(20, player.getAttack_value());
    }

    @Test
    void setAttack() throws IllegalStateException {
        Player player = new Player();
        player.stack.push(new Items(10));

        player.setAttack();

        assertEquals(10, player.getAttack_value());
        assertTrue(player.stack.isEmpty());
    }

    // ... (similar tests for getters and other methods)

    @Test
    void attack() throws Exception {
        Player player1 = new Player();
        Player player2 = new Player();

        player1.setAttackHard(50);
        player1.setDefenceHard(50);
        player2.setAttackHard(20);
        player2.setDefenceHard(30);

        assertTrue(player1.attack(player2)); // Player 1 attacks successfully
        assertFalse(player2.attack(player1)); // Player 2 attacks unsuccessfully
    }
    @Test
    void numberInInventory() {
        Player player = new Player();
        int random_index = (int) (Math.random() * player.inventory.size());
        Items itemInInventory = player.inventory.get(random_index);
        int valueToFind = itemInInventory.getValue().getIntValue();

        player.inventory.add(itemInInventory);

        assertEquals(itemInInventory, player.numberInInventory(valueToFind));
        assertNull(player.numberInInventory(-5896415)); // Item not in inventory
    }

    @Test
    void isInInventory() {
        Player player = new Player();
        int random_index = (int) (Math.random() * player.inventory.size());
        Items randomItem = player.inventory.get(random_index);
        int valueToFind = randomItem.getValue().getIntValue();

        assertTrue(player.isInInventory(valueToFind));
        assertFalse(player.isInInventory(15)); // Item not in inventory
    }

    @Test
    void itemInStack() {
        Player player = new Player();
        String itemValue = "+";
        Items itemInStack = new Items(Operator.ADD);

        player.stack.push(itemInStack);

        assertEquals(itemInStack, player.itemInStack(itemValue));
        assertNull(player.itemInStack("12")); // Item not in stack
    }

    @Test
    void isInStack() {
        Player player = new Player();
        String itemValue = "-";
        Items itemInStack = new Items(Operator.SUB);

        player.stack.push(itemInStack);

        assertTrue(player.isInStack(itemValue));
        assertFalse(player.isInStack("*")); // Item not in stack
    }

    @Test
    void pushNumberInStack() {
        Player player = new Player();
        int initialInventorySize = player.inventory.size();

        //TODO corriger pb
         //Items itemToPush = player.inventory.getFirst();
        Items itemToPush = player.inventory.get(0);
        player.pushNumberInStack(itemToPush);

        assertTrue(player.stack.contains(itemToPush));
        assertEquals(initialInventorySize - 1, player.inventory.size()); // Item removed from inventory
    }

    @Test
    void pushOperatorInStack() throws Exception {
        Player player = new Player();
        Items number1 = new Items(2);
        Items number2 = new Items(3);
        Items operator = new Items(Operator.ADD);

        player.stack.push(number1);
        player.stack.push(number2);

        player.pushOperatorInStack(operator);

        assertEquals(3, player.stack.size()); // Operator added to stack
        assertTrue(player.stack.firstElem().getValue().isOperator());
    }

    @Test
    void pushOperatorInStack_throwsExceptionWhenStackLessThanTwoElements() throws Exception {
        Player player = new Player();
        Items operator = new Items(Operator.ADD);

        assertThrows(Exception.class, () -> player.pushOperatorInStack(operator));
    }

    @Test
    void pushOperatorInStack_throwsExceptionWhenTopElementsAreNotNumbers() throws Exception {
        Player player = new Player();
        Items number1 = new Items(2);
        Items operator1 = new Items(Operator.ADD);

        player.stack.push(number1);
        player.stack.push(operator1);

        assertThrows(Exception.class, () -> player.pushOperatorInStack(operator1));
    }

    @Test
    void putObjectOutOfStack() {
        Player player = new Player();
        Items itemToPutOut = new Items(5);

        player.stack.push(itemToPutOut);
        int initialInventorySize = player.inventory.size();

        player.putObjectOutOfStack(itemToPutOut);

        assertTrue(player.inventory.contains(itemToPutOut));
        assertEquals(initialInventorySize+1, player.inventory.size()); // Item added to inventory if integer, inventory size increased
    }


    @Test
    void separateNumbers_addsParentsToInventoryIfCombinedNumber() throws NoSuchFieldException {
        Player player = new Player();
        Items child = new Items(15, new Items(5), new Items(10)); // Simulate combined number

        player.stack.push(child);
        int initialInventorySize = player.inventory.size();
        player.separateNumbers(child);

        assertEquals(initialInventorySize + 2, player.inventory.size()); // Parents added to inventory
        assertTrue(player.inventory.contains(child.getParent1()));
        assertTrue(player.inventory.contains(child.getParent2()));
    }

}




