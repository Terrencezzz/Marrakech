package comp1110.ass2;


/**
 * This class aims to deal with the movement about Assam(The customer).
 */
public class Move {

    private IntPair translation;
    private String direction;
    private final Die die;  // Added Die object

    private final String direction1 = "Down";
    private final String direction2 = "Up";
    private final String direction3 = "Right";
    private final String direction4 = "Left";
    public String direction;


    public Move(IntPair translation, String direction, Die die) {
        this.translation = translation;
        this.direction = direction;
        this.die = die;
    }

    /**
     * Applies the transformation to the customer.(This one is not done yet!!!)
     * It will combine translation and direction.
     *
     * @return
     */
    public IntPair applyTransform() {
        return translation;
    }

    /**
     * To check which direction does the customer faces.(This is not done yet!!!)
     *
     * @return
     */
    public String checkDirection() {
        return direction;
    }
}
