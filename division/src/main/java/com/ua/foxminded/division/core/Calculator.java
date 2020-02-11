package com.ua.foxminded.division.core;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;

public class Calculator {
    

    public static final int ZERO = 0;
    
    public DivisionData divide(int divided, int divider) {
	DivisionData divisionData = new DivisionData();
	divisionData.setDivided(divided);
	divisionData.setDivider(divider);
	divisionData.setResult(divided / divider);
	divisionData.setRemains(setRemainsOfDivision(divided, divider));
	return divisionData;
    }

    private ArrayList<Integer> setRemainsOfDivision(int divided, int divider) {
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

    private char[] toArrayNumber(int number) {
	String textNumber = Integer.toString(number);
	return textNumber.toCharArray();
    }

}
