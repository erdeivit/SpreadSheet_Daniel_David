/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Daniel León
 */
public class ShuntingYard {

    private String infix;
    Stack stack;
    Queue queue;

    public ShuntingYard(String infix) {
        this.infix = infix;
        //this.stack = new Stack();
        this.stack = new Stack();
        this.queue = new LinkedList();
    }

    public ShuntingYard() {
        this.infix = "";
        //this.stack = new Stack();
        this.stack = new Stack();
        this.queue = new LinkedList();

    }

    public void generatePostfix() {
        String c;
        int i = 0;
        //ShuntingYard sy = new ShuntingYard("(5*4+3*2)-1");
        while (i < this.infix.length()) {
            String num = "";
            c = String.valueOf(this.infix.charAt(i));
            //int k = i;
            while (c.matches("-?\\d+(\\.\\d+)?") || c.matches("\\.")) {
                num = num + c;
                i++;
                if (i != this.infix.length()) {
                    c = String.valueOf(this.infix.charAt(i));
                    //i = k-1;
                } else {
                    c = "";
                }
            }

            if (num.matches("-?\\d+(\\.\\d+)?")) {
                this.queue.add(num);
            } else {
                if (this.stack.isEmpty()) {
                    this.stack.add(c);
                } else {
                    String top = (String) this.stack.peek();
                    //this means has less priority the new symbol
                    if (pemdas(c, top) <= 0 && !"(".equals(top)) {
                        this.queue.add(this.stack.pop());
                        this.stack.add(c);
                    } else {
                        if (")".equals(c)) {
                            Iterator itr = this.stack.iterator();
                            while (itr.hasNext()) {
                                this.queue.add(this.stack.pop());
                                if (this.stack.size() != 0) {
                                    if ("(".equals(this.stack.peek())) {
                                        this.stack.pop();
                                    }
                                }

                            }

                        } else {
                            this.stack.add(c);
                        }
                    }
                }
                i++;
            }
        }

        if (!this.stack.isEmpty()) {
            this.queue.add(this.stack.pop());
        }

    }

    /*public double evaluatePostfix() {

        this.stack = new Stack();
        int queue_size = this.queue.size();
        //Check if is a number
        for (int i = 0; i < queue_size; i++) {

            String c = (String) this.queue.peek();
            if (c.matches("-?\\d+(\\.\\d+)?")) {
                this.stack.add(c);
                this.queue.poll();
            } else {

                double term_2 = Double.parseDouble(String.valueOf(this.stack.pop()));
                double term_1 = Double.parseDouble(String.valueOf(this.stack.pop()));
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

                this.stack.add(result);

            }
        }

        return Double.parseDouble(String.valueOf(this.stack.pop()));
    }*/

