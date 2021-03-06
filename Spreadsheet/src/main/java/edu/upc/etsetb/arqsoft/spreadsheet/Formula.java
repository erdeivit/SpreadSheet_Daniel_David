/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

import java.util.Map;

/**
 *
 * @author Daniel León and David Hernández
 */
public class Formula extends Data {

    public Formula(String content, String result) {
        super(content, result);
    }

    public Formula(String content) {
        super(content);
    }

    /*
    The computeResult() function is the one that triggers all the necessary
    mechanisms to calculate the final result of the cell. Therefore, stores the
    final result of the content of the cell in the result variable of the Data.
    */
    @Override
    public void computeResult(Map<String, Cell> cellMap){
        ShuntingYard sy = new ShuntingYard();
        try {
          Calculator calculator = new Calculator(cellMap,this.content);
          this.result = calculator.calculate();
        } catch (ExpressionException ex) {
            this.result = ex.getMessage();
        } catch (Exception | StackOverflowError EmtpyStackException)
        {
            this.result = "Equation not correct. Review it or the cells references.";
        }
    }

}
