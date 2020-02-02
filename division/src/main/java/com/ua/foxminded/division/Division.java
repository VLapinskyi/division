package com.ua.foxminded.division;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;


public class Division {
    private static final String MINUS = "\u0332 ";
    private static final String VERTICAL_BAR = "|";
    private static final String WHITE_SPACE = " ";
    private static final String HYPHEN = "\u002D";
    private static final int ZERO = 0;
    private static final String BLANK = "";

    public void printResult(int divided, int divider) {
	ArrayList<String> resultLines = buildLines(divided, divider);
	for (String line : resultLines)
	    System.out.println(line);
    }

    private ArrayList<String> buildLines(int divided, int divider) {
	ArrayList<String> resultLines = new ArrayList<>();
	ArrayList<Integer> numbersOfColumn = takeNumbersOfColumn(divided, divider);
	ArrayList<String> symbolsBeforeNumbers = setSymbolsBeforeNumbers(numbersOfColumn);
	ArrayList<String> rightSide = setRightSide(divided, divider, numbersOfColumn);

	for (int i = 0; i < numbersOfColumn.size(); i++) {
	    if (i == 0) {
		resultLines.add(symbolsBeforeNumbers.get(0) + divided + rightSide.get(0));
	    }
	    else if (i == 1) {
		resultLines.add(symbolsBeforeNumbers.get(1) + numbersOfColumn.get(1) + rightSide.get(1));
		resultLines.add(setHyphensForTheFirstBlock(numbersOfColumn) + rightSide.get(2));
	    }
	    else {
		resultLines.add(symbolsBeforeNumbers.get(i) + numbersOfColumn.get(i));
	    }
	    if (i % 2 == 1 && i != 1 && i != numbersOfColumn.size() - 1) {
		if (i == 1)
		    resultLines.add(setHyphensForTheFirstBlock(numbersOfColumn));
		else
		    resultLines.add(setHyphensForOtherBlocks(resultLines));
	    }
	}
	
	return resultLines;
    }

    private ArrayList<Integer> takeNumbersOfColumn(int divided, int divider) {
	ArrayList<Integer> numbersOfColumn = new ArrayList<>();
	if (divided < divider) {
	    numbersOfColumn.add(divided);
	    numbersOfColumn.add(ZERO);
	    numbersOfColumn.add(divided);
	} else {
	    char[] dividedNumberArray = toArrayNumber(divided);
	    StringBuilder dividedElement = new StringBuilder();
	    for (int i = 0; i < dividedNumberArray.length; i++) {
		dividedElement.append(dividedNumberArray[i]);
		int result = parseInt(dividedElement.toString()) / divider;
		if (result != 0) {
		    numbersOfColumn.add(parseInt(dividedElement.toString()));
		    numbersOfColumn.add(result * divider);
		    int remain = numbersOfColumn.get(numbersOfColumn.size() - 2)
			    - numbersOfColumn.get(numbersOfColumn.size() - 1);
		    dividedElement.delete(0, dividedElement.length());
		    dividedElement.append(remain);
		}
		if (i == dividedNumberArray.length - 1) {
		    numbersOfColumn.add(parseInt(dividedElement.toString()));
		}
	    }
	}

	return numbersOfColumn;
    }

    private ArrayList<String> setSymbolsBeforeNumbers(ArrayList<Integer> numbersOfColumn) {
	ArrayList<String> symbolsBeforeNumbers = new ArrayList<>();
	
	for (int i = 0; i < numbersOfColumn.size(); i++) {

	    if (i == 0)
		symbolsBeforeNumbers.add(MINUS);

	    else if (i % 2 == 1)
		symbolsBeforeNumbers.add(setSymbolsForOddPositions(symbolsBeforeNumbers, numbersOfColumn, i));

	    else if (i % 2 == 0 && i != numbersOfColumn.size() - 1)
		symbolsBeforeNumbers.add(setSymbolsForEvenPositions(symbolsBeforeNumbers, numbersOfColumn, i));

	    else if (i == numbersOfColumn.size() - 1)
		symbolsBeforeNumbers.add(setSymbolsForTheLastPosition(symbolsBeforeNumbers, numbersOfColumn, i));

	}
	return symbolsBeforeNumbers;
    }

