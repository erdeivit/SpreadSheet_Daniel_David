package edu.upc.etsetb.arqsoft.spreadsheet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel León
 */
public abstract class FunctionFactory {
    
    public static Function getInstance(String functionType,String expression){
        if(functionType.equalsIgnoreCase("MIN")){
            return new Max(expression);
        }else if(functionType.equalsIgnoreCase("MAX")){
            return new Max(expression);
        }
        else if(functionType.equalsIgnoreCase("MAX")){
            return new Max(expression);
        }else if(functionType.equalsIgnoreCase("MAX")){
            return new Max(expression);
        }else{
            return null; 
        } 
    }

}
