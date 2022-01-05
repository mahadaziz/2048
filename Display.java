/**
 * @file Display.java
 * @author Mahad Aziz - azizm17
 * @brief Contains an abstract object for displaying the game board and various messages used in 2048.
 * @date April 12, 2021
 */
package src;

/**
 * @brief An abstract object for displaying the game board and various messages related to a game of 2048.
 * @details Includes methods to display the game board, score, instructions, and other useful messages.
 */
public class Display {
    private static Display display = null;
    private Display(){}

    /**
     * @brief Public static method for obtaining a single Display instance
     * @return The single Display object
     */
    public static Display getInstance() {
        if (display == null) {
            return display = new Display();
        }
        return display;
    }

    /**
     * @brief Displays the board to the user
     * @details Loops through each element of the 2 dimensional array in the board field and prints it out
     */
    public void printBoard(Game game) {
        for (int[] row : game.getBoard()) {
            System.out.println("_____________________");
            for (int space : row) {
                if (space == 0) {
                    System.out.print("|    ");
                }
                else if (space == 2 || space == 4 || space == 8) {
                    System.out.print("|"+ space + "   ");
                }
                else if (space == 16 || space == 32 || space == 64) {
                    System.out.print("|"+ space + "  ");
                }
                else if (space == 128 || space == 256 || space == 512) {
                    System.out.print("|"+ space + " ");
                }
                else if (space == 1024 || space == 2048) {
                    System.out.print("|"+ space + "");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("_____________________");
        System.out.println();
    }

    /**
     * @brief Displays the user's score
     * @param game An instance of the Game object that holds the board to be displayed
     * @details Prints out the user's current score using the getter from the Game ADT
     */
    public void printScore(Game game) {
        System.out.println("-----");
        System.out.println("Score "+game.getScore());
        System.out.println("-----");
    }

    /**
     * @brief Displays the game's start message
     */
    public void printStartMessage() {
        System.out.println("--------------------------");
        System.out.println("           2048           ");
        System.out.println("--------------------------");
    }

    /**
     * @brief Displays this message when the user has won the game
     */
    public void printWinningMessage() {
        System.out.println("Congratulations! You reached the 2048 tile and finished the game.");
    }

    /**
     * @brief Displays the game over message
     */
    public void printEndingMessage() {
        System.out.println("Game Over");
    }

    /**
     * @brief Displays the game's instruction message
     */
    public void printInstructions() {
        System.out.println("------------------------------------------");
        System.out.println("Up: w, Down: s, Left: a, Right: d, Quit: q");
        System.out.println("------------------------------------------");
    }
}
