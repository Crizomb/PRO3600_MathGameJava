package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import base.Operator;

class OperatorTest {

    @Test
    void evaluate_shouldCalculateCorrectValueForAddition() {
        int result = Operator.ADD.Evaluate(4, 5);
        assertEquals(9, result);
    }

    @Test
    void evaluate_shouldCalculateCorrectValueForSubtraction() {
        // D'apres timothée, Operator.SUB(10, 3) doit retourner 3-10=-7, voir avec lui si  questions.
        int result = Operator.SUB.Evaluate(10, 3);
        assertEquals(-7, result);
    }

    @Test
    void evaluate_shouldCalculateCorrectValueForMultiplication() {
        int result = Operator.MUL.Evaluate(6, 7);
        assertEquals(42, result);
    }

    @Test
    void evaluate_shouldCalculateCorrectValueForDivision() {
        int result = Operator.DIV.Evaluate(15, 3);
        assertEquals(5, result);
    }

    @Test
    void values_shouldReturnAllOperatorValues() {
        Operator[] expectedValues = {Operator.ADD, Operator.SUB, Operator.MUL, Operator.DIV};
        assertArrayEquals(expectedValues, Operator.values());
    }

    @Test
    void valueOf_shouldReturnOperatorForValidString() {
        Operator operator = Operator.valueOf("ADD");
        assertEquals(Operator.ADD, operator);
    }

    @Test
    void valueOf_shouldThrowIllegalArgumentExceptionForInvalidString() {
        assertThrows(IllegalArgumentException.class, () -> Operator.valueOf("INVALID"));
    }
}
