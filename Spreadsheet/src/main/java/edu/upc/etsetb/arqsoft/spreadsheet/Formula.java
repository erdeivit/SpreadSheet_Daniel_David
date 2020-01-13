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
    
    
    public void loadResult(Map<String, Cell> cellMap) {
        
        //ShuntingYard sy = new ShuntingYard("(3.5*4*12+3*2)-(5^4.2-1.5)");
        //ShuntingYard sy = new ShuntingYard("(5*4+3*2)-1");
        ShuntingYard sy = new ShuntingYard();
        int res = sy.evaluateExpresion(cellMap,this.content);
        
        switch (res) {
            case 0:
                sy.generatePostfix();
                this.result = String.valueOf(sy.evaluatePostfix());
                break;
            case -1:
                this.result = "RANGE NO FORM";
                break;
            case -2:
                this.result = "REFERENCE TO TEXT";
                break;

        }

    }
    
    
}
