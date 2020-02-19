package com.ua.foxminded.division.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FormatterTest {

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

	String expectedText = "̲ 0|1\r\n 0|-\r\n -|0\r\n 0";
	
	assertEquals(expectedText, formatter.format(divisionData));
    }

    @Test
    public void shouldReturnCorrectStringWhenSevenHundredFiftyFourDivideByFour() {
	when(divisionData.getDivided()).thenReturn(754);
	when(divisionData.getDivider()).thenReturn(4);
	when(divisionData.getRemains()).thenReturn( new ArrayList<Integer>
		(Arrays.asList(7, 4, 35, 32, 34, 32, 2)));
	when(divisionData.getResult()).thenReturn(188);
	
	String expectedText = "̲ 754|4\r\n 4  |---\r\n -  |188\r\n̲ 35\r\n 32\r\n --\r\n ̲ 34\r\n  32\r\n  --\r\n   2";

	assertEquals(expectedText, formatter.format(divisionData));
    }

}
