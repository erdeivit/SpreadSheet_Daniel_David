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
public class Min extends Function {

    public Min(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) {
        this.factors = expression.split(",");

        Calculator calculator = new Calculator(cellMap, this.factors[0]);
        calculator.setIsAFunctionRange(true);
        try {
            for (int i = 0; i < factors.length; i++) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    if (i == 0){
                        this.result = splittedFactorItem;
                    }
                    if (Double.parseDouble(this.result) > Double.parseDouble(splittedFactorItem)) {
                        this.result = splittedFactorItem;
                    }
                }

            }
        } catch (Exception ex) {
            this.result = ex.getMessage();
        }
        return this.result;
    }

}
