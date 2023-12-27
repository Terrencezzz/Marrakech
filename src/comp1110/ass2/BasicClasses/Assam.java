package comp1110.ass2.BasicClasses;


import comp1110.ass2.EnumClasses.Direction;
import comp1110.ass2.FunctionalClasses.IntPair;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to record the status of Assam.
 * @author Terrence Wu
 */
public class Assam {

    private IntPair position;

    private Direction direction;


    /**
     * Initialize the status of Assam to position (3,3) and direction "Down".
     */

    public Assam(IntPair position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }


    public Assam(String assamString) {
        if (assamString.charAt(0) == 'A') {
            int col = Integer.parseInt(String.valueOf(assamString.charAt(1)));
            int row = Integer.parseInt(String.valueOf(assamString.charAt(2)));
            this.position = new IntPair(row, col);
            this.direction = Direction.charToDirection(assamString.charAt(3));
        }
    }


    /**
     * You can get the current position of Assam by using this function.
     *
     * @return The current position of Assam as an IntPair.
     */
    public IntPair getPosition() {
        return position;
    }


    /**
     * You can get the current direction of Assam by using this function.
     *
     * @return The current direction of Assam.
     */
    public Direction getDirection() {
        return direction;
    }

    public static List<IntPair> besidePositions(Assam assam) {
        List<IntPair> positions = new ArrayList<>();
        int row = assam.getPosition().getRow();
        int col = assam.getPosition().getCol();

        for (int i = -1; i <= 1; i++) {
            if (col + i >= 0 && col + i <= 6 && i != 0) {
                IntPair newPosition = new IntPair(row, col + i);
                positions.add(newPosition);
            }
        }

        for (int i = -1; i <= 1; i++) {
            if (row + i >= 0 && row + i <= 6) {
                IntPair newPosition = new IntPair(row + i, col);
                positions.add(newPosition);
            }
        }
        return positions;
    }


    @Override
    public String toString() {
        return "A" + position.getCol() + position.getRow() + Direction.directionToChar(direction);
    }
}