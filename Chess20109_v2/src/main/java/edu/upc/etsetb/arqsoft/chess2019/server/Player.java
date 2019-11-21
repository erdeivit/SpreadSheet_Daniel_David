/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel León
 */
public class Player {
    
    List<Piece> listofpieces;
    Color color;

    public List<Piece> getListofpieces() {
        return listofpieces;
    }

    public void setListofpieces(List<Piece> listofpieces) {
        this.listofpieces = listofpieces;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Player(Color color, Board board) {
        this.color = color;
        this.listofpieces  = new ArrayList<>();
    
        //RELLENAR EL TABLERO
        if(color == color.WHITE){
            
            //PAWNS 
            for(int i = 0; i<8 ; i++){
                
             Piece pawn = new Pawn(color);
             board.setPiece(6,i,pawn);
             this.listofpieces.add(pawn);

            }
            //ROOKS
            Piece rook1 = new Rook(color);
            Piece rook2 = new Rook(color);
            board.setPiece(7,0,rook1);
            board.setPiece(7,7,rook2);
            this.listofpieces.add(rook1);
            this.listofpieces.add(rook2);
            
            //KNIGHTS
            Piece knight1 = new Knight(color);
            Piece knight2 = new Knight(color);
            board.setPiece(7,1,knight1);
            board.setPiece(7,6,knight2);
            this.listofpieces.add(knight1);
            this.listofpieces.add(knight2);
            
            //BISHOP
            Piece bishop1 = new Bishop(color);
            Piece bishop2 = new Bishop(color);
            board.setPiece(7,2,bishop1);
            board.setPiece(7,5,bishop2);
            this.listofpieces.add(bishop1);
            this.listofpieces.add(bishop2);
            
            //QUEEN
            Piece queen = new Queen(color);
            board.setPiece(7,3,queen);
            this.listofpieces.add(queen);
            
            //KING
            Piece king = new King(color);
            board.setPiece(7,4,king);
            this.listofpieces.add(king);
        }
        else{
            //PAWNS 
            for(int i = 0; i<8 ; i++){
                
             Piece pawn = new Pawn(color);
             board.setPiece(1,i,pawn);
             this.listofpieces.add(pawn);

            }
            //ROOKS
            Piece rook1 = new Rook(color);
            Piece rook2 = new Rook(color);
            board.setPiece(0,0,rook1);
            board.setPiece(0,7,rook2);
            this.listofpieces.add(rook1);
            this.listofpieces.add(rook2);
            
            //KNIGHTS
            Piece knight1 = new Knight(color);
            Piece knight2 = new Knight(color);
            board.setPiece(0,1,knight1);
            board.setPiece(0,6,knight2);
            this.listofpieces.add(knight1);
            this.listofpieces.add(knight2);
            
            //BISHOP
            Piece bishop1 = new Bishop(color);
            Piece bishop2 = new Bishop(color);
            board.setPiece(0,2,bishop1);
            board.setPiece(0,5,bishop2);
            this.listofpieces.add(bishop1);
            this.listofpieces.add(bishop2);
            
            //QUEEN
            Piece queen = new Queen(color);
            board.setPiece(0,3,queen);
            this.listofpieces.add(queen);
            
            //KING
            Piece king = new King(color);
            board.setPiece(0,4,king);
            this.listofpieces.add(king);
            
 
        }

    }
    
    public static void move(Piece oPiece,int rO,int cO,int rD,int cD,Board b) throws NoPieceMovementException, PathFreeException{
        
        oPiece.canReachDestination(rO,cO,rD,cD,b);
        
    }

    public void proceedToMove(Piece oPiece,int rO,int cO, int rD, int cD, Board b) {
        b.setPiece(rD, cD, oPiece);
        b.setPiece(rO, cO, null);    
    }
    
    
}
