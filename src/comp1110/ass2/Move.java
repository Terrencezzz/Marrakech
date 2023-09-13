package comp1110.ass2;


/**
 * This class aims to deal with the movement about Assam(The customer).
 */
public class Move {

    private int movement = Die.getDieNumber();


    public Move(int movement) {
        this.movement = movement;
    }



    /**
     * Applies the transformation to Assam.
     * Base on the direction of Assam, add the number of movement.
     * If Assam hit the board edge, then turn the direction based on the board (See what's the board looks like).(7 getX 7 board)
     * @return IntPair representing the new coordinates after applying the die effect
     */
    public Assam applyTransform(Assam assamString) {
        Direction direction = assamString.getDirection();
        IntPair position = assamString.getPosition();
        int col = position.getCol();
        int row = position.getRow();
        switch (direction) {
            case NORTH -> {
                if (col - movement < 0) {
                    if (row == 6) {
                        direction = Direction.WEST;
                        col = 0;
                        row -= (movement - col - 1);
                    } else if (row % 2 == 0) {
                        direction = Direction.SOUTH;
                        col = movement - col - 1;
                        row += 1;
                    } else {
                        direction = Direction.SOUTH;
                        col = movement - col - 1;
                        row -= 1;
                    }
                } else {
                    col -= movement;
                }
            }
            case EAST -> {
                if (row + movement > 6) {
                    if (col == 0) {
                        direction = Direction.SOUTH;
                        col = (6 - movement - 1);
                        row = 6;
                    } else if (col % 2 == 0) {
                        direction = Direction.WEST;
                        col -= 1;
                        row = 13 - movement - row;
                    } else {
                        direction = Direction.WEST;
                        col += 1;
                        row = 13 - movement - row;

                    }
                } else {
                    row += movement;
                }
            }
            case SOUTH -> {
                if (col + movement > 6) {
                    if (row == 0) {
                        direction = Direction.EAST;
                        row = movement + col - 7;
                        col = 6;
                    } else if (col % 2 == 0) {
                        direction = Direction.NORTH;
                        row -= 1;
                        col = 13 - movement - col;
                    } else {
                        direction = Direction.NORTH;
                        row += 1;
                        col = 13 - movement - col;
                    }
                } else {
                    col += movement;
                }
            }
            case WEST -> {
                if (row - movement < 0) {
                    if (col == 6) {
                        direction = Direction.NORTH;
                        col = 7 + row - movement;
                        row = 0;
                    } else if (col % 2 == 0) {
                        direction = Direction.EAST;
                        col += 1;
                        row = movement - row - 1;
                    } else {
                        direction = Direction.EAST;
                        col -= 1;
                        row = movement - row - 1;
                    }
                } else {
                    row -= movement;
                }
            }
            default -> {
                return assamString;
            }
        }
        return new Assam(new IntPair(col, row), direction);
    }


    /**
     * This is a getter method for movement.
     * @return movement
     */
    public int getMovement() {
        return movement;
    }
}
