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
public class Knight extends Piece {
    
    public Knight(Color color) {
        super(color);
    }
        
    public void isPieceMovement (int rs, int cs, int rd, int cd) throws NoPieceMovementException{
        
    }
 
    public void isPathFree (int rs, int cs, int rd, int cd, Board b) throws PathFreeException{
        //The path will be free always, the knight can "jump"
    }
}
