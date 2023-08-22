package comp1110.ass2;

import java.util.List;
import java.util.ArrayList;
public class Board {
    private final int BOARD_SIZE = 7; // for a 7x7 board
    private final String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    public List<IntPair> bluePositionsList = new ArrayList<>();
    public List<IntPair> redPositionsList = new ArrayList<>();
    public List<IntPair> yellowPositionsList = new ArrayList<>();
    public List<IntPair> brownPositionsList = new ArrayList<>();


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

        updateColorPositions(x, y, rugColor);
    }

    /**
     * This is a helper function for updating the status of the board.
     * Each time a new rug is placed on the board, this function will be called.
     * @param x
     * @param y
     * @param rugColor
     */
    private void updateColorPositions(int x, int y, String rugColor) {
        IntPair pair = new IntPair(x, y);

        switch (rugColor) {
            case "Blue" -> bluePositionsList.add(pair);
            case "Red" -> redPositionsList.add(pair);
            case "Yellow" -> yellowPositionsList.add(pair);
            case "Brown" -> brownPositionsList.add(pair);
            default -> {
            }
        }
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
