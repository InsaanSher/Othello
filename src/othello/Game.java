package othello;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        player1 = new Player("light");
        player2 = new Player("dark");
        currentPlayer = player1; // or decide randomly
    }

    public void playGame() {
        while (!isGameOver()) {
            displayBoard();
            makeMove();
            switchPlayer();
        }
        declareWinner();
    }

    private boolean isGameOver() {
        return !board.canPlayerMove(player1.getColor()) && !board.canPlayerMove(player2.getColor());
    }

    private void makeMove() {
        // Implement logic to get player's move (row, col) and validate it
        // This method should interact with the user or an AI to get the move
    }

    public void displayBoard() {
        board.displayBoard();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public String getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    public void makeMove(int row, int col) throws FailToPlaceException {
        if (board.isValidMove(row, col, currentPlayer.getColor())) {
            board.makeMove(row, col, currentPlayer.getColor());
            currentPlayer.updateScore(board);
        } else {
            throw new FailToPlaceException("Invalid move.");
        }
    }

    private void declareWinner() {
        player1.updateScore(board);
        player2.updateScore(board);
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Player 1 (Light) wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Player 2 (Dark) wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Additional methods as needed
}

