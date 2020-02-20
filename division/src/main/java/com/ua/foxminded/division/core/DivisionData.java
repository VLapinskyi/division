package com.ua.foxminded.division.core;

import java.util.ArrayList;

public class DivisionData {
    private int divided;
    private int divider;
    private int result;
    private ArrayList<Integer> remains;

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

    @Override
    public boolean equals(Object obj) {
	if(obj == this)
	    return true;
	if(obj == null || obj.getClass() != this.getClass())
	    return false;
	
	DivisionData divisionData = (DivisionData) obj;
	return divided == divisionData.getDivided() && divider == divisionData.getDivider()
		&& result == divisionData.getResult() && (remains == divisionData.getRemains()
			|| (remains != null && remains.equals(divisionData.getRemains())));
    }

}
