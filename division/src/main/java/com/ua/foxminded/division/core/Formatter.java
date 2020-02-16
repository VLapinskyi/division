package com.ua.foxminded.division.core;

import java.util.ArrayList;

public class Formatter {
    private static final String MINUS = "\u0332 ";
    private static final String VERTICAL_BAR = "|";
    private static final String WHITE_SPACE = " ";
    private static final String HYPHEN = "\u002D";

    public String format(DivisionData divisionData) {
	StringBuilder formattedDivision = new StringBuilder();
	ArrayList<String> divisionText = setDivisionText(
		divisionData.getDivided(),
		divisionData.getDivider(),
		divisionData.getRemains(),
		divisionData.getResult());

	for (int i = 0; i < divisionText.size(); i++) {
	    formattedDivision.append(divisionText.get(i));
	    if (i != divisionText.size() - 1) {
		formattedDivision.append(System.lineSeparator());
	    }
	}
	return formattedDivision.toString();
    }

    private ArrayList<String> setDivisionText(int divided,
	    				      int divider,
	    				      ArrayList<Integer> remains, 
	    				      int result) {
	ArrayList<String> divisionLines;

	divisionLines = getWhiteSpacesBeforeRemains(remains);
	divisionLines = getMinuses(divisionLines);
	divisionLines = getNumbersForLeftSide(divided, divisionLines, remains);
	divisionLines = getHyphensForLeftSide(divisionLines, remains);
	divisionLines = getRightSide(divided, divider, result, divisionLines, remains);

	return divisionLines;
    }

    private ArrayList<String> getWhiteSpacesBeforeRemains(ArrayList<Integer> remains) {
	ArrayList<String> lines = new ArrayList<>();
	StringBuilder whiteSpaces = new StringBuilder();
	
	for (int i = 0; i < remains.size(); i++) {
	    if (i == 0) {
		whiteSpaces.append(WHITE_SPACE);
		lines.add(whiteSpaces.toString());
	    } else {
		if (i % 2 == 1) {
		    int quantity = takeNumberLength(remains.get(i - 1)) - takeNumberLength(remains.get(i));
		    for (int iterator = 0; iterator < quantity; iterator++) {
			whiteSpaces.append(WHITE_SPACE);
		    }
		    lines.add(whiteSpaces.toString());
		} else {
		    int remainder = remains.get(i - 2) - remains.get(i - 1);
		    int quantity = takeNumberLength(remains.get(i - 1)) - takeNumberLength(remainder);

		    if (remainder == 0 && i != remains.size() - 1) {
			quantity++;
		    }

		    for (int iterator = 0; iterator < quantity; iterator++) {
			whiteSpaces.append(WHITE_SPACE);
		    }
		    
		    lines.add(whiteSpaces.toString());
		}
	    }
	}

	return lines;
    }

    private ArrayList<String> getMinuses(ArrayList<String> divisionLines) {
	ArrayList<String> lines = new ArrayList<>(divisionLines);

	for (int i = 0; i < lines.size() - 1; i++) {
	    if (i % 2 == 0) {
		StringBuilder textForLine = new StringBuilder();
		textForLine.append(lines.get(i).substring(0, lines.get(i).length() - 1));
		textForLine.append(MINUS);
		lines.set(i, textForLine.toString());
	    }
	}

	return lines;
    }

    private ArrayList<String> getNumbersForLeftSide(int divided,
	    					    ArrayList<String> divisionLines,
	    					    ArrayList<Integer> remains) {

	ArrayList<String> lines = new ArrayList<>(divisionLines);

	for (int i = 0; i < lines.size(); i++) {
	    StringBuilder textForLine = new StringBuilder();
	    if (i == 0) {
		textForLine.append(lines.get(i));
		textForLine.append(divided);
		lines.set(i, textForLine.toString());
	    } else {
		textForLine.append(lines.get(i));
		textForLine.append(remains.get(i));
		lines.set(i, textForLine.toString());
	    }
	}

	return lines;
    }

    private ArrayList<String> getHyphensForLeftSide(ArrayList<String> divisionLines, ArrayList<Integer> remains) {
	ArrayList<String> lines = new ArrayList<>(divisionLines);
	int countOfHyphens = 0;

	for (int i = 0; i < divisionLines.size(); i++) {
	    if (i % 2 == 1) {
		StringBuilder line = new StringBuilder();
		line.append(divisionLines.get(i - 1).substring(0, divisionLines.get(i - 1).indexOf(MINUS)));

		line.append(WHITE_SPACE + Integer.toString(remains.get(i - 1)).replaceAll("\\d", HYPHEN));
		lines.add(i + 1 + countOfHyphens, line.toString());
		countOfHyphens++;
	    }
	}

	return lines;
    }

    private ArrayList<String> getRightSide(int divided,
	    				   int divider,
	    				   int result,
	    				   ArrayList<String> divisionLines,
	    				   ArrayList<Integer> remains) {

	ArrayList<String> lines = new ArrayList<>(divisionLines);
	StringBuilder firstLine = new StringBuilder();
	StringBuilder secondLine = new StringBuilder();
	StringBuilder thirdLine = new StringBuilder();
	
	firstLine.append(lines.get(0) + VERTICAL_BAR + divider);
	lines.set(0, firstLine.toString());
	
	StringBuilder whiteSpaces = new StringBuilder();
	int quantity = takeNumberLength(divided) - takeNumberLength(remains.get(0));
	for (int i = 0; i < quantity; i++) {
	    whiteSpaces.append(WHITE_SPACE);
	}
	
	String resultOfDivision = Integer.toString(result);
	String hyphensForResult = resultOfDivision.replaceAll("\\d", HYPHEN);
	secondLine.append(lines.get(1) + whiteSpaces.toString() + VERTICAL_BAR + hyphensForResult);
	lines.set(1, secondLine.toString());

	thirdLine.append(lines.get(2) + whiteSpaces.toString() + VERTICAL_BAR + resultOfDivision);
	lines.set(2, thirdLine.toString());
	
	
	return lines;
    }

    private int takeNumberLength(int number) {
	return Integer.toString(number).length();
    }
}