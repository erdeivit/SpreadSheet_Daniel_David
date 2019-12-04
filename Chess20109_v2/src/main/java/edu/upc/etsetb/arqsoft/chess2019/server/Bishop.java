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
public class Bishop extends Piece{

    public Bishop(Color color) {
        super(color);
    }
        
    public void isPieceMovement (int rs, int cs, int rd, int cd) throws NoPieceMovementException{
        
      if ( Math.abs(rd-rs) != Math.abs(cd-cs)){
          throw new NoPieceMovementException();
      }
        
    }
 
    public void isPathFree (int rs, int cs, int rd, int cd, Board b) throws PathFreeException{

      if (rd-rs > 0 && cd-cs > 0){ //CUADRANTE DEBAJO DERECHA
          
          for (int i = 1 ; i <= (rd-rs) ; i++ ){
              if (b.getPiece(rs+i,cs+i) == null){
                  throw new PathFreeException();
              }
          }
          
      }
      else if (rd-rs < 0 && cd-cs > 0){ //CUADRANTE SUPERIOR DERECHO
          
          for (int i = 1 ; i <= (rs-rd) ; i++ ){
              if (b.getPiece(rs-i,cs+i) == null){
                  throw new PathFreeException();
              }
          }
          
      }
      else if (rd-rs < 0 && cd-cs < 0){ // CUADREANTE SUPERIOR IZQUIERDP
          
          for (int i = 1 ; i <= (rs-rd) ; i++ ){
              if (b.getPiece(rs-i,cs-i) == null){
                  throw new PathFreeException();
              }
          }
      }
      else if (rd-rs > 0 && cd-cs < 0){ //CUADRANTE INFERIOR DERECHO
          
           for (int i = 1 ; i <= (rd-rs) ; i++ ){
              if (b.getPiece(rs+i,cs-i) == null){
                  throw new PathFreeException();
              }
          }
      }

        

    
        
    }
    
}
