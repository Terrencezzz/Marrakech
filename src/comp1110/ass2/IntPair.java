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
     * To combine x and y as an IntPair.
     * @param input
     * @return
     */
    public static IntPair createIntPair(String input){
        String[] intPair = input.split(",");
        int x = Integer.parseInt(intPair[0]);
        int y = Integer.parseInt(intPair[1]);
        return new IntPair(x,y);
    }

}

