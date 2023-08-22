package comp1110.ass2;

import java.util.List;
import java.util.ArrayList;
public class Board {
    private static final int BOARD_SIZE = 7;
    private final String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    /**
     * Places a rug on the board with specified coordinates and color.
     *
     * @param startX The X-coordinate of the starting point of the rug.
     * @param startY The Y-coordinate of the starting point of the rug.
     * @param endX The X-coordinate of the ending point of the rug.
     * @param endY The Y-coordinate of the ending point of the rug.
     * @param rugColor The color of the rug to be placed.
     * @throws IllegalArgumentException If the provided coordinates are invalid for placing the rug.
     */
    public void placeRug(int startX, int startY, int endX, int endY, String rugColor) {
        if (startX >= 0 && startY >= 0 && endX < BOARD_SIZE && endY < BOARD_SIZE) {
            board[startX][startY] = rugColor;
            board[endX][endY] = rugColor;
        } else {
            throw new IllegalArgumentException("Invalid coordinates for placing rug.");
        }
    }

    /**
     * Initializes the board and places the first rug at the specified position.
     *
     * @param position The initial position (coordinates) for placing the first rug.
     * @param rugColor The color of the rug to be placed.
     * @throws IllegalArgumentException If the provided initial position is outside the board boundaries.
     */
    public Board(IntPair position, String rugColor) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null;
            }
        }

        if (position.getX() >= 0 && position.getY() >= 0 && position.getX() < BOARD_SIZE && position.getY() < BOARD_SIZE) {
            // If the initial position is valid, place the rug
            int x = position.getX();
            int y = position.getY();
            board[x][y] = rugColor;
            //If the initial position is invalid, throw an exception
        } else {
            throw new IllegalArgumentException("Invalid coordinates for placing rug.");
        }
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
     * @return A string representing the board, with each cell as "((x,y),Value)".
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
