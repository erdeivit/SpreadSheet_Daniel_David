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
public class Queen extends Piece{
    
    public Queen(Color color) {
        super(color);
    }
        
    public void isPieceMovement (int rs, int cs, int rd, int cd) throws NoPieceMovementException{
           
        if (!(rs == rd || cs == cd || (Math.abs(rd-rs) == Math.abs(cd-cs)))) {
            throw new NoPieceMovementException();
        }

    }
 
    public void isPathFree (int rs, int cs, int rd, int cd, Board b) throws PathFreeException{
        
    }
}
