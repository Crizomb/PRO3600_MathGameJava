package test;

import base.Items;
import base.ItemsStack;
import base.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsStackTest {

    @Test
    void testFirstElem_EmptyStack() {
        ItemsStack stack = new ItemsStack();
        assertNull(stack.firstElem(), "Empty stack should return null on firstElem");
    }

    @Test
    void testFirstElem_OneElement() {
        ItemsStack stack = new ItemsStack();
        Items item = new Items(10);
        stack.push(item);

        Items firstItem = stack.firstElem();
        assertNotNull(firstItem, "Stack with one element should return the element");
        assertEquals(item, firstItem, "Returned element should be the same");
    }

    @Test
    void testFirstElem_MultipleElements() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        stack.push(item2);
        stack.push(item1);

        Items firstItem = stack.firstElem();
        assertNotNull(firstItem, "Stack with elements should return the first element");
        assertEquals(item1, firstItem, "Returned element should be the first element");
    }

    @Test
    void testTwoFirstElementAreNumber_EmptyStack() {
        ItemsStack stack = new ItemsStack();
        assertFalse(stack.twoFirstElementAreNumber(), "Empty stack shouldn't have numbers");
    }

    @Test
    void testTwoFirstElementAreNumber_OneElement() {
        ItemsStack stack = new ItemsStack();
        Items item = new Items(10);
        stack.push(item);

        assertFalse(stack.twoFirstElementAreNumber(), "One element stack shouldn't have two numbers");
    }

    @Test
    void testTwoFirstElementAreNumber_TwoElements_BothNumbers() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        stack.push(item2);
        stack.push(item1);

        assertTrue(stack.twoFirstElementAreNumber(), "Two elements should be numbers");
    }

    @Test
    void testTwoFirstElementAreNumber_TwoElements_FirstNotNumber() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(Operator.ADD);
        Items item2 = new Items(20);
        stack.push(item2);
        stack.push(item1);

        assertFalse(stack.twoFirstElementAreNumber(), "First element shouldn't be a number");
    }

    @Test
    void testPopOnValue_EmptyStack() {
        ItemsStack stack = new ItemsStack();
        Items value = new Items(10);
        stack.popOnValue(value);

        assertTrue(stack.isEmpty(), "Pop on empty stack should not change the fact that it's empty");
    }

    @Test
    void testPopOnValue_SingleElement_Match() {
        ItemsStack stack = new ItemsStack();
        Items item = new Items(10);
        stack.push(item);

        Items value = new Items(10);
        stack.popOnValue(value);

        assertTrue(stack.isEmpty(), "Stack should be empty after removing the only element");
    }

    @Test
    void testPopOnValue_SingleElement_NoMatch() {
        ItemsStack stack = new ItemsStack();
        Items item = new Items(10);
        stack.push(item);

        Items value = new Items(20);
        stack.popOnValue(value);

        assertFalse(stack.isEmpty(), "Stack shouldn't be empty if value not found");
        assertEquals(item, stack.peek(), "Original element should still be on top");
    }

    @Test
    void testPopOnValue_MultipleElements_MatchFirst() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        Items item3 = new Items(30);
        stack.push(item3);
        stack.push(item2);
        stack.push(item1);

        Items value = new Items(10);
        stack.popOnValue(value);

        assertEquals(item2, stack.peek(), "Second element should be on top after removing first");
        assertEquals(2, stack.size(), "Stack size should decrease by 1");
    }

    @Test
    void testPopOnValue_MultipleElements_MatchMiddle() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        Items item3 = new Items(30);
        stack.push(item3);
        stack.push(item2);
        stack.push(item1);

        Items value = new Items(20);
        stack.popOnValue(value);

        assertEquals(item1, stack.peek(), "First element should be on top after removing middle");
        assertEquals(2, stack.size(), "Stack size should decrease by 1");
    }

    @Test
    void testPopOnValue_MultipleElements_MatchEnd() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        Items item3 = new Items(30);
        stack.push(item3);
        stack.push(item2);
        stack.push(item1);

        Items value = new Items(20);
        stack.popOnValue(value);

        assertEquals(item1, stack.peek(), "First element should be on top after removing middle");
        assertEquals(2, stack.size(), "Stack size should decrease by 1");
    }

    @Test
    void testPopOnValue_MultipleElements_NoMatch() {
        ItemsStack stack = new ItemsStack();
        Items item1 = new Items(10);
        Items item2 = new Items(20);
        Items item3 = new Items(30);
        stack.push(item3);
        stack.push(item2);
        stack.push(item1);

        Items value = new Items(40);
        stack.popOnValue(value);

        assertEquals(item1, stack.peek(), "Original order should be preserved");
        assertEquals(3, stack.size(), "Stack size shouldn't change");
    }
}

