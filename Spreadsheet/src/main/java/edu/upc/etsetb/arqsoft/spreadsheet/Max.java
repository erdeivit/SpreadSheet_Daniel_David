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
public class Max extends Function{

    public Max(String expression) {
        super(expression);
    }
    
    @Override
    public void calculate() {
        System.out.println(expression);
    }
    
    
    
}
