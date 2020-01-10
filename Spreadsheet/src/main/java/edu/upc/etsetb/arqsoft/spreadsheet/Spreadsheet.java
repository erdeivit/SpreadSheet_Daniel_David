/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;import java.util.TreeMap;

import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Daniel León
 */
public class Spreadsheet {

    private Map<String, Cell> cellMap ;
    private int total_rows;
    private int total_columns;

    public Spreadsheet()  {
        
        this.cellMap = new TreeMap<>();
        this.total_rows = 0;
        this.total_columns = 0;
       
    }
    
    public void loadFile(String filename)throws FileNotFoundException, IOException{
         
        Scanner file = new Scanner(new File(filename));   
        
        if (file != null){
            
            String actualLine;
            String[] actualLineArray = null;
            while(file.hasNextLine()){  
                actualLine = file.nextLine();
                total_rows++;
                actualLineArray = actualLine.split(";");

               for(int i = 0; i< actualLineArray.length ; i++){              
                   //TEMPORAL ASIGNATION OF ALL THE DATA AS TYPE TEXT.
                   //VALIDATE EACH TYPE OF DATA.              
                   if (actualLineArray[i].isEmpty()){
                       cellMap.put(toAlphabetic(i)+total_rows,new Cell());
                   }
                   else if (actualLineArray[i].charAt(0) == '='){
                       cellMap.put(toAlphabetic(i)+total_rows,new Cell(new Formula(actualLineArray[i])));
                   }
                   else
                   {
                       cellMap.put(toAlphabetic(i)+total_rows,new Cell(new Text(actualLineArray[i])));
                   }
               }
            }       
            total_columns = actualLineArray.length;
        }
        
        else{           
            System.out.println("The spreadsheet does not exist");       
        }
    }
    
    public void executeResults(){

        Iterator it = this.cellMap.keySet().iterator();
        while(it.hasNext()){
          String key = (String) it.next();
          if (this.cellMap.get(key).getData() != null){
            this.cellMap.get(key).getData().loadResult();
          }
        }
        
    }
    
    public void updateTUI(){
        
        //FALTA PONERLO BONITO
    
        Iterator it = this.cellMap.keySet().iterator();
        while(it.hasNext()){
          String key = (String) it.next();
          if (this.cellMap.get(key).getData() != null){
            System.out.println("Clave: " + key + " -> Valor: " + this.cellMap.get(key).getData().getResult());
          }
          else
          {
            System.out.println("[]");
          }

        }
    }

    public Map<String, Cell> getCellMap() {
        return cellMap;
    }

    public void setCellMap(Map<String, Cell> cellMap) {
        this.cellMap = cellMap;
    }
    
    
    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }
    
    public void saveSpreadSheet(String filename){
        
    }
    
    public static String toAlphabetic(int i) {
        if( i<0 ) {
            return "-"+toAlphabetic(-i-1);
        }

        int quot = i/26;
        int rem = i%26;
        char letter = (char)((int)'A' + rem);
        if( quot == 0 ) {
            return ""+letter;
        } else {
            return toAlphabetic(quot-1) + letter;
        }
}
    
    
    
}
