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
public class Board {
    
    private Square[][] squares ;

    public Board() {
        squares = new Square[8][8] ;
        
        //RELLENAR EL TABLERO DE PIEZAS
        for(int i = 0; i<8 ; i++){
            for(int j = 0; j<8 ; j++){
            squares[i][j] = new Square();
            }
        }
        
        /*
        //PAWNS 
        for(int i = 0; i<8 ; i++){
            squares[1][i].setPiece(new Pawn(Color.BLACK));
            squares[6][i].setPiece(new Pawn(Color.WHITE));
        }
        //ROOKS
        squares[0][0].setPiece(new Rook(Color.BLACK));
        squares[0][7].setPiece(new Rook(Color.BLACK));
        squares[7][0].setPiece(new Rook(Color.WHITE));
        squares[7][7].setPiece(new Rook(Color.WHITE));
        
        //KNIGHTS
        squares[0][1].setPiece(new Knight(Color.BLACK));
        squares[0][6].setPiece(new Knight(Color.BLACK));
        squares[7][1].setPiece(new Knight(Color.WHITE));
        squares[7][6].setPiece(new Knight(Color.WHITE));
        
        //BISHOP
        squares[0][2].setPiece(new Bishop(Color.BLACK));
        squares[0][5].setPiece(new Bishop(Color.BLACK));
        squares[7][2].setPiece(new Bishop(Color.WHITE));
        squares[7][5].setPiece(new Bishop(Color.WHITE));
        
        //QUEEN
        squares[0][3].setPiece(new Queen(Color.BLACK));
        squares[7][3].setPiece(new Queen(Color.WHITE));
        
        //KING
        squares[0][4].setPiece(new King(Color.BLACK));
        squares[7][4].setPiece(new King(Color.BLACK));
        */
        
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }
    
    public void setPiece(int r, int c, Piece piece){
        this.squares[r][c].setPiece(piece);
    }
    public Piece getPiece(int r, int c){
        return this.squares[r][c].getPiece();
    }
}
