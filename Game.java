/**
 * @file Game.java
 * @author Mahad Aziz - azizm17
 * @brief Game abstract data type class that simulates the gameplay of the game 2048
 * @date April 12, 2021
 */
package src;

import java.util.Arrays;
import java.util.Random;

/**
 * @brief An ADT of Game that allows the user to play the game 2048
 * @details The game takes no parameters and the enumeration class is used to control the movement of the game board
 */
public class Game {

    private int score;
    private int[][] board;
    protected boolean isTest;

    /**
     * @brief Initializes the Game object
     * @details Initializes the score and board fields and also spawns two random values in the empty board
     */
    public Game() {
        this.score = 0;
        this.board = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        this.isTest = false;
        spawnRandom();
        spawnRandom();
    }

    /**
     * @brief Getter method that returns the game board
     * @return A 2 dimensional array that simulates the game board with all the tiles
     */
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * @brief Getter method that returns the user's score
     * @return An integer that holds the current score the user has achieved
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @brief Setter method that sets the game board
     * @param b A 2 dimensional array that will be set to the board field of the Game class
     */
    public void setBoard(int[][] b) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = b[i][j];
            }
        }
    }

    /**
     * @brief Calls the corresponding movement function based on the direction the user wants to move the board
     * @param d An enumerated type that holds the direction the user wants to move the board
     */
    public void makeMove(Directions d) {
        if (d == Directions.up) {
            moveUp();
        }
        else if (d == Directions.down) {
            moveDown();
        }
        else if (d == Directions.left) {
            moveLeft();
        }
        else if (d == Directions.right){
            moveRight();
        }
        gameComplete();
        noPossibleMoves();
    }

    /**
     * @brief This function determines if the user has won the game based on if a 2048 tile exists on the board
     */
    public boolean gameComplete() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief This function determines if the user can possibly make anymore moves otherwise the game ends
     */
    public boolean noPossibleMoves() {
        Game g = new Game();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                g.board[i][j] = this.board[i][j];
            }
        }
        if (!(g.shiftLeft() || g.shiftRight() || g.shiftDown() || g.shiftUp() || g.addLeft() || g.addRight() || g.addUp() || g.addDown())) {
            return true;
        }
        return false;
    }

    /**
     * @brief This function spawns a random 2 or 4 value on the game board
     * @details A position on the board is randomly selected and if it currently holds a 0 then a random number is placed
     * otherwise another random positions is selected
     */
    public void spawnRandom() {
        Random rand = new Random();
        int[] tiles = new int[] {2,4};
        int i = rand.nextInt(4);
        int j = rand.nextInt(4);
        while (board[i][j] != 0) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
        }
        board[i][j] = tiles[rand.nextInt(2)];
    }

    /**
     * This function calls the shiftLeft() and the addLeft() functions if the user decides to shift the board to the left
     * If either shiftLeft() or addLeft() make changes to the board then a random value is spawned on the board
     * otherwise the board did not change and the user can make another move
     */
    private void moveLeft() {
        boolean valid1;
        boolean valid2;
        valid1 = shiftLeft();
        valid2 = addLeft();
        if ((valid1 || valid2) && !isTest) {
            spawnRandom();
        }
    }

    /**
     * This function shifts all the tiles on the game board to the left
     * Each row is checked whether it was even shifted to the left or not. If a row was shifted then true is returned,
     * otherwise if no rows were shifted then false is returned
     * A boolean is returned that tells if the board was shifted to the left in any way
     */
    private boolean shiftLeft() {
        int j = 0;
        boolean valid1 = false;
        for (int[] row : this.board) {
            int[] temp = new int[]{0, 0, 0, 0};
            int[] temp2 = row;
            int i = 0;
            for (int space : row) {
                if (space != 0) {
                    temp[i] = space;
                    i++;
                }
            }
            this.board[j] = temp;
            if (!Arrays.equals(this.board[j],temp2)) {
                valid1 = true;
            }
            j++;
        }
        return valid1;
    }

    /**
     * This function adds any adjacent tiles on the board and then shifts all the tiles on the game board to the left
     * Adjacent tiles are only added if they hold the same value and if any tiles are added together only then
     * are all the tiles on the board shifted to the left
     * A boolean is returned that tells if there was an instance where adjacent tiles were added together
     */
    private boolean addLeft() {
        int j = 0;
        boolean valid2 = false;
        for (int row = 0; row < this.board.length; row++) {
            for (int space = 0; space < this.board.length-1; space++) {
                if (this.board[row][space] != 0 && this.board[row][space+1] != 0 && this.board[row][space] == this.board[row][space+1]) {
                    valid2 = true;
                    this.board[row][space] *= 2;
                    this.board[row][space+1] = 0;
                    this.score += this.board[row][space];
                }
            }
        }
        if (valid2) {
            shiftLeft();
        }
        return valid2;
    }

    /**
     * This function calls the shiftRight() and the addRight() functions if the user decides to shift the board to the right
     * If either shiftRight() or addRight() make changes to the board then a random value is spawned on the board
     * otherwise the board did not change and the user can make another move
     */
    private void moveRight() {
        boolean valid1;
        boolean valid2;
        valid1 = shiftRight();
        valid2 = addRight();
        if ((valid1 || valid2) && !isTest) {
            spawnRandom();
        }
    }

    /**
     * This function shifts all the tiles on the game board to the right
     * Each row is checked whether it was even shifted to the right or not. If a row was shifted then true is returned,
     * otherwise if no rows were shifted then false is returned
     * A boolean is returned that tells if the board was shifted to the right in any way
     */
    private boolean shiftRight() {
        int j = 0;
        boolean valid1 = false;
        for (int[] row : this.board) {
            int[] temp = new int[]{0, 0, 0, 0};
            int[] temp2 = row;
            int i = 3;
            for (int space = this.board.length-1; space >= 0; space--) {
                if (row[space] != 0) {
                    temp[i] = row[space];
                    i--;
                }
            }
            this.board[j] = temp;
            if (!Arrays.equals(this.board[j],temp2)) {
                valid1 = true;
            }
            j++;
        }
        return valid1;
    }

    /**
     * This function adds any adjacent tiles on the board and then shifts all the tiles on the game board to the right
     * Adjacent tiles are only added if they hold the same value and if any tiles are added together only then
     * are all the tiles on the board shifted to the right
     * A boolean is returned that tells if there was an instance where adjacent tiles were added together
     */
    private boolean addRight() {
        int j = 0;
        boolean valid2 = false;
        for (int row = 0; row < this.board.length; row++) {
            for (int space = this.board.length-1; space > 0; space--) {
                if (this.board[row][space] != 0 && this.board[row][space-1] != 0 && this.board[row][space] == this.board[row][space-1]) {
                    valid2 = true;
                    this.board[row][space] *= 2;
                    this.board[row][space-1] = 0;
                    this.score += this.board[row][space];
                }
            }
        }
        if (valid2) {
            shiftRight();
        }
        return valid2;
    }

    /**
     * This function calls the shiftUp() and the addUp() functions if the user decides to shift the board upward
     * If either shiftUp() or addUp() make changes to the board then a random value is spawned on the board
     * otherwise the board did not change and the user can make another move
     */
    private void moveUp() {
        boolean valid1;
        boolean valid2;
        valid1 = shiftUp();
        valid2 = addUp();
        if ((valid1 || valid2) && !isTest) {
            spawnRandom();
        }
    }

    /**
     * This function shifts all the tiles on the game board upward
     * Each column is checked whether it was even shifted upward or not. If a column was shifted then true is returned,
     * otherwise if no columns were shifted then false is returned
     * A boolean is returned that tells if the board was shifted upward in any way
     */
    private boolean shiftUp() {
        int j = 0;
        boolean valid1 = false;
        for (int row = 0; row < this.board.length; row++) {
            int[] temp = new int[]{0, 0, 0, 0};
            int[] temp2 = new int[4];
            int[] temp3 = new int[4];
            for (int k = 0; k < this.board.length; k++) {
                temp2[k] = this.board[k][row];
            }
            int i = 0;
            for (int space = 0; space < this.board.length; space++) {
                if (this.board[space][row] != 0) {
                    temp[i] = this.board[space][row];
                    i++;
                }
            }
            for (int k = 0; k < this.board.length; k++) {
                this.board[k][row] = temp[k];
                temp3[k] = temp[k];
            }
            if (!Arrays.equals(temp3,temp2)) {
                valid1 = true;
            }
            j++;
        }
        return valid1;
    }

    /**
     * This function adds any vertically adjacent tiles on the board and then shifts all the tiles on the game board upward
     * Adjacent tiles are only added if they hold the same value and if any tiles are added together only then
     * are all the tiles on the board upward
     * A boolean is returned that tells if there was an instance where adjacent tiles were added together
     */
    private boolean addUp() {
        int j = 0;
        boolean valid2 = false;
        for (int row = 0; row < this.board.length; row++) {
            for (int space = 0; space < this.board.length-1; space++) {
                if (this.board[space][row] != 0 && this.board[space+1][row] != 0 && this.board[space][row] == this.board[space+1][row]) {
                    valid2 = true;
                    this.board[space][row] *= 2;
                    this.board[space+1][row] = 0;
                    this.score += this.board[space][row];
                }
            }
        }
        if (valid2) {
            shiftUp();
        }
        return valid2;
    }

    /**
     * This function calls the shiftDown() and the addDown() functions if the user decides to shift the board downward
     * If either shiftDown() or addDown() make changes to the board then a random value is spawned on the board
     * otherwise the board did not change and the user can make another move
     */
    private void moveDown() {
        boolean valid1;
        boolean valid2;
        valid1 = shiftDown();
        valid2 = addDown();
        if ((valid1 || valid2) && !isTest) {
            spawnRandom();
        }
    }

    /**
     * This function shifts all the tiles on the game board downward
     * Each column is checked whether it was even shifted downward or not. If a column was shifted then true is returned,
     * otherwise if no columns were shifted then false is returned
     * A boolean is returned that tells if the board was shifted downward in any way
     */
    private boolean shiftDown() {
        int j = 0;
        boolean valid1 = false;
        boolean valid2 = false;
        for (int row = 0; row < this.board.length; row++) {
            j = 0;
            int[] temp = new int[]{0, 0, 0, 0};
            int[] temp2 = new int[4];
            int[] temp3 = new int[4];
            for (int k = this.board.length-1; k >= 0; k--) {
                temp2[j] = this.board[k][row];
                j++;
            }
            int i = 3;
            for (int space = this.board.length-1; space >= 0; space--) {
                if (this.board[space][row] != 0) {
                    temp[i] = this.board[space][row];
                    i--;
                }
            }
            j = 0;
            for (int k = this.board.length-1; k >= 0; k--) {
                this.board[k][row] = temp[k];
                temp3[j] = temp[k];
                j++;
            }
            if (!Arrays.equals(temp3,temp2)) {
                valid1 = true;
            }
            j++;
        }
        return valid1;
    }

    /**
     * This function adds any vertically adjacent tiles on the board and then shifts all the tiles on the game board downward
     * Adjacent tiles are only added if they hold the same value and if any tiles are added together only then
     * are all the tiles on the board downward
     * A boolean is returned that tells if there was an instance where adjacent tiles were added together
     */
    private boolean addDown() {
        int j = 0;
        boolean valid2 = false;
        for (int row = 0; row < this.board.length; row++) {
            for (int space = this.board.length-1; space > 0; space--) {
                if (this.board[space][row] != 0 && this.board[space-1][row] != 0 && this.board[space][row] == this.board[space-1][row]) {
                    valid2 = true;
                    this.board[space][row] *= 2;
                    this.board[space-1][row] = 0;
                    this.score += this.board[space][row];
                }
            }
        }
        if (valid2) {
            shiftDown();
        }
        return valid2;
    }
}
