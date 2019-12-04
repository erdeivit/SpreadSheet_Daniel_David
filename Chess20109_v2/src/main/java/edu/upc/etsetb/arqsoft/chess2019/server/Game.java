/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JuanCarlos
 */
public class Game {

    private ServerProtocolMngr protMngr;
    Board board;
    Player player_white, player_black;
    Player playingPlayer;


    public Game() {
        
        this.board = new Board();
        this.player_black = new Player(Color.BLACK,board);
        this.player_white = new Player(Color.WHITE,board);
        this.playingPlayer = new Player(Color.WHITE,board);
    }
    
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer_white() {
        return player_white;
    }

    public void setPlayer_white(Player player_white) {
        this.player_white = player_white;
    }

    public Player getPlayer_black() {
        return player_black;
    }

    public void setPlayer_black(Player player_black) {
        this.player_black = player_black;
    }
    

    public void setServerProtMngr(ServerProtocolMngr protMngr) {
        this.protMngr = protMngr;
    }

    public void move(int rO, int cO, int rD, int cD){
        
        rO = rO-1;
        cO = cO-1;
        rD = rD-1;
        cD = cD-1;
        
        Piece oPiece = this.board.getPiece(rO, cO);
        Color playingColor = this.playingPlayer.getColor();

     
        if(oPiece == null){
            this.protMngr.sendFromServerToClient("E You do not own any piece in this square");
            return;
        }
        
         else{
        
            Color oPieceColor = oPiece.getColour();

            if(oPieceColor!=playingColor){
                this.protMngr.sendFromServerToClient("E the color of the piece in the origin square is not" +
                " the right one. Expected " + playingColor + ". Arrived " + oPieceColor);
            return ;
        
            }
        }
        
        Piece dPiece = this.board.getPiece(rD, cD);
        
        if(dPiece != null && dPiece.getColour()== playingPlayer.getColor()){
            this.protMngr.sendFromServerToClient("E You own a piece in the destination square");
            return;
        }

        try {
            this.playingPlayer.move(oPiece,rO,cO,rD,cD,this.board);
            //QUEDA COMPROBAR LO DEL JAQUE AL MOVER LA PIEZA
            this.playingPlayer.proceedToMove(oPiece,rO,cO,rD,cD,this.board);
            

        } catch (NoPieceMovementException ex) {
            this.protMngr.sendFromServerToClient("E The movement is not valid");
            return ;
        } catch (PathFreeException ex) {
            this.protMngr.sendFromServerToClient("E There is a piece in your way to the destination, not valid movement");
            return ;
        }
        
        
        /*
        Initial version: it just sends back an OK message to the client.
        You should modify its code for implementing the sequence diagram in the 
        script of the lab session

        NOTE: USE THE FOLLOWING UNCOMMENTED INSTRUCTION FOR SENDING AN ERROR MESSAGE TO THE CLIENT.
        AN ERROR MESSAGE SHALL BE AN END-OF-LINE FREE STRING STARTING WITH "E "
         */
        //         this.protMngr.sendFromServerToClient("E this is an error message");

        
        
        /* 
        DO NOT CHANGE THE CODE BELOW.
        FINAL PART OF THE METHOD. IF ARRIVED HERE, THE MOVEMENT CAN BE PERFORMED
        METHOD assessCheckOrCheckMate(...) CHECKS WHETHER THERE IS CHECK OR CHECK-MATE.
        IF IT IS CHECK-MATE THE METHOD RETURNS TRUE AND A NON-EMPTY STRING
        IF THERE IS CHECK THE METHOD RETURNS FALSE AND A NON-EMPTY STRING
        IF THERE IS NONE OF BOTH, THE METHOD RETURNS FALSE AND AN EMPTY STRING
         */
        StringBuilder assessMess = new StringBuilder();
        boolean isCheckMate = this.assessCheckOrCheckMate(assessMess);

        if (assessMess.length() == 0) {
            this.protMngr.sendFromServerToClient("OK");
        } else {
            this.protMngr.sendFromServerToClient(assessMess.insert(0, "OK\n").toString());
        }
        if(isCheckMate){
            this.proceedToFinalizedGame() ;
        }

    }

    private boolean assessCheckOrCheckMate(StringBuilder assessMess) {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return false ;
    }

    private void proceedToFinalizedGame() {
        // IF THE PROGRAM SHOULD BE COMPLETED, IT SHOULD BE IMPLEMENTED
        return ;
    }
    
 
}
