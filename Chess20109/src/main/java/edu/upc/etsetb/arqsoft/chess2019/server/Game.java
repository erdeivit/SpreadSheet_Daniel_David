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
public class Game {
    
    Board board;
    Player player_white;
    Player player_black;

    public Game() {
        
        this.board = new Board();
        this.player_black = new Player(Color.BLACK,board);
        this.player_white = new Player(Color.WHITE,board);
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
    
    public static void main(String args[]){
        Game game = new Game();
        System.out.println("GAME CREATED");
    }
    
}
