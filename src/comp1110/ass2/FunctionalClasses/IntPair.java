package comp1110.ass2.FunctionalClasses;


import java.util.Objects;

/**
 * This class is aimed to meet some requirements about the coordinate system.
 * @author Terrence Wu
 */
public class IntPair{
    /**
     * Constructor for IntPair, aims to combine col and row to (col , row)
     */
    private int col;
    private int row;

    public IntPair(int row, int col) {
        this.col = col;
        this.row = row;
    }


    /**
     * This is a getter method for col, if you want to use col, then use getCol().
     *
     * @return The getX-coordinate of the IntPair.
     */
    public int getCol() {
        return col;
    }


    /**
     * This is a getter method for row, if you want to use row, then use getRow().
     *
     * @return The y-coordinate of the IntPair.
     */
    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPair intPair = (IntPair) o;
        return col == intPair.col && row == intPair.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString() {
        return this.row + "" + this.col;
    }
}

