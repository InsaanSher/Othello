package othello;

public class GameController {
    private Game game;

    public GameController() {
        game = new Game();
    }

    public void startGame() {
        game.playGame();
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public void displayBoard() {
        game.displayBoard();
    }

    public String getCurrentPlayerColor() {
        return game.getCurrentPlayerColor();
    }

    public void makeMove(int row, int col) throws FailToPlaceException {
        game.makeMove(row, col);
    }

    public String getWinner() {
        return game.getWinner();
    }

    // ... other delegating methods as needed
}
