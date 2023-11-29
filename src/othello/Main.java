package othello;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameController controller = new GameController();
        Scanner scanner = new Scanner(System.in);

        // Start the game
        controller.startGame();

        // Game loop
        while (!controller.isGameOver()) {
            try {
                // Display the board
                controller.displayBoard();

                // Get player input for the next move
                System.out.println("Current Player: " + controller.getCurrentPlayerColor());
                System.out.print("Enter row and column (e.g., '4 5'): ");
                int row = scanner.nextInt() - 1; // Adjusting for 0-based indexing
                int col = scanner.nextInt() - 1; // Adjusting for 0-based indexing

                // Make the move
                controller.makeMove(row, col);

            } catch (FailToPlaceException e) {
                System.out.println(e.getMessage());
                // Handle invalid move
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the buffer
            }
        }

        // End of the game
        controller.displayBoard();
        System.out.println("Game Over. " + controller.getWinner() + " wins!");
        scanner.close();
    }
}
