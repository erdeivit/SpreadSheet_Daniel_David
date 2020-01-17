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
public class Mean extends Function {

    public Mean(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) {
        this.factors = expression.split(";");
        double sum = 0.0;
        for (int i = 0; i < this.factors.length; i++) {
            try {
                Calculator calculator = new Calculator(cellMap, this.factors[i]);
                this.factors[i] = calculator.calculate();
                sum = sum + Double.parseDouble(this.factors[i]);
            } catch (Exception ex) {
                this.result = ex.getMessage();
            }
        }
        this.result = String.valueOf(sum / this.factors.length);
        return this.result;
    }

}
