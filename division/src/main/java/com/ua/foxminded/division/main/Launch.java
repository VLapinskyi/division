package com.ua.foxminded.division.main;

import com.ua.foxminded.division.core.Calculator;
import com.ua.foxminded.division.core.DivisionData;
import com.ua.foxminded.division.core.Formatter;

public class Launch {

    public static void main(String[] args) {
	Calculator calculator = new Calculator();
	DivisionData divisionData = calculator.divide(30004, 21);
	Formatter formatter = new Formatter();
	System.out.print(formatter.format(divisionData));
    }

}
