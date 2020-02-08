package com.ua.foxminded.division.core;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;

class Calculator {
    
    private Calculator() {
	throw new IllegalStateException("Utility class");
    }

    public static final int ZERO = 0;

    protected static int setResultOfDivision(int divided, int divider) {
	return divided / divider;
    }
    
    protected static ArrayList<Integer> setRemainsOfDivision(int divided, int divider) {
	ArrayList<Integer> remains = new ArrayList<>();
	if (divided < divider) {
	    remains.add(divided);
	    remains.add(ZERO);
	    remains.add(divided);
	} else {
	    char[] dividedNumberArray = toArrayNumber(divided);
	    StringBuilder dividedElement = new StringBuilder();
	    for (int i = 0; i < dividedNumberArray.length; i++) {
		dividedElement.append(dividedNumberArray[i]);
		int result = parseInt(dividedElement.toString()) / divider;
		if (result != 0) {
		    remains.add(parseInt(dividedElement.toString()));
		    remains.add(result * divider);
		    int remain = remains.get(remains.size() - 2) - remains.get(remains.size() - 1);
		    dividedElement.delete(0, dividedElement.length());
		    dividedElement.append(remain);
		}
		if (i == dividedNumberArray.length - 1) {
		    remains.add(parseInt(dividedElement.toString()));
		}
	    }
	}

	return remains;
    }

    private static char[] toArrayNumber(int number) {
	String textNumber = Integer.toString(number);
	return textNumber.toCharArray();
    }
}