    /*public void processReferences(Map<String, Cell> cellMap, String content)
            throws ExpressionException {

        String expression = "";
        String c;
        //content = "=A1+33*1.5+A2:A55-BB112";

        int i = 1;
        while (i < content.length()) {
            c = String.valueOf(content.charAt(i));

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
                    if (i != content.length()) {
                        c = String.valueOf(content.charAt(i));
                    } else {
                        c = "";
                    }
                }

                expression = expression + num;
            } //I will check if I have a cell reference or a cell range, or a function
            else if (c.matches("\\D")) {
                //I find one or several letters
                String cell_reference = "";

                while (c.matches("\\D")) {
                    cell_reference = cell_reference + c;
                    i++;
                    if (i != content.length()) {
                        c = String.valueOf(content.charAt(i));

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
                        if (i != content.length()) {
                            c = String.valueOf(content.charAt(i));
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
                            if (i != content.length()) {
                                c = String.valueOf(content.charAt(i));
                            } else {
                                c = "";
                            }
                        }
                        //Then we will have a digit
                        while (c.matches("\\d")) {
                            cell_reference = cell_reference + c;
                            i++;
                            if (i != content.length()) {
                                c = String.valueOf(content.charAt(i));
                            } else {
                                c = "";
                            }
                        }
                        //FROM HERE PROGRAM THE "SEARCH OF THE CELL RANGEEEEE------"
                        //expression = expression + cell_reference;

                        //THERE IS AN ERROR BECAUSE YOU SPECIFIED A RANGE OF CELLS ALONE
                        throw new ExpressionException("Found range" + c + "that is not "
                                + "an argument of a function.");
                    } else {
                        //TODO: QUE PASA SI ESTA VACIA
                        //If is not a range is just a regular cell reference
                        //FROM HERE PROGRAM THE "SEARCH OF THE CELL reference"
                        if (cellMap.get(cell_reference).getData() == null) {
                            //If you point to a cell that has nothing, it considers the cell like a 0
                            expression = expression + 0;
                        } else {
                            if (cellMap.get(cell_reference).getData().getClass().getSimpleName().equals("NumericalValue")) {
                                expression = expression + cellMap.get(cell_reference).getData().getContent();
                            } else if (cellMap.get(cell_reference).getData().getClass().getSimpleName().equals("Text")) {
                                throw new ExpressionException("Found invalid text: " + cell_reference );
                            } else {

                                if (cellMap.get(cell_reference).getData().getResult() == null) {
                                    cellMap.get(cell_reference).getData().computeResult(cellMap);
                                    expression = expression + cellMap.get(cell_reference).getData().getResult();

                                } else {
                                    expression = expression + cellMap.get(cell_reference).getData().getResult();
                                }
                                //IN THIS CASE, THERE IS A FORMULA ON THE OTHER CELL REFERENCE, AND WE HAVE TO CHECK IF IT HAS A RESULT
                            }
                        }
                    }

                } //THIS MEANS I HAVE LETTERS+(
                else if (c.matches("(")) {
                    //PILA Y COLA. ALGO QUE TERMINE DE VER TODO EL BLOQUE QUE HAY DENTRO DE LA PRIMERA FUNCION
                    //BUCLE HASTA QUE TERMINE DE VER TODAS LAS FUNCIONES Y TENERLAS POR SEPARADO, PARA EJECUTARLAS DESPUÉS DE GOLPE

                    String function_expression = "";
                    i++;
                    c = String.valueOf(content.charAt(i));
                    if (expression.substring(expression.length() - 3).equals("MIN")) {
                        while (!c.matches(")")) {
                            function_expression = function_expression + c;
                            i++;
                            if (i != content.length()) {
                                c = String.valueOf(content.charAt(i));
                            } else {
                                c = "";
                            }
                        }
                    } else if (expression.substring(expression.length() - 3).equals("MAX")) {

                    } else if (expression.substring(expression.length() - 4).equals("SUMA")) {

                    } else if (expression.substring(expression.length() - 8).equals("PROMEDIO")) {

                    }

                    //HAY QUE PLANTEARSE CON UN IF ELSE QUE PASA SI DESPUES VUELVE A HABER LETRA
                    //It may be a potential function   
                    expression = expression + cell_reference;

                }

            }
        }

        //System.out.println(expression);
        this.infix = expression;

    }*/

    public int pemdas(String op1, String op2) {
        int op1_preference = 0;
        int op2_preference = 0;
        switch (op1) {
            case "+":
            case "-":
                op1_preference = 1;
                break;
            case "*":
            case "/":
                op1_preference = 2;
                break;
            case "^":
                op1_preference = 3;
            case "(":
            case ")":
                op1_preference = 4;
                break;
        }
        switch (op2) {
            case "+":
            case "-":
                op2_preference = 1;
                break;
            case "*":
            case "/":
                op2_preference = 2;
                break;
            case "^":
                op2_preference = 3;
            case "(":
            case ")":
                op2_preference = 4;
                break;
        }

        return op1_preference - op2_preference;

    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

}
