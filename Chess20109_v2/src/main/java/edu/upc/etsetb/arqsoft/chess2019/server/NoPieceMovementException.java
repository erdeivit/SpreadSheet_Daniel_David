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
public class NoPieceMovementException extends Exception {

    /**
     * Creates a new instance of <code>NoPieceMovementException</code> without
     * detail message.
     */
    public NoPieceMovementException() {
    }

    /**
     * Constructs an instance of <code>NoPieceMovementException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoPieceMovementException(String msg) {
        super(msg);
    }
}
