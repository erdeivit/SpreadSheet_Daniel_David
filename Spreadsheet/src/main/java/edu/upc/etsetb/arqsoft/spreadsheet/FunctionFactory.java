package edu.upc.etsetb.arqsoft.spreadsheet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel León and David Hernández
 */

/*
This factory is used to create a function of any of the four available types,
depending on the instance received. e.g if it receives a MIN, means that a
function of type Min has to be created.
*/
public abstract class FunctionFactory {
    
    public static Function getInstance(String functionType,String expression){
        if(functionType.equalsIgnoreCase("MIN")){
            return new Min(expression);
        }else if(functionType.equalsIgnoreCase("MAX")){
            return new Max(expression);
        }
        else if(functionType.equalsIgnoreCase("MEAN")){
            return new Mean(expression);
        }else if(functionType.equalsIgnoreCase("SUM")){
            return new Sum(expression);
        }else{
            return null; 
        } 
    }

}
