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
public class Formula extends Data {

    public Formula(String content, String result) {
        super(content, result);
    }

    public Formula(String content) {
        super(content);
    }

    public void computeResult(Map<String, Cell> cellMap) {

        //ShuntingYard sy = new ShuntingYard("(3.5*4*12+3*2)-(5^4.2-1.5)");
        //ShuntingYard sy = new ShuntingYard("(5*4+3*2)-1");
        ShuntingYard sy = new ShuntingYard();
        try {
          Calculator calculator = new Calculator(cellMap,this.content);
          this.result = calculator.calculate();
        } catch (ExpressionException ex) {
            this.result = ex.getMessage();
        }


    }

}
