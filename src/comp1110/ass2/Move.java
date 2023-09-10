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
     * If direction is North or South, y + movement or y - movement.
     * If direction is East or West, getX + movement or y + movement.
     * If Assam hit the board edge, then turn the direction based on the board (See what's the board looks like).(7 getX 7 board)
     * @return IntPair representing the new coordinates after applying the die effect
     */
    public Assam applyTransform(Assam assamString) {
        Direction direction = assamString.getDirection();
        IntPair position = assamString.getPosition();
        int x = position.getX();
        int y = position.getY();
        switch (direction) {
            case NORTH:
                if (x - movement < 0) {
                    if (y == 6) {
                        direction = Direction.WEST;
                        x = 0;
                        y -= (movement - x - 1);
                    }
                    else if (y % 2 == 0) {
                        direction = Direction.SOUTH;
                        x = movement - x - 1;
                        y += 1;
                    }
                    else {
                        direction = Direction.SOUTH;
                        x = movement - x - 1;
                        y -= 1;
                    }
                }
                else {
                    x -= movement;
                }
                break;
            case EAST:
                if (y + movement > 6) {
                    if (x == 0) {
                        direction = Direction.SOUTH;
                        x = (6 - movement - 1);
                        y = 6;
                    }
                    else if (x % 2 == 0) {
                        direction = Direction.WEST;
                        x -= 1;
                        y = 13 - movement - y;
                    }
                    else {
                        direction = Direction.WEST;
                        x += 1;
                        y = 13 - movement - y;

                    }
                }
                else {
                    y += movement;
                }
                break;
            case SOUTH:
                if (x + movement > 6) {
                    if (y == 0) {
                        direction = Direction.EAST;
                        y = movement + x - 7;
                        x = 6;
                    }
                    else if (x % 2 == 0) {
                        direction = Direction.NORTH;
                        y -= 1;
                        x = 13 - movement - x;
                    }
                    else {
                        direction = Direction.NORTH;
                        y += 1;
                        x = 13 - movement - x;
                    }
                }
                else {
                    x += movement;
                }
                break;
            case WEST:
                if (y - movement < 0) {
                    if (x == 6){
                        direction = Direction.NORTH;
                        x = 7 + y - movement;
                        y = 0;
                    }
                    else if (x % 2 == 0) {
                        direction = Direction.EAST;
                        x += 1;
                        y = movement - y - 1;
                    }
                    else {
                        direction = Direction.EAST;
                        x -= 1;
                        y = movement - y - 1;
                    }
                }
                else {
                    y -= movement;
                }
                break;
            default: return assamString;
        }
        return new Assam(new IntPair(x, y), direction);
    }


    /**
     * This is a getter method for movement.
     * @return movement
     */
    public int getMovement() {
        return movement;
    }
}
