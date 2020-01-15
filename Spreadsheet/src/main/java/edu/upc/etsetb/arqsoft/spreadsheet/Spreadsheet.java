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
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.TreeMap;

import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel León
 */
public class Spreadsheet {

    private Map<String, Cell> cellMap;
    private int total_rows;
    private int total_columns;

    public Spreadsheet() {

        this.cellMap = new TreeMap<>();
        this.total_rows = 0;
        this.total_columns = 0;

    }

    public boolean loadFile(String filename) {

        Scanner file;
        try {
            file = new Scanner(new File(filename));
            String actualLine;
            String[] actualLineArray = null;

            if (file.hasNextLine()) {
                actualLine = file.nextLine();
                for (int i = 0; i < actualLine.length(); i++) {
                    if (String.valueOf(actualLine.charAt(i)).matches(",")) {
                        total_columns++;
                    }
                }
                total_columns++; //if there were 4 ; there are 5 columns
                while (file.hasNextLine()) {

                    total_rows++;
                    actualLineArray = actualLine.split(",");

                    for (int i = 0; i < actualLineArray.length; i++) {
                        Cell cell = new Cell();
                        cell.checkAndSetTypeofData(actualLineArray[i]);
                        cellMap.put(toAlphabetic(i) + total_rows, cell);
                    }
                    //Last columns empty at back part
                    for (int i = actualLineArray.length; i < total_columns; i++) {
                        Cell cell = new Cell();
                        cell.checkAndSetTypeofData("");
                        cellMap.put(toAlphabetic(i) + total_rows, cell);
                    }
                    actualLine = file.nextLine();
                }
                //total_columns = actualLineArray.length;

                return true;
            } else {
                System.out.println("The " + filename + " is empty!\n"
                        + "\nProgram will close...");
                return false;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("The " + filename + " does not exist!\n"
                    + "\nProgram will close...");
            return false;
        }

    }

    public void executeResults() {

        Iterator it = this.cellMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (this.cellMap.get(key).getData() != null) {
                this.cellMap.get(key).getData().computeResult(this.cellMap);
            }
        }

    }

    public void updateTUI() {

        String key, column;
        int row;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < this.total_rows; i++) {
            System.out.println();
            for (int j = 0; j < this.total_columns; j++) {

                column = toAlphabetic(j);
                row = i + 1;
                key = column + String.valueOf(row);

                if (this.cellMap.get(key).getData() != null) {
                    builder.delete(0, builder.length());
                    builder.append("[").append(this.cellMap.get(key).getData().getResult()).append("]");
                    System.out.print(builder.toString());

                } else {
                    System.out.print("[  ]");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void saveFile(String filename) throws FileNotFoundException, IOException {

        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        String key, line, column;
        int row;

        for (int i = 0; i < this.total_rows; i++) {

            line = "";

            for (int j = 0; j < this.total_columns; j++) {

                column = toAlphabetic(j);
                row = i + 1;
                key = column + String.valueOf(row);

                if (j + 1 != this.total_columns) {
                    if (this.cellMap.get(key).getData() != null) {

                        line = line + this.cellMap.get(key).getData().getContent() + ",";

                    } else {

                        line = line + ",";
                    }
                } else if (this.cellMap.get(key).getData() != null) {

                    line = line + this.cellMap.get(key).getData().getContent();

                }
            }
            writer.println(line);
        }

        writer.close();
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

    public static String toAlphabetic(int i) {
        if (i < 0) {
            return "-" + toAlphabetic(-i - 1);
        }

        int quot = i / 26;
        int rem = i % 26;
        char letter = (char) ((int) 'A' + rem);
        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot - 1) + letter;
        }
    }

}
