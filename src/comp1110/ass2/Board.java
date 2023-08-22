package comp1110.ass2;

public class Board {
    private final int BOARD_SIZE = 7; // for a 7x7 board
    private final String[][] board = new String[BOARD_SIZE][BOARD_SIZE];


    public Board(IntPair position, String rugColor) {
        // Initialize the board with all positions set to null
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null;
            }
        }

        // Place the rug at the specified position
        int x = position.getX();
        int y = position.getY();
        board[x][y] = rugColor;
    }




    /**
     * Make the board looks like: ((x,y),Null).
     * @return
     */
    @Override
    public String toString() {
        StringBuilder boardStatus = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boardStatus.append("(").append(i).append(",").append(j).append("),");
                boardStatus.append(board[i][j] == null ? "Null" : board[i][j]).append("\n");
            }
        }
        return boardStatus.toString();
    }

}
