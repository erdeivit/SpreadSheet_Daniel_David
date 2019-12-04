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
public abstract class Data {
    
    private String content;
    
    public Data(String content){
        this.content = content;
    }
    
    public abstract void loadData();
    
}
