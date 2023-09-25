package comp1110.ass2;


/**
 * This class aims to record the status of Assam.
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


    @Override
    public String toString() {
        return "A" + position.getCol() + position.getRow() + Direction.directionToChar(direction);
    }

}