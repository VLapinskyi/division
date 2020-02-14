package com.ua.foxminded.division.core;

import java.util.ArrayList;

public class Formatter {
    private static final String MINUS = "\u0332 ";
    private static final String VERTICAL_BAR = "|";
    private static final String WHITE_SPACE = " ";
    private static final String HYPHEN = "\u002D";
    private static final String BLANK = "";

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

    private ArrayList<String> setDivisionText(int divided, int divider, ArrayList<Integer> remains,
	    int result) {
	ArrayList<String> divisionLines = new ArrayList<>();
	ArrayList<String> symbolsBeforeRemains = setSymbolsBeforeRemains(remains);
	ArrayList<String> rightSide = setRightSide(divided, divider, remains, result);

	for (int i = 0; i < remains.size(); i++) {
	    if (i == 0) {
		divisionLines.add(symbolsBeforeRemains.get(0) + divided + rightSide.get(0));
	    } else if (i == 1) {
		divisionLines.add(symbolsBeforeRemains.get(1) + remains.get(1) + rightSide.get(1));
		divisionLines.add(setHyphensForTheFirstBlock(remains) + rightSide.get(2));
	    } else {
		divisionLines.add(symbolsBeforeRemains.get(i) + remains.get(i));
	    }
	    if (i % 2 == 1 && i != 1 && i != remains.size() - 1) {
		if (i == 1) {
		    divisionLines.add(setHyphensForTheFirstBlock(remains));
		} else {
		    divisionLines.add(setHyphensForOtherBlocks(divisionLines));
		}
	    }
	}
	return divisionLines;
    }

    private ArrayList<String> setSymbolsBeforeRemains(ArrayList<Integer> remains) {
	ArrayList<String> symbolsBeforeRemains = new ArrayList<>();

	for (int i = 0; i < remains.size(); i++) {

	    if (i == 0) {
		symbolsBeforeRemains.add(MINUS);
	    } else if (i == remains.size() - 1) {
		symbolsBeforeRemains.add(setSymbolsForTheLastPosition(symbolsBeforeRemains, remains, i));
	    } else {
		symbolsBeforeRemains.add(setSymbolsForMiddlePositions(symbolsBeforeRemains, remains, i));
	    }
	}
	return symbolsBeforeRemains;
    }

    private String setSymbolsForMiddlePositions(ArrayList<String> symbolsBeforeRemains, ArrayList<Integer> remains,
	    int numberOfPosition) {
	if (numberOfPosition % 2 == 1) {
	StringBuilder symbols = new StringBuilder();
	    symbols.append(symbolsBeforeRemains.get(symbolsBeforeRemains.size() - 1).replace(MINUS, WHITE_SPACE));
	int quantity = takeNumberLength(remains.get(numberOfPosition - 1))
		    - takeNumberLength(remains.get(numberOfPosition));
	    for (int i = 0; i < quantity; i++) {
	    symbols.append(WHITE_SPACE);
	    }
	return symbols.toString();
	} else {
	    int resultOfPreviousRemains = remains.get(numberOfPosition - 2) - remains.get(numberOfPosition - 1);
	    StringBuilder symbols = new StringBuilder();
	    if (resultOfPreviousRemains == 0) {
		symbols.append(symbolsBeforeRemains.get(symbolsBeforeRemains.size() - 1));
		int quantity = takeNumberLength(remains.get(numberOfPosition - 1)) - 1;
		for (int i = 0; i < quantity; i++) {
		    symbols.append(WHITE_SPACE);
		}
		symbols.append(MINUS);
	    } else {
		int quantity = takeNumberLength(remains.get(numberOfPosition - 1))
			- takeNumberLength(resultOfPreviousRemains) - 1;
		if (quantity == -1) {
		    symbols.append(
			    replaceLastWhiteSpaceWithMinus(symbolsBeforeRemains.get(symbolsBeforeRemains.size() - 1)));
		}
		else {
		symbols.append(symbolsBeforeRemains.get(symbolsBeforeRemains.size() - 1));
		    for (int i = 1; i <= quantity; i++) {
		    symbols.append(WHITE_SPACE);
		    }
		symbols.append(MINUS);
		}
	    }
	    return symbols.toString();
	}
    }

    private String setSymbolsForTheLastPosition(ArrayList<String> symbolsBeforeRemains,
	    ArrayList<Integer> remains, int numberOfPosition) {
	StringBuilder symbols = new StringBuilder();
	symbols.append(symbolsBeforeRemains.get(numberOfPosition - 2).replace(MINUS, BLANK));
	int quantity = takeNumberLength(remains.get(numberOfPosition - 2))
		- takeNumberLength(remains.get(numberOfPosition)) + 1;
	for (int i = 0; i < quantity; i++) {
	    symbols.append(WHITE_SPACE);
	}
	return symbols.toString();
    }

    private String replaceLastWhiteSpaceWithMinus(String textForReplacement) {
	return textForReplacement.substring(0, textForReplacement.length() - 1) + MINUS;
    }

    private String setHyphensForTheFirstBlock(ArrayList<Integer> remains) {
	String input = WHITE_SPACE + remains.get(0);
	return input.replaceAll("\\d", HYPHEN);
    }

    private String setHyphensForOtherBlocks(ArrayList<String> divisionLines) {
	String input = divisionLines.get(divisionLines.size() - 2);
	String hyphens = input.replace(MINUS, WHITE_SPACE);
	hyphens = hyphens.replaceAll("\\d", HYPHEN);
	return hyphens;
    }

    private ArrayList<String> setRightSide(int divided, int divider, ArrayList<Integer> remains, int result) {
	ArrayList<String> rightSide = new ArrayList<>();
	StringBuilder firstLine = new StringBuilder();
	StringBuilder secondLine = new StringBuilder();
	StringBuilder thirdLine = new StringBuilder();

	firstLine.append(VERTICAL_BAR + divider);
	rightSide.add(firstLine.toString());

	int quantity = takeNumberLength(divided) - takeNumberLength(remains.get(0));
	for (int i = 0; i < quantity; i++) {
	    secondLine.append(WHITE_SPACE);
	    thirdLine.append(WHITE_SPACE);
	}
	String resultOfDivision = Integer.toString(result);

	String hyphens = resultOfDivision.replaceAll("\\d", HYPHEN);
	secondLine.append(VERTICAL_BAR + hyphens);
	rightSide.add(secondLine.toString());

	thirdLine.append(VERTICAL_BAR + resultOfDivision);
	rightSide.add(thirdLine.toString());

	return rightSide;
    }

    private int takeNumberLength(int number) {
	return Integer.toString(number).length();
    }
}
