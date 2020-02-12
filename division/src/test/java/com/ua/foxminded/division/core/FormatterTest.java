package com.ua.foxminded.division.core;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormatterTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int FOUR = 4;
    private static final int SEVEN_HUNDRED_FIFTY_FOUR = 754;

    Formatter formatter;
    Calculator calculator;

    @BeforeEach
    void initFormatter() {
	formatter = new Formatter();
	calculator = new Calculator();
    }

    @Test
    void shouldReturnCorrectStringWhenZeroDivideByOne() {
	String expectedText = "\u0332 0|1" + lineSeparator()
		+ " 0|\u002D" + lineSeparator()
		+ " \u002D|0" + lineSeparator()
		+ " 0";
	assertEquals(expectedText, formatter.format(calculator.divide(ZERO, ONE)));
    }

    @Test
    void shouldReturnCorrectStringWhenSevenHundredFiftyFourDivideByFour() {
	String expectedText = "\u0332 754|4" + lineSeparator()
		+ " 4  |\u002D\u002D\u002D" + lineSeparator()
		+ " \u002D  |188" + lineSeparator()
		+ "\u0332 35" + lineSeparator()
		+ " 32" + lineSeparator()
		+ " \u002D\u002D" + lineSeparator()
		+ " \u0332 34" + lineSeparator()
		+ "  32" + lineSeparator()
		+ "  \u002D\u002D" + lineSeparator()
		+ "   2";
	assertEquals(expectedText, formatter.format(calculator.divide(SEVEN_HUNDRED_FIFTY_FOUR, FOUR)));
    }

}
