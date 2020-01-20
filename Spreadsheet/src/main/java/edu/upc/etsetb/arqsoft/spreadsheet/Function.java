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
public abstract class Function {
    protected String[] factors = null;
    protected String result;
    protected String[] splittedFactor = null;

    /*
    The abstract class function is used to manage the different ways of 
    calculating the implemented functions in the program. Its constructor has
    the capability of splitting all the factors and store them in the 
    String[] factors variable of the Function.
    */
    public Function(String expression) {
        
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
                    if (!this.factors[j].contains("(") || !this.factors[j].contains(")")) {
                        this.factors[j] = this.factors[j] + "," + this.factors[j + k];
                        this.factors[j + k] = "";
                    }
                    k = 1;
                }
            }
        }
    }
    public abstract String calculate(Map<String, Cell> cellMap) throws ExpressionException; 
    
}
