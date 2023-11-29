package othello;

public class Player {
    private String color; // "light" or "dark"
    private int score;    // The score of the player

    public Player(String color) {
        this.color = color;
        this.score = 2; // Initial score (each player starts with 2 pieces on the board)
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Update the score based on the current state of the board
    public void updateScore(Board board) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSquare(i, j).getOwner().equals(color)) {
                    count++;
                }
            }
        }
        this.score = count;
    }

    // Method for making a move
    public void makeMove(Board board, int row, int col) throws FailToPlaceException {
        if (board.isValidMove(row, col, this.color)) {
            board.makeMove(row, col, this.color);
            this.updateScore(board); // Update the score after making a move
        } else {
            throw new FailToPlaceException("Invalid move at (" + row + ", " + col + ")");
        }
    }
}
