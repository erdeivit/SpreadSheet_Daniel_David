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
public class Formula extends Data {

    public Formula(String content, String result) {
        super(content, result);
    }

    public Formula(String content) {
        super(content);
    }
    
    
    public void loadResult() {
        
        //AQUI ES DONDE HAY QUE LLAMAR AL SHUTTING YARD
        this.result = "EJECUTAR FORMULA";
    }
    
    
}
