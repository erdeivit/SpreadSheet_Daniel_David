/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Daniel León
 */
public class Calculator {

    private ShuntingYard sy;
    private String content, infix;
    private Map<String, Cell> cellMap;
    private Queue queue;

    public Calculator(Map<String, Cell> cellMap, String content) {
        this.sy = new ShuntingYard();
        this.content = content;
        this.cellMap = cellMap;
    }

    public String calculate() throws ExpressionException {

        processReferences();
        sy.setInfix(this.infix);
        sy.generatePostfix();
        this.queue = sy.getQueue();
        return String.valueOf(evaluatePostfix());

    }

    public void processReferences()
            throws ExpressionException {

        String expression = "";
        String c;
        //content = "=A1+33*1.5+A2:A55-BB112";

        int i = 1;
        while (i < this.content.length()) {
            c = String.valueOf(this.content.charAt(i));

            //I check if this is an operator
            if (c.matches("[+-/*^()]")) {
                expression = expression + c;
                i++;
            } //I check if what i have is a number
            else if (c.matches("-?\\d+(\\.\\d+)?")) {

                String num = "";
                while (c.matches("-?\\d+(\\.\\d+)?") || c.matches("\\.")) {
                    num = num + c;
                    i++;
                    //FOR THE LAST CASE, TO NOT HAVE A NULL POINTER
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
                
                expression = expression + cell_reference;
                cell_reference = "";
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

                        //THERE IS AN ERROR BECAUSE YOU SPECIFIED A RANGE OF CELLS ALONE
                        throw new ExpressionException("Found range" + expression + "that is not "
                                + "an argument of a function.");
                    } else {
                        //TODO: QUE PASA SI ESTA VACIA
                        //If is not a range is just a regular cell reference
                        //FROM HERE PROGRAM THE "SEARCH OF THE CELL reference"
                        if (this.cellMap.get(cell_reference).getData() == null) {
                            //If you point to a cell that has nothing, it considers the cell like a 0
                            expression = expression + 0;
                        } else {
                            if (this.cellMap.get(cell_reference).getData().getClass().getSimpleName().equals("NumericalValue")) {
                                expression = expression + this.cellMap.get(cell_reference).getData().getContent();
                            } else if (this.cellMap.get(cell_reference).getData().getClass().getSimpleName().equals("Text")) {
                                throw new ExpressionException("Found invalid text: " + cell_reference);
                            } else {

                                if (this.cellMap.get(cell_reference).getData().getResult() == null) {
                                    this.cellMap.get(cell_reference).getData().computeResult(this.cellMap);
                                    expression = expression + this.cellMap.get(cell_reference).getData().getResult();

                                } else {
                                    expression = expression + this.cellMap.get(cell_reference).getData().getResult();
                                }
                                //IN THIS CASE, THERE IS A FORMULA ON THE OTHER CELL REFERENCE, AND WE HAVE TO CHECK IF IT HAS A RESULT
                            }
                        }
                    }

                } //THIS MEANS I HAVE LETTERS+(
                else if (c.matches("\\(")) {
                    //PILA Y COLA. ALGO QUE TERMINE DE VER TODO EL BLOQUE QUE HAY DENTRO DE LA PRIMERA FUNCION
                    //BUCLE HASTA QUE TERMINE DE VER TODAS LAS FUNCIONES Y TENERLAS POR SEPARADO, PARA EJECUTARLAS DESPUÉS DE GOLPE

                    i++;
                    c = String.valueOf(this.content.charAt(i));
                    
                    boolean functionexists = false;
                    int t = 3;
                    ImplementedFunctions imp;
                    Function fe;
                    while (!functionexists && t < 9  ) {
                        for (ImplementedFunctions f : ImplementedFunctions.values()) {
                          try {
                              imp = ImplementedFunctions.valueOf(expression.substring(expression.length() - t));
                                if(imp == f){ 
                                    //UNA VEZ DETECTO LA EXPRESION HE DE ENVIARLE LO QUE VIENE
                                    fe = FunctionFactory.getInstance(String.valueOf(imp), this.content.substring(i));
                                    fe.calculate();
                                    functionexists = true;
                                }
                            } catch (IllegalArgumentException ex) {
                            }                       
                        }
                        t++;
                    }

                    //HAY QUE PLANTEARSE CON UN IF ELSE QUE PASA SI DESPUES VUELVE A HABER LETRA
                    //It may be a potential function   
                    expression = expression + cell_reference;

                }

            }
        }

        //System.out.println(expression);
        this.infix = expression;

    }

    public double evaluatePostfix() {

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
                double term_1 = Double.parseDouble(String.valueOf(stack.pop()));
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
                        result = term_1 / term_2;
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

}
