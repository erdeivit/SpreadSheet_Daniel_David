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
import java.util.Scanner;

/**
 *
 * @author Daniel León
 */
public class Spreadsheet {

    private Cell[][] cells ;

    public Spreadsheet() throws FileNotFoundException, IOException {
        
        Scanner file = new Scanner(new File("spreadsheet.txt"));
        
        String actualLine = file.nextLine();
        String[] actualLineArray = actualLine.split(";");
        int rows = 1;
        int columns = actualLineArray.length;
        
        while(file.hasNextLine()){
            
           rows++;
           file.nextLine();
           
        }
        file.close();
        file = null;
        file = new Scanner(new File("spreadsheet.txt"));
        
        cells = new Cell[rows][columns];
        
        int i = 0;
        while(file.hasNextLine()){  
            
            actualLine = file.nextLine();
            actualLineArray = actualLine.split(";");
            
           for(int j = 0; j< actualLineArray.length ; j++){
               
               //TEMPORAL ASIGNATION OF ALL THE DATA AS TYPE TEXT.
               //VALIDATE EACH TYPE OF DATA.
               
               this.cells[i][j] = new Cell();
               this.cells[i][j].setData(new Text(actualLineArray[j]));
               
           }

           System.out.println(actualLine.split(";").length);
           i++;
        }

        System.out.println(this.cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    
    
    
}
