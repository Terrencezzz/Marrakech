package comp1110.ass2;


/**
 * This class aims to record the status of Assam.
 */
public class Assam {
    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private IntPair position;

    private Direction direction;


    /**
     * Initialize the status of Assam to position (3,3) and direction Direction.DOWN.
     */
    public Assam(){
        this.position = new IntPair(3,3);
        this.direction = Direction.DOWN;
    }
    /**
     * Initialize Assam with the given position and direction.
     * @param position  The initial position of Assam.
     * @param direction The initial direction of Assam.
     */
    public Assam(IntPair position, Direction direction){
        this.position = position;
        this.direction = direction;
    }


    /**
     * You can get the current position of Assam by using this function.
     * @return The current position of Assam as an IntPair.
     */
    public IntPair getPosition(){
        return position;
    }

    /**
     * Set Assam's new position.
     * @param newPosition The new position as an IntPair object.
     */
    public void setPosition(IntPair newPosition) {
        this.position = newPosition;
    }

    /**
     * You can get the current direction of Assam by using this function.
     * @return The current direction of Assam as a Direction enum.
     */
    public Direction getDirection(){
        return direction;
    }
    /**
     * Set Assam's new direction.
     * @param newDirection The new direction as a Direction enum.
     */
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    /**
     * Create a new Assam object based on the given input string.
     * @param input Input string containing Assam's position and direction.
     * @return A new Assam object.
     */
    public static Assam createStatus(String input) {
        String[] status = input.split(",");
        IntPair intPair = IntPair.createIntPair(status[0] + "," + status[1]);
        Direction direction = Direction.valueOf(status[2].trim().toUpperCase());
        return new Assam(intPair, direction);
    }
    /**
     * Move Assam based on the current direction and given steps.
     * @param steps number of steps to move
     */

    //Move Assam
    public void move(int steps) {
        int newX = position.getX();
        int newY = position.getY();

        switch (direction) {
            case UP:
                newY -= steps;
                break;
            case DOWN:
                newY += steps;
                break;
            case LEFT:
                newX -= steps;
                break;
            case RIGHT:
                newX += steps;
                break;
        }

        this.position = new IntPair(newX, newY);
    }

}



