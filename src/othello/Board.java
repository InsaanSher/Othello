package othello;

public class Board {
    private Square[][] board;

    public Board() {
        board = new Square[8][8];
        // Initialize the board with empty squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }
        // Set up the initial four counters in the center
        board[3][3].setOwner("dark");
        board[3][4].setOwner("light");
        board[4][3].setOwner("light");
        board[4][4].setOwner("dark");
    }

    public boolean isValidMove(int row, int col, String playerColor) {
        // Check if the square is already occupied
        if (!board[row][col].getOwner().equals("empty")) {
            return false;
        }

        // Check for sandwiching in all eight directions
        boolean valid = false;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue; // Skip checking the current square
                valid |= checkDirection(row, col, dx, dy, playerColor);
            }
        }
        return valid;
    }

    private boolean checkDirection(int row, int col, int dx, int dy, String playerColor) {
        int x = row + dx;
        int y = col + dy;
        boolean foundOpposite = false;

        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            String currentOwner = board[x][y].getOwner();
            if (currentOwner.equals("empty")) return false;
            if (currentOwner.equals(playerColor)) {
                return foundOpposite;
            }
            foundOpposite = true;
            x += dx;
            y += dy;
        }
        return false;
    }

    public void makeMove(int row, int col, String playerColor) throws FailToPlaceException {
        if (!isValidMove(row, col, playerColor)) {
            throw new FailToPlaceException("Invalid move.");
        }

        board[row][col].setOwner(playerColor);
        // Flip the sandwiched counters in all valid directions
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                if (checkDirection(row, col, dx, dy, playerColor)) {
                    flipCounters(row, col, dx, dy, playerColor);
                }
            }
        }
    }

    private void flipCounters(int row, int col, int dx, int dy, String playerColor) {
        int x = row + dx;
        int y = col + dy;

        while (x >= 0 && x < 8 && y >= 0 && y < 8 && !board[x][y].getOwner().equals(playerColor)) {
            board[x][y].setOwner(playerColor);
            x += dx;
            y += dy;
        }
    }

    public boolean canPlayerMove(String playerColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(i, j, playerColor)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return !canPlayerMove("light") && !canPlayerMove("dark");
    }

    public int[] getScore() {
        int lightCount = 0;
        int darkCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getOwner().equals("light")) {
                    lightCount++;
                } else if (board[i][j].getOwner().equals("dark")) {
                    darkCount++;
                }
            }
        }
        return new int[]{lightCount, darkCount};
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String owner = board[i][j].getOwner();
                String displayChar = owner.equals("empty") ? "." : owner.substring(0, 1).toUpperCase();
                System.out.print(displayChar + " ");
            }
            System.out.println();
        }
    }

    // Helper method to get the opposite color
    private String oppositeColor(String color) {
        return color.equals("light") ? "dark" : "light";
    }
}

