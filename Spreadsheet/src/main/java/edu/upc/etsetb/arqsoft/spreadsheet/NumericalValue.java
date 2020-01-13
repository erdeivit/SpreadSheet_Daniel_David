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
public class NumericalValue extends Data{

    public NumericalValue(String content, String result) {
        super(content, result);
    }

    public NumericalValue(String content) {
        super(content);
    }

    @Override
    public void loadResult(Map<String, Cell> cellMap) {
        
        this.result = this.content;
    }
    
    
    
}
