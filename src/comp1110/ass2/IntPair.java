package comp1110.ass2;


/**
 * This class is aimed to meet some requirements about the coordinate system.
 */
public class IntPair{
    /**
     * Constructor for IntPair, aims to combine getX and y to (getX , y)
     */
    private int x;
    private int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * This is a getter method for getX, if you want to use getX, then use getX().
     *
     * @return The getX-coordinate of the IntPair.
     */
    public int getX() {
        return x;
    }


    /**
     * This is a getter method for y, if you want to use y, then use getY().
     *
     * @return The y-coordinate of the IntPair.
     */
    public int getY() {
        return y;
    }


    /**
     * To combine getX and y as an IntPair.
     */
    public static IntPair createIntPair(String input) {
        String[] intPair = input.split(",");
        int x = Integer.parseInt(intPair[0]);
        int y = Integer.parseInt(intPair[1]);
        return new IntPair(x, y);
    }

}

