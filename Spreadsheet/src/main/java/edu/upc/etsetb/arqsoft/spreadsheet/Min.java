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
public class Min extends Function {

    public Min(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) throws ExpressionException {
        Calculator calculator = new
        Calculator(cellMap, "");
        calculator.setIsAFunctionRange(true);
        int k = 0;
        for (int i = 0; i < factors.length; i++) {
            if (!this.factors[i].equals("")) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    if (k == 0) {
                        this.result = splittedFactorItem;
                        k = 1;
                    }
                    if (Double.parseDouble(this.result) > Double.parseDouble(splittedFactorItem)) {
                        this.result = splittedFactorItem;
                    }
                }
            }
        }
        return this.result;
    }
}
