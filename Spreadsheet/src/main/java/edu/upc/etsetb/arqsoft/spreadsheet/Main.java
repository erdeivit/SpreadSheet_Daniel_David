/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Daniel León
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Text ('text') or Graphic ('graphic') user interface? - Graphic not available for the moment-");
        String tClient = scanner.nextLine();
        if (!tClient.equalsIgnoreCase("text")) {
            System.out.println("Graphic user interface not available....leaving session");
            System.exit(-1);
        }
        
        Spreadsheet spreadsheet = new Spreadsheet();
        //Initializate the spreadsheet (load the file)
        System.out.println("Introduce the filename (include the file extension) - e.g spreadsheet.txt");
        String filename = scanner.nextLine();
        spreadsheet.loadFile(filename);
        spreadsheet.executeResults();
        spreadsheet.updateTUI();
        
        

    }
    
    public void loadTXTfile() throws FileNotFoundException, IOException{
        

    }
    
}
