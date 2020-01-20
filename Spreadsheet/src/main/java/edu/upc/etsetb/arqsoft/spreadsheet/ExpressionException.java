/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet;

/**
 *
 * @author Daniel León and David Hernández
 */
public class ExpressionException extends Exception {

    /**
     * Creates a new instance of <code>ExpressionException</code> without detail
     * message.
     */
    public ExpressionException() {
    }

    /**
     * Constructs an instance of <code>ExpressionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExpressionException(String msg) {
        super(msg);
    }
}
