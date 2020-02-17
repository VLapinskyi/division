package com.ua.foxminded.division.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    public Calculator calculator;

    @BeforeEach
    public void initCalculator() {
	calculator = new Calculator();
    }

    @Test
    public void shouldThrowArithmeticException() {
	assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }

    @Test
    public void shouldReturnCorrectDivisionDataWhenZeroDivideByOne() {
	DivisionData expected = new DivisionData();
	expected.setDivided(0);
	expected.setDivider(1);
	expected.setRemains(new ArrayList<>(Arrays.asList(0, 0, 0)));
	expected.setResult(0);
	assertEquals(expected, calculator.divide(0, 1));
    }

    @Test
    public void shouldReturnCorrectDivisionDataWhenSevenHundredFiftyFourDivideByFour() {
	DivisionData expected = new DivisionData();
	expected.setDivided(754);
	expected.setDivider(4);
	expected.setRemains(new ArrayList<>(Arrays.asList(7, 4, 35, 32, 34, 32, 2)));
	expected.setResult(188);
	assertEquals(expected, calculator.divide(754, 4));
    }

}
