package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestGame {

    private Game game1;
    private Game game2;
    private Game game3;

    @Before
    public void setUp() {
        game1 = new Game();
        game1.isTest = true;
        game2 = new Game();
        game2.isTest = true;
        game3 = new Game();
        game3.isTest = true;
    }

    @After
    public void tearDown() {
        game1 = null;
        game2 = null;
        game3 = null;
    }

    @Test
    public void test_getScore() {
        // Normal test case
        assertEquals(0, game1.getScore());
    }

    @Test
    public void test_moveLeft1() {
        // Test to shift the tiles left
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.left);
        assertTrue(equalBoard(new int[][]{{2, 0, 0, 0}, {4, 0, 0, 0}, {8, 0, 0, 0}, {16, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveLeft2() {
        // Test to add tiles together to the left
        game3.setBoard(new int[][]{{2, 0, 0, 2}, {0, 4, 0, 4}, {8, 8, 0, 0}, {16, 0, 16, 0}});
        game3.makeMove(Directions.left);
        assertTrue(equalBoard(new int[][]{{4, 0, 0, 0}, {8, 0, 0, 0}, {16, 0, 0, 0}, {32, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveLeft3() {
        // Test when no tiles can be moved to the left
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.left);
        assertTrue(equalBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveLeftScore1() {
        // Test the score of shifting tiles to the left
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.left);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveLeftScore2() {
        // Test the score of adding tiles together to the left
        game3.setBoard(new int[][]{{2, 0, 0, 2}, {0, 4, 0, 4}, {8, 8, 0, 0}, {16, 0, 16, 0}});
        game3.makeMove(Directions.left);
        assertEquals(60, game3.getScore());
    }

    @Test
    public void test_moveLeftScore3() {
        // Test the score when no tiles can be moved to the left
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.left);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveRight1() {
        // Test to shift the tiles right
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.right);
        assertTrue(equalBoard(new int[][]{{0, 0, 0, 2}, {0, 0, 0, 4}, {0, 0, 0, 8}, {0, 0, 0, 16}}, game3.getBoard()));
    }

    @Test
    public void test_moveRight2() {
        // Test to add tiles together to the right
        game3.setBoard(new int[][]{{2, 0, 0, 2}, {0, 4, 0, 4}, {8, 8, 0, 0}, {16, 0, 16, 0}});
        game3.makeMove(Directions.right);
        assertTrue(equalBoard(new int[][]{{0, 0, 0, 4}, {0, 0, 0, 8}, {0, 0, 0, 16}, {0, 0, 0, 32}}, game3.getBoard()));
    }

    @Test
    public void test_moveRight3() {
        // Test when no tiles can be moved to the right
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.right);
        assertTrue(equalBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveRightScore1() {
        // Test the score of shifting tiles to the right
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.right);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveRightScore2() {
        // Test the score of adding tiles together to the right
        game3.setBoard(new int[][]{{2, 0, 0, 2}, {0, 4, 0, 4}, {8, 8, 0, 0}, {16, 0, 16, 0}});
        game3.makeMove(Directions.right);
        assertEquals(60, game3.getScore());
    }

    @Test
    public void test_moveRightScore3() {
        // Test the score when no tiles can be moved to the right
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.right);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveUp1() {
        // Test to shift the tiles up
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.up);
        assertTrue(equalBoard(new int[][]{{2, 4, 16, 0}, {8, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveUp2() {
        // Test to add tiles together upward
        game3.setBoard(new int[][]{{2, 4, 0, 16}, {0, 4, 8, 0}, {2, 0, 8, 0}, {0, 0, 0, 16}});
        game3.makeMove(Directions.up);
        assertTrue(equalBoard(new int[][]{{4, 8, 16, 32}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveUp3() {
        // Test when no tiles can be moved upward
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.up);
        assertTrue(equalBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveUpScore1() {
        // Test the score of shifting tiles upward
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.up);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveUpScore2() {
        // Test the score of adding tiles together upward
        game3.setBoard(new int[][]{{2, 4, 0, 16}, {0, 4, 8, 0}, {2, 0, 8, 0}, {0, 0, 0, 16}});
        game3.makeMove(Directions.up);
        assertEquals(60, game3.getScore());
    }

    @Test
    public void test_moveUpScore3() {
        // Test the score when no tiles can be moved to the upward
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        game3.makeMove(Directions.up);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveDown1() {
        // Test to shift the tiles down
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.down);
        assertTrue(equalBoard(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {2, 0, 0, 0}, {8, 4, 16, 0}}, game3.getBoard()));
    }

    @Test
    public void test_moveDown2() {
        // Test to add tiles together downward
        game3.setBoard(new int[][]{{2, 4, 0, 16}, {0, 4, 8, 0}, {2, 0, 8, 0}, {0, 0, 0, 16}});
        game3.makeMove(Directions.down);
        assertTrue(equalBoard(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {4, 8, 16, 32}}, game3.getBoard()));
    }

    @Test
    public void test_moveDown3() {
        // Test when no tiles can be moved downward
        game3.setBoard(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}});
        game3.makeMove(Directions.down);
        assertTrue(equalBoard(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}}, game3.getBoard()));
    }

    @Test
    public void test_moveDownScore1() {
        // Test the score of shifting tiles downward
        game3.setBoard(new int[][]{{2, 0, 0, 0}, {0, 4, 0, 0}, {8, 0, 0, 0}, {0, 0, 16, 0}});
        game3.makeMove(Directions.down);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_moveDownScore2() {
        // Test the score of adding tiles together downward
        game3.setBoard(new int[][]{{2, 4, 0, 16}, {0, 4, 8, 0}, {2, 0, 8, 0}, {0, 0, 0, 16}});
        game3.makeMove(Directions.down);
        assertEquals(60, game3.getScore());
    }

    @Test
    public void test_moveDownScore3() {
        // Test the score when no tiles can be moved downward
        game3.setBoard(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}});
        game3.makeMove(Directions.down);
        assertEquals(0, game3.getScore());
    }

    @Test
    public void test_gameComplete() {
        // Test to see if the game returns true once the 2048 tile is reached
        game3.setBoard(new int[][]{{0, 0, 0, 2048}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}});
        assertTrue(game3.gameComplete());
    }

    @Test
    public void test_notGameComplete() {
        // Test to see if the game returns false once the 2048 tile is reached
        game3.setBoard(new int[][]{{0, 0, 0, 1024}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}});
        assertFalse(game3.gameComplete());
    }

    @Test
    public void test_gameOver() {
        // Test to see if the game returns true once no more moves can be made on the board
        game3.setBoard(new int[][]{{2, 4, 8, 16}, {4, 8, 16, 32}, {8, 16, 32, 64}, {16, 32, 64, 128}});
        assertTrue(game3.noPossibleMoves());
    }

    @Test
    public void test_notGameOver() {
        // Test to see if the game returns false when more moves can be made on the board
        game3.setBoard(new int[][]{{0, 0, 0, 1024}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 4, 8, 16}});
        assertFalse(game3.noPossibleMoves());
    }

    public static boolean equalBoard(int[][] a1, int[][] a2) {
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1.length; j++) {
                if (a1[i][j] != a2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
