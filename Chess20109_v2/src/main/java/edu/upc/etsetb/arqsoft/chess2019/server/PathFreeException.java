/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

/**
 *
 * @author Daniel León
 */
public class PathFreeException extends Exception {

    /**
     * Creates a new instance of <code>PathFreeException</code> without detail
     * message.
     */
    public PathFreeException() {
    }

    /**
     * Constructs an instance of <code>PathFreeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PathFreeException(String msg) {
        super(msg);
    }
}
