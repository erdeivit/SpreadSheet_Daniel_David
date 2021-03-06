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
public class Mean extends Function {

    public Mean(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) throws ExpressionException {        
        double sum = 0.0;
        int numberOfFactors = 0;
        Calculator calculator = new Calculator(cellMap, "");
        calculator.setIsAFunctionRange(true);
        for (int i = 0; i < this.factors.length; i++) {
            if (!this.factors[i].equals("")) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    sum = sum + Double.parseDouble(splittedFactorItem);
                    numberOfFactors++;
                }
            }
        }
        this.result = String.valueOf(sum / numberOfFactors);
        return this.result;
    }
}
