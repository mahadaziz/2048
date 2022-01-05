/**
 * @file Demo.java
 * @author Mahad Aziz - azizm17
 * @brief Class that allows the 2048 game to be played
 * @date April 12, 2021
 */
package src;

/**
 * @brief A class that allows the user to play the game
 * @details Creates instances of the Game, Display, and Controller classes and then simulates the game
 */
public class Demo {
	public static void main(String[] args) {
		Game game = new Game();
		Display display = Display.getInstance();
		Controller cont = Controller.getInstance(game, display);
		cont.runGame();
	}
}
