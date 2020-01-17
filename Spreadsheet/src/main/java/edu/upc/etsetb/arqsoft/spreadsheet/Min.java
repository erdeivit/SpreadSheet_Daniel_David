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
public class Min extends Function{

    public Min(String expression) {
        super(expression);
    }
    
    @Override
    public String calculate(Map<String, Cell> cellMap) {
        this.factors = expression.split(";");
        for (int i = 0; i < factors.length; i++) {
            try {
                Calculator calculator = new Calculator(cellMap, this.factors[i]);
                this.factors[i] = calculator.calculate();
                if (i == 0) //TODO ARREGLAR ESTO
                {
                  this.result = this.factors[i];
                }
                if (Double.parseDouble(this.result) > Double.parseDouble(this.factors[i])) {
                    this.result = this.factors[i];
                }
            } catch (Exception ex) {
                this.result = ex.getMessage();
            }
        }
        return this.result;
    }
    
    
    
}