    private String setSymbolsForOddPositions(ArrayList<String> symbolsBeforeNumbers, ArrayList<Integer> numbersOfColumn,
	    int numberOfPosition) {
	StringBuilder symbols = new StringBuilder();
	symbols.append(symbolsBeforeNumbers.get(symbolsBeforeNumbers.size() - 1).replace(MINUS, BLANK));
	int quantity = takeNumberLength(numbersOfColumn.get(numberOfPosition - 1))
		- takeNumberLength(numbersOfColumn.get(numberOfPosition)) + 1; // +1 - instead of MINUS from the higher
									       // line
	for (int iterator = 0; iterator < quantity; iterator++)
	    symbols.append(WHITE_SPACE);
	return symbols.toString();
    }

    private String setSymbolsForEvenPositions(ArrayList<String> symbolsBeforeNumbers,
	    ArrayList<Integer> numbersOfColumn, int numberOfPosition) {
	StringBuilder symbols = new StringBuilder();
	if (takeNumberLength(numbersOfColumn.get(numberOfPosition))
		- takeNumberLength(numbersOfColumn.get(numberOfPosition - 2)) == 1)
	    symbols.append(symbolsBeforeNumbers.get(numberOfPosition - 2));
	else
	    symbols.append(WHITE_SPACE + symbolsBeforeNumbers.get(numberOfPosition - 2));
	return symbols.toString();
    }

    private String setSymbolsForTheLastPosition(ArrayList<String> symbolsBeforeNumbers,
	    ArrayList<Integer> numbersOfColumn, int numberOfPosition) {
	StringBuilder symbols = new StringBuilder();
	symbols.append(symbolsBeforeNumbers.get(numberOfPosition - 2).replace(MINUS, BLANK));
	int quantity = takeNumberLength(numbersOfColumn.get(numberOfPosition - 2))
		- takeNumberLength(numbersOfColumn.get(numberOfPosition)) + 1; // +1 - instead of MINUS
	for (int iterator = 0; iterator < quantity; iterator++)
	    symbols.append(WHITE_SPACE);
	return symbols.toString();
    }

    private String setHyphensForTheFirstBlock(ArrayList<Integer> numbersOfColumn) {
	String input = WHITE_SPACE + numbersOfColumn.get(0);
	return input.replaceAll("\\d", HYPHEN);
    }

    private String setHyphensForOtherBlocks(ArrayList<String> resultLines) {
	String input = resultLines.get(resultLines.size() - 2);
	String hyphens = input.replace(MINUS, WHITE_SPACE);
	hyphens = hyphens.replaceAll("\\d", HYPHEN);
	return hyphens;
    }

    private ArrayList<String> setRightSide(int divided, int divider, ArrayList<Integer> numbersOfColumn) {
	ArrayList<String> rightSide = new ArrayList<>();
	StringBuilder firstLine = new StringBuilder();
	StringBuilder secondLine = new StringBuilder();
	StringBuilder thirdLine = new StringBuilder();

	firstLine.append(VERTICAL_BAR + divider);
	rightSide.add(firstLine.toString());

	int quantity = takeNumberLength(divided) - takeNumberLength(numbersOfColumn.get(0));
	for (int i = 0; i < quantity; i++) {
	    secondLine.append(WHITE_SPACE);
	    thirdLine.append(WHITE_SPACE);
	}
	String resultOfDivision = Integer.toString(divided / divider);

	String hyphens = resultOfDivision.replaceAll("\\d", HYPHEN);
	secondLine.append(VERTICAL_BAR + hyphens);
	rightSide.add(secondLine.toString());

	thirdLine.append(VERTICAL_BAR + resultOfDivision);
	rightSide.add(thirdLine.toString());

	return rightSide;
    }

    private char[] toArrayNumber(int number) {
	String textNumber = Integer.toString(number);
	return textNumber.toCharArray();
    }

    private int takeNumberLength(int number) {
	return Integer.toString(number).length();
    }

}
