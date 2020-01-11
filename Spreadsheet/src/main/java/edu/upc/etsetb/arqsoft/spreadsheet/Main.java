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
    
    public static Spreadsheet spreadsheet;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Text ('text') or Graphic ('graphic') user interface? - Graphic not available for the moment-");
//        String tClient = scanner.nextLine();
//        if (!tClient.equalsIgnoreCase("text")) {
//            System.out.println("Graphic user interface not available....leaving session");
//            System.exit(-1);
//        }
        spreadsheet = new Spreadsheet();
        //Initializate the spreadsheet (load the file)
        
//        System.out.println("Introduce the filename (include the file extension) - e.g spreadsheet.txt");
//        String filename = scanner.nextLine();
        String filename = "spreadsheet.txt"; //TO DELETE
                
        if(spreadsheet.loadFile(filename)){
            
            spreadsheet.executeResults();
            spreadsheet.updateTUI();
                
            boolean end = false;
            String command;
            while (!end) {
                System.out.println("\n--------Choose one of the following options--------\n"
                        + "edit - Edits one cell \n"
                        + "save - Saves the file \n"
                        + "exit - Finishes the execution of the program");

                command = scanner.nextLine();
                end = processCommand(command);

            }
     
        }
    }
    
    public static boolean processCommand(String command) throws FileNotFoundException,IOException {   
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case "new":
                //si me dice una celda nueva, he de ver "cuantas columnas y filas nuevas" he de hacer
                //hacer un bucle para crearlas
                return false;
            case "edit":
                System.out.println("\n Introduce the cell reference followed by the new content e.g: A1 TOTAL=5 \n");
                command = scanner.nextLine();
                String[] parts = command.split(" ",2);
                Cell cell = spreadsheet.getCellMap().get(parts[0]);
                if (cell == null){
                    System.out.println("The cell reference does not exist");
                    return false;
                }
                else if (parts[1] == null){
                    System.out.println("You did not specified the new data");
                    return false;
                }
                else{
                    cell.checkAndSetTypeofData(parts[1]);
                    spreadsheet.executeResults();
                    spreadsheet.updateTUI();
                    return false;
                }

            case "save":
                String filename = "spreadsheet2.txt";
                spreadsheet.saveFile(filename);
                return false;
            case "exit":
               return true;
            default:
                System.out.println("Unknown command: " + command);
                return false;

        }

    }
    
}
