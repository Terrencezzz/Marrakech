package comp1110.ass2;

public class Board {
    private static final int ROWS = 7;
    private static final int COLUMNS = 7;
    private String[][] board;

    public Board() {
        board = new String[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = "n00"; // Initialize each square with the special null string
            }
        }
    }

    // Set a rug on a given position
    public void setRug(int x, int y, String rugString) {
        board[x][y] = rugString;
    }
}

