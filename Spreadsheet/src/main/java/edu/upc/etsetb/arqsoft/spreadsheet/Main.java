package edu.upc.etsetb.arqsoft.spreadsheet;

import static edu.upc.etsetb.arqsoft.spreadsheet.Spreadsheet.toAlphabetic;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Daniel León and David Hernández
 */
public class Main {

    public static String filename;
    public static Spreadsheet spreadsheet;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean decisionOK = false;
        System.out.println("TEXT ('text') or Graphic ('graphic') user interface? - Graphic not available for the moment-");
        String tClient = scanner.nextLine();
        if (!tClient.equalsIgnoreCase("text") && !tClient.equalsIgnoreCase("")) {
            System.out.println("Graphic user interface not available....leaving session");
            return;
        }
        spreadsheet = new Spreadsheet();
        //Initializate the spreadsheet (load the file)
        while(!decisionOK){
        System.out.println("Do you want to load or create new file? (LOAD/create)");
        String decision = scanner.nextLine();
        switch (decision) {
            case "load":
            case "LOAD":
            case "":
                System.out.println("Introduce the filename (include the file extension) - e.g spreadsheet.txt");
                filename = scanner.nextLine();
                if (spreadsheet.loadFile(filename)) {
                    decisionOK = true;
                }           
                break;
            case "CREATE":
            case "create":
                System.out.println("Introduce the filename.txt/columns/rows"
                        + " - e.g spreadsheet.txt/10/10");
                String command = scanner.nextLine();
                String[] parts = command.split("/", 3);
                createFile(parts[0], Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
                filename = parts[0];
                System.out.println("Created!");
                if (spreadsheet.loadFile(filename)) {
                    decisionOK = true;
                }
                break;
            default:
                System.out.println("Unknown decision: " + decision);
                break;
        }
        if (decisionOK) {
            spreadsheet.executeResults();
            spreadsheet.updateTUI();
            boolean end = false;
            String command;
            while (!end) {
                System.out.println("\n--------Choose one of the following options--------\n"
                        + "edit - Edits one cell \n"
                        + "save - Saves the file \n"
                        + "EXIT - Finishes the execution of the program");
                command = scanner.nextLine();
                end = processCommand(command);
            }
        }
        }
    }
    public static boolean processCommand(String command) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case "new":
                return false;
            case "edit":
            case "EDIT":
                System.out.println("\n Introduce the cell reference followed by the new content e.g: A1 TOTAL=5 \n");
                command = scanner.nextLine();
                String[] parts = command.split(" ", 2);
                Cell cell = spreadsheet.getCellMap().get(parts[0]);
                if (cell == null) {
                    System.out.println("The cell reference does not exist");
                    return false;
                } else if (parts[1] == null) {
                    System.out.println("You did not specified the new data");
                    return false;
                } else {
                    cell.checkAndSetTypeofData(parts[1]);
                    spreadsheet.executeResults();
                    spreadsheet.updateTUI();
                    return false;
                }
            case "save":
            case "SAVE":
                System.out.println("Do you want to overwrite the actual file? (Y/N)");
                String decision = scanner.nextLine();
                switch (decision) {
                    case "Y":
                    case "y":
                        spreadsheet.saveFile(filename);
                        System.out.println("Saved!");
                        break;
                    case "N":
                    case "n":
                        System.out.println("Introduce the filename (include the file extension) - e.g spreadsheet.txt");
                        String newFileName = scanner.nextLine();
                        spreadsheet.saveFile(newFileName);
                        System.out.println("Saved!");
                    default:
                        System.out.println("Unknown decision: " + command + " save operation aborted!");
                        break;
                }
                return false;
            case "exit":
            case "EXIT":
            case "":
                return true;
            default:
                System.out.println("Unknown command: " + command);
                return false;
        }
    }

    public static void createFile(String filename, int columns, int rows) throws FileNotFoundException, IOException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        String key, line, column;
        int row;
        for (int i = 0; i < rows; i++) {
            line = "";
            for (int j = 0; j < columns; j++) {
                column = toAlphabetic(j);
                row = i + 1;
                key = column + String.valueOf(row);
                if (j + 1 != columns) {
                    line = line + ";";
                }
            }
            writer.println(line);
        }
        writer.close();
    }
}
