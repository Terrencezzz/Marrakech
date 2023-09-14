package comp1110.ass2;


/**
 * This class is aimed to meet some requirements about the coordinate system.
 */
public class IntPair{
    /**
     * Constructor for IntPair, aims to combine getX and y to (getX , y)
     */
    private int col;
    private int row;

    public IntPair(int row, int col) {
        this.col = col;
        this.row = row;
    }


    /**
     * This is a getter method for getX, if you want to use getX, then use getX().
     *
     * @return The getX-coordinate of the IntPair.
     */
    public int getCol() {
        return col;
    }


    /**
     * This is a getter method for y, if you want to use y, then use getY().
     *
     * @return The y-coordinate of the IntPair.
     */
    public int getRow() {
        return row;
    }
}

