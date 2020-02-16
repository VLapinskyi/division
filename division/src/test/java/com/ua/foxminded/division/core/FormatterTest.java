package com.ua.foxminded.division.core;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FormatterTest {

    private static final String MINUS = "\u0332 ";
    private static final String HYPHEN = "\u002D";
    private static final String DOUBLE_HYPHENS = "\u002D\u002D";
    private static final String TRIPLE_HYPHENS = "\u002D\u002D\u002D";
    private static final String VERTICAL_BAR = "|";
    private static final String WHITE_SPACE = " ";
    private static final String DOUBLE_WHITE_SPACES = "  ";
    private static final String TRIPLE_WHITE_SPACES = "   ";

    @Mock
    public DivisionData divisionData;

    public Formatter formatter;
    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	formatter = new Formatter();
    }

    @Test
    public void shouldReturnCorrectStringWhenZeroDivideByOne() {
	when(divisionData.getDivided()).thenReturn(0);
	when(divisionData.getDivider()).thenReturn(1);
	when(divisionData.getRemains()).thenReturn( new ArrayList<Integer>
		(Arrays.asList(0, 0, 0)));
	when(divisionData.getResult()).thenReturn(0);

	String expectedText = MINUS + 0 + VERTICAL_BAR + 1 + lineSeparator()
		+ WHITE_SPACE + 0 + VERTICAL_BAR + HYPHEN + lineSeparator()
		+ WHITE_SPACE + HYPHEN + VERTICAL_BAR + 0 + lineSeparator()
		+ WHITE_SPACE + 0;
	
	assertEquals(expectedText, formatter.format(divisionData));
    }

    @Test
    public void shouldReturnCorrectStringWhenSevenHundredFiftyFourDivideByFour() {
	when(divisionData.getDivided()).thenReturn(754);
	when(divisionData.getDivider()).thenReturn(4);
	when(divisionData.getRemains()).thenReturn( new ArrayList<Integer>
		(Arrays.asList(7, 4, 35, 32, 34, 32, 2)));
	when(divisionData.getResult()).thenReturn(188);
	
	String expectedText = MINUS + 754 + VERTICAL_BAR + 4 + lineSeparator()
		+ WHITE_SPACE + 4 + DOUBLE_WHITE_SPACES + VERTICAL_BAR + TRIPLE_HYPHENS + lineSeparator()
		+ WHITE_SPACE + HYPHEN + DOUBLE_WHITE_SPACES + VERTICAL_BAR + 188 + lineSeparator()
		+ MINUS + 35 + lineSeparator()
		+ WHITE_SPACE + 32 + lineSeparator()
		+ WHITE_SPACE + DOUBLE_HYPHENS + lineSeparator()
		+ WHITE_SPACE + MINUS + 34 + lineSeparator()
		+ DOUBLE_WHITE_SPACES + 32 + lineSeparator()
		+ DOUBLE_WHITE_SPACES + DOUBLE_HYPHENS + lineSeparator()
		+ TRIPLE_WHITE_SPACES + 2;

	assertEquals(expectedText, formatter.format(divisionData));
    }

}
