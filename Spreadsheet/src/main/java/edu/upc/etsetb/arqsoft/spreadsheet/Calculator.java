/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import static edu.upc.etsetb.arqsoft.spreadsheet.Spreadsheet.toAlphabetic;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Daniel León
 */
public class Calculator {

    private final ShuntingYard sy;
    private String content, infix;
    private final Map<String, Cell> cellMap;
    private Queue queue;
    private boolean isAFunctionRange, noCalculateRange;

    public Calculator(Map<String, Cell> cellMap, String content) {
        this.sy = new ShuntingYard();
        this.content = content;
        this.cellMap = cellMap;
        this.isAFunctionRange = false;
        this.noCalculateRange = false;
    }

    public String calculate() throws ExpressionException {
        processReferences();
        if (!this.noCalculateRange) {
            sy.setInfix(this.infix);
            sy.generatePostfix();
            this.queue = sy.getQueue();
            return String.valueOf(evaluatePostfix());
        } else {
            this.noCalculateRange = false;
            return getContentCellsInaRange(this.infix);
        }
    }

    public void processReferences()
            throws ExpressionException {
        String expression = "";
        String c;
        int i = 0;
        if (String.valueOf(this.content.charAt(0)).matches("\\=")) {
            i = 1;
        }
        while (i < this.content.length()) {
            c = String.valueOf(this.content.charAt(i));
            //Check if this is an operator
            if (c.matches("[+-/*^()]")) {
                expression = expression + c;
                i++;
            } //Check if what I have is a number
            else if (c.matches("-?\\d+(\\.\\d+)?")) {
                String num = "";
                while (c.matches("-?\\d+(\\.\\d+)?") || c.matches("\\.")) {
                    num = num + c;
                    i++;
                    if (i != this.content.length()) {
                        c = String.valueOf(this.content.charAt(i));
                    } else {
                        c = "";
                    }
                }
                expression = expression + num;
            } //I will check if I have a cell reference or a cell range, or a function
            else if (c.matches("\\D")) {
                //I find one or several letters
                String cell_reference = "";
                while (c.matches("\\D") && !c.matches("\\(")) {
                    cell_reference = cell_reference + c;
                    i++;
                    if (i != this.content.length()) {
                        c = String.valueOf(this.content.charAt(i));
                    } else {
                        c = "";
                    }
                }
                //Now I may have a digit or a parenthesis, or a function
                if (c.matches("\\d")) {
                    //If now I have a digit I may have just a cell reference or a cell range
                    while (c.matches("\\d")) {
                        cell_reference = cell_reference + c;
                        i++;
                        if (i != this.content.length()) {
                            c = String.valueOf(this.content.charAt(i));
                        } else {
                            c = "";
                        }
                    }
                    //Now there are no more numbers, so
                    if (c.matches(":")) {
                        //Then this will be a potential range
                        while (c.matches("\\D")) {
                            //we have a letter
                            cell_reference = cell_reference + c;
                            i++;
                            if (i != this.content.length()) {
                                c = String.valueOf(this.content.charAt(i));
                            } else {
                                c = "";
                            }
                        }
                        //Then we will have a digit
                        while (c.matches("\\d")) {
                            cell_reference = cell_reference + c;
                            i++;
                            if (i != this.content.length()) {
                                c = String.valueOf(this.content.charAt(i));
                            } else {
                                c = "";
                            }
                        }
                        //FROM HERE PROGRAM THE "SEARCH OF THE CELL RANGEEEEE------"
                        //expression = expression + cell_reference;                 
                        if (!isAFunctionRange) {
                            throw new ExpressionException("Found range " + cell_reference + c + " that is not "
                                    + "an argument of a function.");
                        } else {
                            expression = expression + cell_reference;
                            this.noCalculateRange = true;
                        }
                    } else {
                        //FROM HERE PROGRAM THE "SEARCH OF THE CELL reference"
                        if (this.cellMap.get(cell_reference) == null) {
                            //This line detects if the given reference does not exist
                            expression = expression + 0;
                            throw new ExpressionException("One or more specified cells do not exist!");
                        } else if (this.cellMap.get(cell_reference).getData() == null) {
                            //If you point to a cell that has nothing, it considers the cell like a 0
                            expression = expression + 0;
                        } else {
                            switch (this.cellMap.get(cell_reference).getData().getClass().getSimpleName()) {
                                case "NumericalValue":
                                    expression = expression + this.cellMap.get(cell_reference).getData().getContent();
                                    break;
                                case "Text":
                                    throw new ExpressionException("Found invalid text: " + cell_reference);
                                default:
                                    if (this.cellMap.get(cell_reference).getData().getResult() == null) {
                                        this.cellMap.get(cell_reference).getData().computeResult(this.cellMap);
                                        try {
                                            Double.parseDouble(this.cellMap.get(cell_reference).getData().getResult());
                                            expression = expression + this.cellMap.get(cell_reference).getData().getResult();
                                        } catch (NumberFormatException ex) {
                                            throw new ExpressionException("A call to a reference that does not exist was found");
                                        }
                                    } else {
                                        expression = expression + this.cellMap.get(cell_reference).getData().getResult();
                                    }
                                    break;
                            }
                        }
                    }
                } //THIS MEANS I HAVE LETTERS + ( WHICH CAN ONLY MEAN THAT IS A FUNCTION
                else if (c.matches("\\(")) {
                    i++;
                    c = String.valueOf(this.content.charAt(i));
                    String restOfFunction = this.content.substring(i);
                    int number_open_parenthesis = 1;
                    String functionContent = "";
                    int t = 0;
                    while (t < number_open_parenthesis) {
                        while (!c.matches("\\)")) {
                            if (c.matches("\\(")) {
                                number_open_parenthesis++;
                            }
                            functionContent = functionContent + c;
                            i++;
                            c = String.valueOf(this.content.charAt(i));
                        }
                        t++;
                        if (t < number_open_parenthesis) {
                            functionContent = functionContent + c;
                            i++;
                            c = String.valueOf(this.content.charAt(i));
                        }
                    }
                    ImplementedFunctions imp;
                    Function fe;
                    for (ImplementedFunctions f : ImplementedFunctions.values()) {
                        try {
                            //IN THIS CASE CELL_REFERENCE CONTAINS THE FUNCTION, AS WE TREAT LIKE IT WAS A "AA" + PARENTESIS
                            imp = ImplementedFunctions.valueOf(cell_reference);
                            if (imp == f) {
                                //SEND TO EVALUATE THE FUNCTION AS "REFERENCES;REFERENCES";
                                fe = FunctionFactory.getInstance(String.valueOf(imp), functionContent);
                                expression = expression + fe.calculate(this.cellMap);
                                //CONTINUE FROM THE END OF THE FUNCTION
                                this.content = this.content.substring(++i);
                                i = 0;
                                break; //FUNCTION IS ALREADY IMPLEMENTED
                            }
                        } catch (IllegalArgumentException ex) {
                        }
                    }
                }
            }
        }
        this.infix = expression;
    }

