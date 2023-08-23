package comp1110.ass2;


/**
 * This class aims to deal with the movement about Assam(The customer).
 */
public class Move {

    private IntPair translation;
    private String direction;
    private final Die die;

    private final String direction1 = "Down";
    private final String direction2 = "Up";
    private final String direction3 = "Right";
    private final String direction4 = "Left";



    public Move(IntPair translation, String direction, Die die) {
        this.translation = translation;
        this.direction = direction;
        this.die = die;
    }

    /**
     * Applies the transformation to the customer.(This one is not done yet!!!)
     * It will combine translation and direction.

     * @return A new position after applying the transform
     */
    public IntPair applyTransform() {
        int dieEffect = die.getDieNumber();
        return new IntPair(translation.getX() + dieEffect, translation.getY() + dieEffect);
}
    /**
     * To check which direction does the customer faces.(This is not done yet!!!)
     * @return
     */
    public String checkDirection() {

        int dieRoll = die.getDieNumber();
        if (dieRoll % 4 == 0) {
            direction = direction1;
        } else if (dieRoll % 4 == 1) {
            direction = direction2;
        } else if (dieRoll % 4 == 2) {
            direction = direction3;
        } else {
            direction = direction4;
        }
        return direction;
    }
}
