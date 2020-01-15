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
public abstract class Data {
    
    protected String content;
    protected String result;

    public Data(String content, String result) {
        this.content = content;
        this.result = result;
    }
    
    public Data(String content){
        this.content = content;
    }
    
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public abstract void computeResult(Map<String, Cell> cellMap);
    
}
