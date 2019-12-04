/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.chess2019.server;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class BishopTest {
    
    @Mock
    Board board;
    
    @InjectMocks
    Bishop instance;
    
    public BishopTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
                // MÉTODO POR SI HAY UN CÓDIGO QUE TENGA QUE EJECUTARSE UNA SOLA VEZ ANTES DE COMENZAZR A EJECUTAR LOS TESTS.
        System.out.println("\n****************************************************************");
        System.out.println("\nTests cases for Bishop in BishopTest\n");
    
    }
    
    @AfterClass
    public static void tearDownClass() {
                // MÉTODO PARA CÓDIGO QUE SE EJECUTA UNA SOLA VEZ AL ACABAR LA EJECUCIÓN DE TODOS LOS TESTS
           System.out.println("\nEND of tests cases for Bishop in BishopTest\n");
            System.out.println("\n****************************************************************");
    }
    
    @Before
    public void setUp() {
                // MÉTODO PARA CÓDIGO QUE SE EJECUTARÁ ANTES DE EJECUTAR CADA UNO DE LOS TESTS (SE EJECUTA TANTAS VECES COMO TEST SE EJECUTAN).
        // BEFORE, TEST, BEFORE, TEST, BEFORE, TEST... 
        this.instance = new Bishop(Color.WHITE);
    }
    
    @After
    public void tearDown() {
                // IGUAL A BEFORE PERO SE EJECUTA AL FINALIZAR LA EJECUCIÓN DE CADA UNO DE LOS TESTS. 
    }

    /**
     * Test of getColour (Suponemos que tenemos getColor)
     */
    @Test
    public void testConstAndGetColorOk() throws Exception{
        System.out.println("Test OK of construction of Piece, and " + "getColor() from Piece");
        Bishop bishop = new Bishop(Color.WHITE);
        Color color = bishop.getColour();
        //Assert.assertEquals("EXPECTED, OBTENIDO);
        Assert.assertEquals(Color.BLACK,color);
    }
    /**
     * Test of isPieceMovement method, of class Bishop.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsPieceMovementOKr2c2() throws Exception {
        //AUGMENTA EN 2 LAS ROWS y en 2 LAS COLUMNS.
        System.out.println("isPieceMovement");
        int rs = 4;
        int cs = 4;
        int rd = 6;
        int cd = 6;
        instance.isPieceMovement(rs, cs, rd, cd);
        // TODO review the generated test code and remove the default call to fail.
        // NO TENGO QUE PONER UN ASSERT. ISPIECEMOVEMENT NO DEVUELVE NADA. NO NEWS = GOOD NEWS.
    }
    @Test
    public void testIsPieceMovementOKrm2c2() throws Exception {
        //AUGMENTA EN 2 LAS ROWS y en 2 LAS COLUMNS.
        System.out.println("isPieceMovement");
        int rs = 4;
        int cs = 4;
        int rd = 2;
        int cd = 6;
        instance.isPieceMovement(rs, cs, rd, cd);
        // TODO review the generated test code and remove the default call to fail.
        // NO TENGO QUE PONER UN ASSERT. ISPIECEMOVEMENT NO DEVUELVE NADA. NO NEWS = GOOD NEWS.
    } // POR HACER
    @Test
    public void testIsPieceMovementOKr2cm2() throws Exception {
        //AUGMENTA EN 2 LAS ROWS y en 2 LAS COLUMNS.
        System.out.println("isPieceMovement");
        int rs = 4;
        int cs = 4;
        int rd = 6;
        int cd = 2;
        instance.isPieceMovement(rs, cs, rd, cd);
        // TODO review the generated test code and remove the default call to fail.
        // NO TENGO QUE PONER UN ASSERT. ISPIECEMOVEMENT NO DEVUELVE NADA. NO NEWS = GOOD NEWS.
    } // POR HACER
    @Test
    public void testIsPieceMovementOKrm2cm2() throws Exception {
        //AUGMENTA EN 2 LAS ROWS y en 2 LAS COLUMNS.
        System.out.println("isPieceMovement");
        int rs = 4;
        int cs = 4;
        int rd = 2;
        int cd = 2;
        instance.isPieceMovement(rs, cs, rd, cd);
        // TODO review the generated test code and remove the default call to fail.
        // NO TENGO QUE PONER UN ASSERT. ISPIECEMOVEMENT NO DEVUELVE NADA. NO NEWS = GOOD NEWS.
    } // POR HACER
    
      /**
     *
     * @throws Exception
     */
    @Test(expected = NoPieceMovementException.class)
    public void testIsPieceMovementKOr3cm2() throws Exception {
        //AUGMENTA EN 2 LAS ROWS y en 2 LAS COLUMNS.
        System.out.println("isPieceMovement");
        int rs = 4;
        int cs = 4;
        int rd = 7;
        int cd = 2;
        instance.isPieceMovement(rs, cs, rd, cd);
        // TODO review the generated test code and remove the default call to fail.
        // NO TENGO QUE PONER UN ASSERT. ISPIECEMOVEMENT NO DEVUELVE NADA. NO NEWS = GOOD NEWS.
    } 
    

    /**
     * Test of isPathFree method, of class Bishop.
     */
    
    @Test
    public void testIsPathFree() throws Exception {
        System.out.println("isPathFree");
        Mockito.when(this.board.getPiece(Mockito.anyInt(),Mockito.anyInt())).thenReturn(null) ;
    }
    
    @Test(expected = PathFreeException.class)
    public void tesstIsPathFreeOKNoPiece3c3() throws Exception{
        Bishop oBishop = new Bishop(Color.BLACK);
        
        Mockito.when(this.board.getPiece(6,6)).thenReturn(oBishop);

        int rs = 4;
        int cs = 4;
        int rd = 7;
        int cd = 7;

        instance.isPathFree(rs, cs, rd, cd, this.board);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
    
