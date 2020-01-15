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
