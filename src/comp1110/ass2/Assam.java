package comp1110.ass2;


/**
 * This class aims to record the status of Assam.
 */
public class Assam {


    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }
    private IntPair position;

    private Direction direction;


    /**
     * Initialize the status of Assam to position (3,3) and direction "Down".
     */
    public Assam(){
        this.position = new IntPair(3,3);
        this.direction = Direction.DOWN;
    }

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
     * You can get the current direction of Assam by using this function.
     * @return The current direction of Assam.
     */
    public Direction getDirection(){
        return direction;
    }


    /**
     * Create a new Assam object based on the given input string.
     * @param input Input string containing Assam's position and direction.
     * @return A new Assam object.
     */
    public static Assam createStatus(String input) {
        String[] status = input.split(",");
        IntPair intPair = IntPair.createIntPair(status[0] + "," + status[1]);
        Direction dir = Direction.valueOf(status[2].trim().toUpperCase());

        return new Assam(intPair, dir);
    }
}




