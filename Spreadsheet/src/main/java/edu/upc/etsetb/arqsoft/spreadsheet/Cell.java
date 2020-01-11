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
public class Cell {
    
    private Data data;
    
    public Cell(){
        
    }
    
    public Cell(Data data){
        
        this.data = data;
    }

    public Data getData() {
        return data;
    }
    
    public void checkAndSetTypeofData(String content){
        
        if (content.isEmpty()){
            this.data = null;
        }
        else if (content.charAt(0) == '='){
            this.data = new Formula(content);
        }
        else if (content.matches("-?\\d+(\\.\\d+)?"))
        {
            this.data = new NumericalValue(content);
        }
        else
        {
            this.data = new Text(content);
        }

    }
    
}
