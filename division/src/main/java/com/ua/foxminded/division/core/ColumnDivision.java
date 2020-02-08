package com.ua.foxminded.division.core;

import java.util.ArrayList;

public class ColumnDivision {
    private int divided;
    private int divider;
    private int result;
    private ArrayList<Integer> remains;
    private ArrayList<String> divisionForPrinting;

    public ColumnDivision(int divided, int divider) {
	this.divided = divided;
	this.divider = divider;
	remains = new ArrayList<>();
	divisionForPrinting = new ArrayList<>();
	calculateResultOfDivision();
	calculateRemainsOfDivision();
	formatDivision();
    }


    protected int getDivided() {
	return divided;
    }

    protected void setDivided(int divided) {
	this.divided = divided;
    }


    protected int getDivider() {
	return divider;
    }

    protected void setDivider(int divider) {
	this.divider = divider;
    }


    protected int getResult() {
	return result;
    }

    protected void setResult(int result) {
	this.result = result;
    }


    protected ArrayList<Integer> getRemains() {
	return remains;
    }

    protected void setRemains(ArrayList<Integer> remains) {
	this.remains = remains;
    }

    protected ArrayList<String> getDivisionForPrinting() {
	return divisionForPrinting;
    }

    protected void setDivisionForPrinting(ArrayList<String> divisionForPrinting) {
	this.divisionForPrinting = divisionForPrinting;
    }

    private void calculateResultOfDivision() {
	result = Calculator.setResultOfDivision(divided, divider);
    }

    private void calculateRemainsOfDivision() {
	remains.addAll(Calculator.setRemainsOfDivision(divided, divider));
    }

    private void formatDivision() {
	divisionForPrinting.addAll(FormatterResult.setDivisionText(divided, divider, remains, result));
    }

    public void printResult() {
	StringBuilder resultForPrinting = new StringBuilder();
	for (String element : divisionForPrinting)
	    resultForPrinting.append(element + System.lineSeparator());
	System.out.print(resultForPrinting.toString());

    }
}
