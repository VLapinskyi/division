package com.ua.foxminded.division.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormatterTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int THIRTY_TWO = 32;
    private static final int THIRTY_FOUR = 34;
    private static final int THIRTY_FIVE = 35;
    private static final int ONE_HUNDRED_EIGHTY_EIGHT = 188;
    private static final int SEVEN_HUNDRED_FIFTY_FOUR = 754;
    private static final String WHITE_SPACE = " ";
    private static final String DOUBLE_WHITE_SPACES = "  ";
    private static final String TRIPLE_WHITE_SPACES = "   ";
    private static final String MINUS = "\u0332 ";
    private static final String VERTICAL_BAR = "|";
    private static final String HYPHEN = "\u002D";
    private static final String DOUBLE_HYPHENS = "\u002D\u002D";
    private static final String TRIPLE_HYPHENS = "\u002D\u002D\u002D";

    Formatter formatter;
    Calculator calculator;

    @BeforeEach
    void initFormatter() {
	formatter = new Formatter();
	calculator = new Calculator();
    }

    @Test
    void shouldReturnCorrectStringWhenZeroDivideByOne() {
	StringBuilder expected = new StringBuilder();
	expected.append(MINUS + ZERO + VERTICAL_BAR + ONE);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + ZERO + VERTICAL_BAR + HYPHEN);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + HYPHEN + VERTICAL_BAR + ZERO);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + ZERO);
	assertEquals(expected.toString(), formatter.format(calculator.divide(ZERO, ONE)));
    }

    @Test
    void shouldReturnCorrectStringWhenSevenHundredFiftyFourDivideByFour() {
	StringBuilder expected = new StringBuilder();
	expected.append(MINUS + SEVEN_HUNDRED_FIFTY_FOUR + VERTICAL_BAR + FOUR);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + FOUR + DOUBLE_WHITE_SPACES + VERTICAL_BAR + TRIPLE_HYPHENS);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + HYPHEN + DOUBLE_WHITE_SPACES + VERTICAL_BAR + ONE_HUNDRED_EIGHTY_EIGHT);
	expected.append(System.lineSeparator());
	expected.append(MINUS + THIRTY_FIVE);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + THIRTY_TWO);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + DOUBLE_HYPHENS);
	expected.append(System.lineSeparator());
	expected.append(WHITE_SPACE + MINUS + THIRTY_FOUR);
	expected.append(System.lineSeparator());
	expected.append(DOUBLE_WHITE_SPACES + THIRTY_TWO);
	expected.append(System.lineSeparator());
	expected.append(DOUBLE_WHITE_SPACES + DOUBLE_HYPHENS);
	expected.append(System.lineSeparator());
	expected.append(TRIPLE_WHITE_SPACES + TWO);

	assertEquals(expected.toString(), formatter.format(calculator.divide(SEVEN_HUNDRED_FIFTY_FOUR, FOUR)));
    }

}
