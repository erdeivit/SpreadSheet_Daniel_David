/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author David Hernandez
 */
public class Sum extends Function {

    public Sum(String expression) {
        super(expression);
    }

    @Override
    public String calculate(Map<String, Cell> cellMap) {
        this.factors = expression.split(";");
        //double a = Double.parseDouble(this.factors[0]);
        double sum = 0.0;
        String[] range = null;
        String c;
        for (int i = 0; i < this.factors.length; i++) {
            try {
                //RANGE FOUND
                if (this.factors[i].indexOf(":") > 0) {
                range = this.factors[i].split(":"); 
                for (String r :range)
                {
                    while (i < r.length())
                    {
                       c = String.valueOf(r.charAt(i));
                    }
                }
//                    //SAME FIRST LETTER
//                    if (range[0].charAt(0) == range[1].charAt(1))
//                    {
//                        //SECOND CHARACTER STILL A LETTER, THEN WE HAVE AA REFERENCE
//                        if (String.valueOf(range[0].charAt(1)).matches("\\D"))
//                        {
//                            
//                        }
//                        
//                    }
                    
                    while (i < range[i].length()) {  
//                        c = String.valueOf(range[i].charAt(i));
//                        //c is a NUMBER
//                        if (c.matches("-?\\d+(\\.\\d+)?")) {
//                            String num = "";
//                            while (c.matches("-?\\d+(\\.\\d+)?") || c.matches("\\.")) {
//                                num = num + c;
//                                i++;
//                            }
//                            this.factors[i] = num;
//                        }
//                        //C IS A CHARACTER
//                        else if (c.matches("\\D"))
//                        {
//                        
//                        }
                        Calculator calculator = new Calculator(cellMap, this.factors[i]);
                        this.factors[i] = calculator.calculate();
                        sum = sum + Double.parseDouble(this.factors[i]);
                        }
                    }else { //RANGE NOT FOUND
                    Calculator calculator = new Calculator(cellMap, this.factors[i]);
                    this.factors[i] = calculator.calculate();
                    sum = sum + Double.parseDouble(this.factors[i]);
                }
                }catch (Exception ex) {
                this.result = ex.getMessage();
            }
            }
            this.result = String.valueOf(sum);
            return this.result;
        }

    }
