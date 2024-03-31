package test;
import base.OperatorOrInt;
import base.Operator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorOrIntTest {

    @Test
    void isOperator_whenCreatedWithOperator_shouldReturnTrue() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(Operator.ADD);
        assertTrue(operatorOrInt.isOperator());
    }

    @Test
    void isOperator_whenCreatedWithInt_shouldReturnFalse() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(10);
        assertFalse(operatorOrInt.isOperator());
    }

    @Test
    void isInt_whenCreatedWithOperator_shouldReturnFalse() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(Operator.ADD);
        assertFalse(operatorOrInt.isInt());
    }

    @Test
    void isInt_whenCreatedWithInt_shouldReturnTrue() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(10);
        assertTrue(operatorOrInt.isInt());
    }

    @Test
    void getOperator_whenCreatedWithOperator_shouldReturnOperator() {
        Operator operator = Operator.ADD;
        OperatorOrInt operatorOrInt = new OperatorOrInt(operator);
        assertEquals(operator, operatorOrInt.getOperator());
    }

    @Test
    void getOperator_whenCreatedWithInt_shouldThrowIllegalStateException() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(10);
        assertThrows(IllegalStateException.class, operatorOrInt::getOperator);
    }

    @Test
    void getIntValue_whenCreatedWithInt_shouldReturnInt() {
        int value = 10;
        OperatorOrInt operatorOrInt = new OperatorOrInt(value);
        assertEquals(value, operatorOrInt.getIntValue());
    }

    @Test
    void getIntValue_whenCreatedWithOperator_shouldThrowIllegalStateException() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(Operator.ADD);
        assertThrows(IllegalStateException.class, operatorOrInt::getIntValue);
    }

    @Test
    void testToString_whenCreatedWithInt_shouldReturnStringRepresentationOfInt() {
        int value = 10;
        OperatorOrInt operatorOrInt = new OperatorOrInt(value);
        assertEquals(String.format("%s", value), operatorOrInt.toString());
    }

    @Test
    void testToString_whenCreatedWithOperator_shouldReturnStringRepresentationOfOperator() {
        Operator operator = Operator.ADD;
        OperatorOrInt operatorOrInt = new OperatorOrInt(operator);
        assertEquals(String.format("%s", operator), operatorOrInt.toString());
    }

    @Test
    void testEquals_whenSameObject_shouldReturnTrue() {
        OperatorOrInt operatorOrInt = new OperatorOrInt(Operator.ADD);
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(Operator.ADD);
        assertEquals(operatorOrInt, operatorOrInt1);
    }

    @Test
    void testEquals_whenEqualObjectsWithOperator_shouldReturnTrue() {
        Operator operator = Operator.ADD;
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(operator);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(operator);
        assertEquals(operatorOrInt1, operatorOrInt2);
    }

    @Test
    void testEquals_whenEqualObjectsWithInt_shouldReturnTrue() {
        int value = 10;
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(value);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(value);
        assertEquals(operatorOrInt1, operatorOrInt2);
    }

    @Test
    void testEquals_whenDifferentObjects_shouldReturnFalse() {
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(Operator.ADD);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(10);
        assertNotEquals(operatorOrInt1, operatorOrInt2);
    }

    @Test
    void testEquals_whenDifferentOperators_shouldReturnFalse() {
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(Operator.ADD);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(Operator.MUL);
        assertNotEquals(operatorOrInt1, operatorOrInt2);
    }

    @Test
    void testEquals_whenDifferentInt_shouldReturnFalse() {
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(11);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(279);
        assertNotEquals(operatorOrInt1, operatorOrInt2);
    }

    @Test
    void testHashCode_whenEqualObjects_shouldReturnSameHashCode() {
        Operator operator = Operator.ADD;
        Operator operator2 = Operator.ADD;
        OperatorOrInt operatorOrInt1 = new OperatorOrInt(operator);
        OperatorOrInt operatorOrInt2 = new OperatorOrInt(operator2);
        assertEquals(operatorOrInt1.hashCode(), operatorOrInt2.hashCode());
    }
}
