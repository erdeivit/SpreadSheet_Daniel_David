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
public class NumericalValue extends Data {

    public NumericalValue(String content, String result) {
        super(content, result);
    }

    public NumericalValue(String content) {
        super(content);
    }

    /*
    In that case as the content of the cell is just a Numerical Value, the
    content is copied in the result variable of Data.
    */
    @Override
    public void computeResult(Map<String, Cell> cellMap) {

        this.result = String.valueOf(Double.parseDouble(this.content));
    }
}