    public double evaluatePostfix() throws ExpressionException {
        Stack stack = new Stack();
        int queue_size = this.queue.size();
        //Check if is a number
        for (int i = 0; i < queue_size; i++) {
            String c = (String) this.queue.peek();
            if (c.matches("-?\\d+(\\.\\d+)?")) {
                stack.add(c);
                this.queue.poll();
            } else {
                double term_2 = Double.parseDouble(String.valueOf(stack.pop()));
                double term_1;
                if (!stack.isEmpty()) {
                    term_1 = Double.parseDouble(String.valueOf(stack.pop()));
                } else {
                    term_1 = 0;
                }
                String operator = (String) this.queue.poll();
                double result = 0.0;
                switch (operator) {
                    case "+":
                        result = term_1 + term_2;
                        break;
                    case "-":
                        result = term_1 - term_2;
                        break;
                    case "*":
                        result = term_1 * term_2;
                        break;
                    case "/":
                        if (term_2 != 0) {
                            result = term_1 / term_2;
                        } else {
                            throw new ExpressionException("Found a division by zero!");
                        }
                        break;
                    case "^":
                        result = Math.pow(term_1, term_2);
                        break;
                }
                stack.add(result);
            }
        }
        return Double.parseDouble(String.valueOf(stack.pop()));
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsAFunctionRange() {
        return isAFunctionRange;
    }

    public void setIsAFunctionRange(boolean isAFunctionRange) {
        this.isAFunctionRange = isAFunctionRange;
    }

    public String getContentCellsInaRange(String range) throws ExpressionException {
        String begginingRange, endRange, column, key;
        int num_letters = 0, i = 0, initial_row, final_row, row;
        double initial_column = 0, final_column = 0;
        String valuesOfRange = "";
        begginingRange = range.split(":")[0];
        endRange = range.split(":")[1];
        String c = String.valueOf(begginingRange.charAt(num_letters));
        //FIRST PART OF THE RANGE
        while (c.matches("\\D")) {
            num_letters++;
            c = String.valueOf(begginingRange.charAt(num_letters));
        }
        initial_row = Integer.parseInt(begginingRange.substring(num_letters));
        begginingRange = begginingRange.substring(0, num_letters);
        num_letters = 0;
        while (i < begginingRange.length()) { //CALCULATE GIVEN A LETTER, WHICH COLUMN IS IN NUMBER
            initial_column = initial_column + (begginingRange.charAt(begginingRange.length() - 1 - i) - 'A' + 1) * (Math.pow(26, i++));
        }
        i = 0;
        //SECOND PART OF THE RANGE
        c = String.valueOf(endRange.charAt(num_letters));
        while (c.matches("\\D")) {
            num_letters++;
            c = String.valueOf(endRange.charAt(num_letters));
        }
        final_row = Integer.parseInt(endRange.substring(num_letters));
        endRange = endRange.substring(0, num_letters);
        num_letters = 0;
        while (i < endRange.length()) { //CALCULATE GIVEN A LETTER, WHICH COLUMN IS IN NUMBER
            final_column = final_column + (endRange.charAt(endRange.length() - 1 - i) - 'A' + 1) * (Math.pow(26, i++));
        }
        for (i = (int) initial_row; i <= final_row; i++) {
            for (int j = (int) initial_column; j <= final_column; j++) {
                column = toAlphabetic(j - 1);
                key = column + String.valueOf(i);
                if (this.cellMap.get(key) == null) {
                    valuesOfRange = valuesOfRange + 0 + ";";
                    throw new ExpressionException("A call to a reference that does not exist was found");
                } else if (this.cellMap.get(key).getData() != null) {
                    if (this.cellMap.get(key).getData().getClass().getSimpleName().equals("NumericalValue")) {
                        valuesOfRange = valuesOfRange + this.cellMap.get(key).getData().getContent() + ";";
                    } else if (this.cellMap.get(key).getData().getClass().getSimpleName().equals("Formula")) {
                        if (this.cellMap.get(key).getData().getResult() == null) {
                            this.cellMap.get(key).getData().computeResult(this.cellMap);
                            try {
                                Double.parseDouble(this.cellMap.get(key).getData().getResult());
                                valuesOfRange = valuesOfRange + this.cellMap.get(key).getData().getResult() + ";";
                            } catch (NumberFormatException ex) {
                                throw new ExpressionException("A call to a reference that does not exist was found");
                            }
                        } else {
                            valuesOfRange = valuesOfRange + this.cellMap.get(key).getData().getResult() + ";";
                        }
                    }
                }
            }
        }
        return valuesOfRange;
    }
}
