/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

/**
 *
 * @author Daniel León
 */
public abstract class Function {
    
    protected String expression;

    public Function(String expression) {
        this.expression = expression;
    }
    
    
    public abstract void calculate(); 
    
}
