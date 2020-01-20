/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author Daniel León and David Hernández
 */
public class Sum extends Function {

    public Sum(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) throws ExpressionException {
        Calculator calculator = new Calculator(cellMap, "");
        double sum = 0.0;
        calculator.setIsAFunctionRange(true);
        for (int i = 0; i < this.factors.length; i++) {
            if (!this.factors[i].equals("")) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                //We split the factor (may come from a range)
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    sum = sum + Double.parseDouble(splittedFactorItem);
                }
            }
        }
        this.result = String.valueOf(sum);
        return this.result;
    }

}
