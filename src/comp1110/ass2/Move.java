package comp1110.ass2;


/**
 * This class aims to deal with the movement about Assam(The customer).
 */
public class Move {

    private final IntPair translation;

    private final String direction;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }


    public Move(IntPair translation, String direction){
        this.translation = translation;
        this.direction = direction;
    }

    /**
     * Applies the transformation to the customer.(This one is not done yet!!!)
     * It will combine translation and direction.
     * @return
     */
    public IntPair applyTransform(){
        return translation;
    }


    /**
     * To check which direction does the customer faces.(This is not done yet!!!)
     * @return
     */
    public String checkDirection(){
        return direction;
    }



}
