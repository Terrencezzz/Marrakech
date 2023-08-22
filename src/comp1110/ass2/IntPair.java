package comp1110.ass2;


/**
 *This class is aimed to meet some requirements about the coordinate system.
 */
public class IntPair {
    private final int x;
    private final int y;



    /**
     *Constructor for IntPair, aims to combine x and y to (x , y)
     * @param x
     * @param y
     */
    public IntPair(int x, int y){
        this.x = x;
        this.y = y;
    }


    /**
     * This is a getter method for x, if you want to use x, then use getX().
     * @return The x-coordinate of the IntPair.
     */
    public int getX(){
        return x;
    }


    /**
     * This is a getter method for y, if you want to use y, then use getY().
      * @return The y-coordinate of the IntPair.
     */
    public int getY(){
        return y;
    }


    /**
     * To create an IntPair object from a string containing the x and y coordinates.
     * The string input should be in the format "x,y".
     * @param input The string containing the x and y coordinates, separated by a comma.
     * @return A new IntPair object with the parsed coordinates.
     * @throws IllegalArgumentException if the input is null, empty, or incorrectly formatted.
     */
    public static IntPair createIntPair(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        String[] intPair = input.split(",");

        if (intPair.length != 2) {
            throw new IllegalArgumentException("Input must contain exactly two coordinates separated by a comma.");
        }

        try {
            int x = Integer.parseInt(intPair[0]);
            int y = Integer.parseInt(intPair[1]);
            return new IntPair(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coordinates must be integers.", e);
        }
    }
}

