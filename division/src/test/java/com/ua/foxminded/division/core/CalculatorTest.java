package com.ua.foxminded.division.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int SEVEN = 7;
    private static final int THIRTY_TWO = 32;
    private static final int THIRTY_FOUR = 34;
    private static final int THIRTY_FIVE = 35;
    private static final int ONE_HUNDRED_EIGHTY_EIGHT = 188;
    private static final int SEVEN_HUNDRED_FIFTY_FOUR = 754;
    private static final ArrayList<Integer> REMAINS_OF_ZERO_DIVIDE_BY_ONE = new ArrayList<>(
	    Arrays.asList(ZERO, ZERO, ZERO));
    private static final ArrayList<Integer> REMAINS_OF_SEVEN_HUNDRED_FIFTY_FOUR_DIVIDE_BY_FOUR = new ArrayList<>(
	    Arrays.asList(SEVEN, FOUR, THIRTY_FIVE, THIRTY_TWO, THIRTY_FOUR, THIRTY_TWO, TWO));

    Calculator calculator;

    @BeforeEach
    void initCalculator() {
	calculator = new Calculator();
    }

    @Test
    void shouldThrowArithmeticException() {
	assertThrows(ArithmeticException.class, () -> calculator.divide(ONE, ZERO));
    }

    @Test
    void shouldReturnCorrectDivisionDataWhenZeroDivideByOne() {
	DivisionData expected = new DivisionData();
	expected.setDivided(ZERO);
	expected.setDivider(ONE);
	expected.setRemains(REMAINS_OF_ZERO_DIVIDE_BY_ONE);
	expected.setResult(ZERO);
	assertEquals(expected, calculator.divide(ZERO, ONE));
    }

    @Test
    void shouldReturnCorrectDivisionDataWhenSevenHundredFiftyFourDivideByFour() {
	DivisionData expected = new DivisionData();
	expected.setDivided(SEVEN_HUNDRED_FIFTY_FOUR);
	expected.setDivider(FOUR);
	expected.setRemains(REMAINS_OF_SEVEN_HUNDRED_FIFTY_FOUR_DIVIDE_BY_FOUR);
	expected.setResult(ONE_HUNDRED_EIGHTY_EIGHT);
	assertEquals(expected, calculator.divide(SEVEN_HUNDRED_FIFTY_FOUR, FOUR));
    }

}
