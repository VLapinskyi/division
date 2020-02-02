package com.ua.foxminded.division;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivisionTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int SIXTEEN = 16;
    private static final int TWENTY_ONE = 21;
    private static final int THIRTY_TWO = 32;
    private static final int THIRTY_FOUR = 34;
    private static final int THIRTY_FIVE = 35;
    private static final int FOURTY_TWO = 42;
    private static final int SIXTY = 60;
    private static final int EIGHTY_FOUR = 84;
    private static final int NINETY = 90;
    private static final int ONE_HUNDRED_SIXTY_EIGHT = 168;
    private static final int ONE_HUNDRED_EIGHTY_FOUR = 184;
    private static final int ONE_HUNDRED_EIGHTY_EIGHT = 188;
    private static final int SEVEN_HUNDRED_FIFTY_FOUR = 754;
    private static final int A_THOUSAND_FOUR_HUNDRED_TWENTY_EIGHT = 1428;
    private static final int THIRTY_THOUSAND_FOUR = 30004;
    
    private static final String WHITE_SPACE = " ";
    private static final String DOUBLE_WHITE_SPACES = "  ";
    private static final String TRIPLE_WHITE_SPACES = "   ";
    private static final String QUADRUPLE_WHITE_SPACES = "    ";
    private static final String MINUS = "\u0332 ";
    private static final String VERTICAL_BAR = "|";
    private static final String HYPHEN = "\u002D";
    private static final String DOUBLE_HYPHENS = "\u002D\u002D";
    private static final String TRIPLE_HYPHENS = "\u002D\u002D\u002D";
    private static final String QUADRUPLE_HYPHENS = "\u002D\u002D\u002D\u002D";

    private static final ArrayList<String> RESULT_OF_ZERO_DIVIDE_BY_ONE = new ArrayList<String>(Arrays.asList(
	    MINUS + ZERO + VERTICAL_BAR + ONE, WHITE_SPACE + ZERO + VERTICAL_BAR + HYPHEN,
	    WHITE_SPACE + HYPHEN + VERTICAL_BAR + ZERO, WHITE_SPACE + ZERO));
    private static final ArrayList<String> RESULT_OF_ONE_DIVIDE_BY_ONE = new ArrayList<>(
	    Arrays.asList(MINUS + ONE + VERTICAL_BAR + ONE,
		    WHITE_SPACE + ONE + VERTICAL_BAR + HYPHEN, WHITE_SPACE + HYPHEN + VERTICAL_BAR + ONE,
		    WHITE_SPACE + ZERO));
    private static final ArrayList<String> RESULT_OF_SEVEN_HUNDRED_FIFTY_FOUR_DIVIDE_BY_FOUR = new ArrayList<>(
	    Arrays.asList(
		    MINUS + SEVEN_HUNDRED_FIFTY_FOUR + VERTICAL_BAR + FOUR,
	    WHITE_SPACE + FOUR + DOUBLE_WHITE_SPACES + VERTICAL_BAR + TRIPLE_HYPHENS,
	    WHITE_SPACE + HYPHEN + DOUBLE_WHITE_SPACES + VERTICAL_BAR + ONE_HUNDRED_EIGHTY_EIGHT,
		    MINUS + THIRTY_FIVE, WHITE_SPACE + THIRTY_TWO, WHITE_SPACE + DOUBLE_HYPHENS,
		    WHITE_SPACE + MINUS + THIRTY_FOUR, DOUBLE_WHITE_SPACES + THIRTY_TWO,
		    DOUBLE_WHITE_SPACES + DOUBLE_HYPHENS, TRIPLE_WHITE_SPACES + TWO));
    private static final ArrayList<String> RESULT_OF_THIRTY_THOUSAND_FOUR_DIVIDE_BY_TWENTY_ONE = new ArrayList<>(
	    Arrays.asList(
		    MINUS + THIRTY_THOUSAND_FOUR + VERTICAL_BAR + TWENTY_ONE,
	    WHITE_SPACE + TWENTY_ONE + TRIPLE_WHITE_SPACES + VERTICAL_BAR + QUADRUPLE_HYPHENS,
	    WHITE_SPACE + DOUBLE_HYPHENS + TRIPLE_WHITE_SPACES + VERTICAL_BAR + A_THOUSAND_FOUR_HUNDRED_TWENTY_EIGHT,
		    WHITE_SPACE + MINUS + NINETY, DOUBLE_WHITE_SPACES + EIGHTY_FOUR,
		    DOUBLE_WHITE_SPACES + DOUBLE_HYPHENS, DOUBLE_WHITE_SPACES + MINUS + SIXTY,
		    TRIPLE_WHITE_SPACES + FOURTY_TWO, TRIPLE_WHITE_SPACES + DOUBLE_HYPHENS,
		    DOUBLE_WHITE_SPACES + MINUS + ONE_HUNDRED_EIGHTY_FOUR,
		    TRIPLE_WHITE_SPACES + ONE_HUNDRED_SIXTY_EIGHT,
		    TRIPLE_WHITE_SPACES + TRIPLE_HYPHENS, QUADRUPLE_WHITE_SPACES + SIXTEEN));


    Division division;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void init() {
	division = new Division();
    }

    @BeforeEach
    void setUpSteam() {
	outContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanSteam() {
	System.setOut(originalOut);
    }

    @Test
    void shouldThrowArithmeticException() {
	assertThrows(ArithmeticException.class, () -> division.printResult(ONE, ZERO));
    }

    @Test
    void shouldPrintCorrectResultWhenZeroDivideByOne() {
	StringBuilder expected = new StringBuilder();
	for (String line : RESULT_OF_ZERO_DIVIDE_BY_ONE)
	    expected.append(line + System.getProperty("line.separator"));
	division.printResult(ZERO, ONE);
	assertEquals(expected.toString(), outContent.toString());
    }
    
    @Test
    void shouldReturnCorrectResultWhenOneDivideOne() {
	StringBuilder expected = new StringBuilder();
	for (String line : RESULT_OF_ONE_DIVIDE_BY_ONE)
	    expected.append(line + System.getProperty("line.separator"));
	division.printResult(ONE, ONE);
	assertEquals(expected.toString(), outContent.toString());
    }

    @Test
    void shouldReturnCorrectResultWhenSevenHundredFiftyFourDivideByFour() {
	StringBuilder expected = new StringBuilder();
	for (String line : RESULT_OF_SEVEN_HUNDRED_FIFTY_FOUR_DIVIDE_BY_FOUR)
	    expected.append(line + System.getProperty("line.separator"));
	division.printResult(SEVEN_HUNDRED_FIFTY_FOUR, FOUR);
	assertEquals(expected.toString(), outContent.toString());
    }

    @Test
    void shouldReturnCorrectResultWhenThrirtyThousandFourDivideByTwentyOne() {
	StringBuilder expected = new StringBuilder();
	for (String line : RESULT_OF_THIRTY_THOUSAND_FOUR_DIVIDE_BY_TWENTY_ONE)
	    expected.append(line + System.getProperty("line.separator"));
	division.printResult(THIRTY_THOUSAND_FOUR, TWENTY_ONE);
	assertEquals(expected.toString(), outContent.toString());
    }

}
