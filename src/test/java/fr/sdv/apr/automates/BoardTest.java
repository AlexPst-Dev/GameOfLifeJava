package fr.sdv.apr.automates;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test de la classe Board
 */
public class BoardTest {

    /**
     * Tests de la fonction getState()
     * **/
    @Test
    public void testGetStateCellSucess(){
        Board board = new Board(10, 10);
        boolean state = board.getBoard()[2][3].getState();
        Assert.assertTrue("Success", state);
    }

    @Test
    public void testGetStateCellFailed(){
        Board board = new Board(10, 10);
        boolean state = board.getBoard()[20][30].getState();
        Assert.assertFalse("Failed", state);
    }

    /****************************************/

    /**
     * Tests de la fonction isValidIndex()
     * **/
    @Test
    public void testIsValidIndexSuccess(){
        int x = 2;
        int y = 3;
        Board board = new Board(10, 10);

        Assert.assertTrue(board.isValidIndex(x, y));
    }

    @Test
    public void testIsValidIndexFailed(){
        int x = -2;
        int y = 3;
        Board board = new Board(10, 10);

        Assert.assertFalse(board.isValidIndex(x, y));
    }

    /****************************************/

    /**
     * Tests de la fonction getBoard()
     * **/
    @Test
    public void testGetBoardSuccess(){
        Board board = new Board(10, 10);
        Board.Cell[][] cell = board.getBoard();
        Assert.assertNotNull(cell);
    }

    @Test
    public void testGetBoardFailed(){
        Board board = new Board(-10, 10);
        Board.Cell[][] cell = board.getBoard();
        Assert.assertNull(cell);
    }
}
