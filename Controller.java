/**
 * @file Controller.java
 * @author Mahad Aziz - azizm17
 * @brief Contains an abstract object for playing a game of 2048.
 * @date April 12, 2021
 */
package src;

import java.util.Scanner;

/**
 * @brief An abstract object for playing a game of 2048 on the terminal
 * @details A module used to play a game of 2048 using Game and Display on the terminal
 */
public class Controller {
    private Game game;
    private Display display;
    private static Controller controller = null;

    private Scanner keyboard = new Scanner(System.in);

    /**
     * @brief Constructor for the controller
     * @details Initializes the GameT and Display objects
     */
    private Controller(Game model, Display view) {
        this.game = model;
        this.display = view;
    }

    /**
     * @brief Public static method for obtaining a single Controller instance
     * @return The single Controller object
     */
    public static Controller getInstance (Game model, Display view) {
        if (controller == null) {
            controller = new Controller(model, view);
        } return controller;
    }

    /**
     * @brief Run the simulation of the game.
     * @details Runs the simulation, accepts user input and moves the tiles on the board accordingly.
     * Displays the board and score along with any necessary messages.
     */
    public void runGame() {
        initializeGame();
        displayStartMessage();
        displayInstructions();
        displayBoard();
        boolean win = false;
        while (!game.noPossibleMoves()) {
            System.out.print("Move:\t");
            String input = readInput();
            if (input.equals("w")) {
                game.makeMove(Directions.up);}
            else if (input.equals("s")) {
                game.makeMove(Directions.down);}
            else if (input.equals("a")) {
                game.makeMove(Directions.left);}
            else if (input.equals("d")) {
                game.makeMove(Directions.right);}
            else if (input.equals("q")) {break;}
            displayScore();
            displayBoard();
            if (game.gameComplete() && !win) {
                win = true;
                displayWinningMessage();
                break;
            }
        } displayEndingMessage();
    }

    /**
     * @brief Initializes the Game object to start a new game
     * @details The board is initialized to contain all zeros and two random tiles are added
     */
    public void initializeGame() {
        this.game = new Game();
    }

    /**
     * @brief Returns the user's input
     * @return The string input that the user typed in the terminal
     */
    public String readInput() {
        return keyboard.nextLine();
    }

    /**
     * @brief Updates the display module to display start message
     */
    public void displayStartMessage() {
        this.display.printStartMessage();
    }

    /**
     * @brief Updates the display module to display the game board
     */
    public void displayBoard() {
        this.display.printBoard(game);
    }

    /**
     * @brief Updates the display module to display the score
     */
    public void displayScore() {
        this.display.printScore(game);
    }

    /**
     * @brief Updates the display module to display the game over message
     */
    public void displayEndingMessage() {
        this.display.printEndingMessage();
    }

    /**
     * @brief Updates the display module to display the message the game won message
     */
    public void displayWinningMessage() {
        this.display.printWinningMessage();
    }

    /**
     * @brief Updates the display module to display the game instructions
     */
    public void displayInstructions() {
        this.display.printInstructions();
    }
}
