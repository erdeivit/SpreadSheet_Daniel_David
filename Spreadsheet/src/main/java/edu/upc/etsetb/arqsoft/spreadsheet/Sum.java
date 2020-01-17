/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author David Hernandez
 */
public class Sum extends Function {

    public Sum(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) {
        this.factors = expression.split("-");
        Calculator calculator = new Calculator(cellMap, this.factors[0]);

        this.factors = expression.split(",");
        double sum = 0.0;

        try {

            calculator.setIsAFunctionRange(true);
            for (int i = 0; i < this.factors.length; i++) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();  
                //We split the factor (may come from a range)
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    sum = sum + Double.parseDouble(splittedFactorItem);
                }
            }
        } catch (Exception ex) {
            
        }

        this.result = String.valueOf(sum);
        return this.result;
    }

}
