package comp1110.ass2.FunctionalClasses;


import comp1110.ass2.BasicClasses.Assam;
import comp1110.ass2.EnumClasses.Direction;

/**
 * This class aims to deal with the movement about Assam(The customer).
 * @author Terrence Wu
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
        int x = position.getCol();
        int y = position.getRow();
        switch (direction) {
            case NORTH -> {
                if (y - movement < 0) {
                    if (x == 6) {
                        direction = Direction.WEST;
                        x = 6 - (movement - y - 1);
                        y = 0;
                    } else if (x % 2 == 0) {
                        direction = Direction.SOUTH;
                        y = movement - y - 1;
                        x += 1;
                    } else {
                        direction = Direction.SOUTH;
                        y = movement - y - 1;
                        x -= 1;
                    }
                } else {
                    y -= movement;
                }
            }
            case EAST -> {
                if (x + movement > 6) {
                    if (y == 0) {
                        direction = Direction.SOUTH;
                        y = movement - 1 - (6 - x);
                        x = 6;
                    } else if (y % 2 == 0) {
                        direction = Direction.WEST;
                        y -= 1;
                        x = 13 - movement - x;
                    } else {
                        direction = Direction.WEST;
                        y += 1;
                        x = 13 - movement - x;

                    }
                } else {
                    x += movement;
                }
            }
            case SOUTH -> {
                if (y + movement > 6) {
                    if (x == 0) {
                        direction = Direction.EAST;
                        x = movement + y - 7;
                        y = 6;
                    } else if (x % 2 == 0) {
                        direction = Direction.NORTH;
                        x -= 1;
                        y = 13 - movement - y;
                    } else {
                        direction = Direction.NORTH;
                        x += 1;
                        y = 13 - movement - y;
                    }
                } else {
                    y += movement;
                }
            }
            case WEST -> {
                if (x - movement < 0) {
                    if (y == 6) {
                        direction = Direction.NORTH;
                        y = 7 + x - movement;
                        x = 0;
                    } else if (y % 2 == 0) {
                        direction = Direction.EAST;
                        y += 1;
                        x = movement - x - 1;
                    } else {
                        direction = Direction.EAST;
                        y -= 1;
                        x = movement - x - 1;
                    }
                } else {
                    x -= movement;
                }
            }
            default -> {
                return assamString;
            }
        }
        return new Assam(new IntPair(y, x), direction);
    }


    /**
     * This is a getter method for movement.
     * @return movement
     */
    public int getMovement() {
        return movement;
    }
}
