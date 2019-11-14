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
public abstract class Piece {
    
    protected Color colour;
    
    public Piece (Color colour)
    {
        this.colour = colour;
    }
    
    public abstract void isPieceMovement (int rs, int cs, int rd, int cd) throws NoPieceMovementException;
 
    public abstract void isPathFree (int rs, int cs, int rd, int cd, Board b) throws PathFreeException; 

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public void canReachDestination(int rO, int cO, int rD, int cD, Board b) throws NoPieceMovementException, PathFreeException {
        isPieceMovement(rO,cO,rD,cD);
        isPathFree(rO,cO,rD,cD,b);
        
    }

    
}
