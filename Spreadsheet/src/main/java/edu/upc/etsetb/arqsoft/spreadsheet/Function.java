/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author Daniel León
 */
public abstract class Function {
    
    protected String expression;
    protected String[] factors;
    protected String result;

    public Function(String expression) {
        this.expression = expression;
        this.factors = null;
    }
    
    
    public abstract String calculate(Map<String, Cell> cellMap); 
    
}
