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
    public String calculate(Map<String, Cell> cellMap) throws ExpressionException {
        ImplementedFunctions imp;
        this.factors = expression.split(",");
        int k = 1;
        for (int j = 0; j < this.factors.length; j++) {
            for (ImplementedFunctions f : ImplementedFunctions.values()) {
                //THERE IS A FUNCTION INSIDE A FUNCTION
                if (this.factors[j].contains(String.valueOf(f))) {
                    while ((j < this.factors.length - 1) && !this.factors[j + k].contains(")")) {
                        this.factors[j] = this.factors[j] + "," + this.factors[j + k];
                        this.factors[j + k] = "";
                        k++;
                    }
                   //IF THE FUNCTIONS JUST HAVE ONE PARAMETER DON'T DO ANYTHING
                    if (!this.factors[j].contains("(") || !this.factors[j].contains(")"))
                    {
                        this.factors[j] = this.factors[j] + "," + this.factors[j + k];
                        this.factors[j + k] = "";
                    }
                    
                    k = 1;
                }
            }
        }
        Calculator calculator = new Calculator(cellMap, "");
        calculator.setIsAFunctionRange(true);
        for (int i = 0; i < factors.length; i++) {
            if (!this.factors[i].equals("")) {
                calculator.setContent(this.factors[i]);
                this.factors[i] = calculator.calculate();
                this.splittedFactor = this.factors[i].split(";");
                for (String splittedFactorItem : this.splittedFactor) {
                    if (i == 0) {
                        this.result = splittedFactorItem;
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
