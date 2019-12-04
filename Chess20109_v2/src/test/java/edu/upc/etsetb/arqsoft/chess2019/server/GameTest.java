/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Daniel León
 */
@RunWith(MockitoJUnitRunner.class)

public class GameTest {
    

    @Mock
    ServerProtocolMngr protMngr;

    
    @InjectMocks
    Game instance;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        this.instance = new Game();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of move method, of class Game.
     */
    @Test
    public void testMoveNoPieceInSquare() {
                       
        System.out.println("move");
        
        int rO = 5;
        int cO = 5;
        int rD = 7;
        int cD = 7;

        instance.setServerProtMngr(this.protMngr);
        instance.move(rO, cO, rD, cD);
        Mockito.verify(this.protMngr).sendFromServerToClient("E You do not own any piece in this square");
        // TODO review the generated test code and remove the default call to fail.
        

    }
    
    @Test
    public void testMoveNotSameColor() {
                       
        System.out.println("move");
        
        int rO = 1;
        int cO = 1;
        int rD = 7;
        int cD = 7;

        instance.setServerProtMngr(this.protMngr);
        instance.move(rO, cO, rD, cD);
        Mockito.verify(this.protMngr).sendFromServerToClient("E the color of the piece in the origin square" +
                " is not the right one. Expected WHITE. Arrived BLACK");
        // TODO review the generated test code and remove the default call to fail.

    }
    
    @Test
    public void testMoveOwnPieceDestinationSquare() {
                       
        System.out.println("move");
        
        int rO = 8;
        int cO = 8;
        int rD = 7;
        int cD = 8;

        instance.setServerProtMngr(this.protMngr);
        instance.move(rO, cO, rD, cD);
        Mockito.verify(this.protMngr).sendFromServerToClient("E You own a piece in the destination square");
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
