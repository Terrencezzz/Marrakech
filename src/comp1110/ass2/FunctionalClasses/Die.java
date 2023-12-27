package comp1110.ass2.FunctionalClasses;

import java.util.Random;
/**
 * This class aims to deal with the random number which is generated by the die.
 * @author Terrence Wu
 */
public class Die {
    private final static int[] FACES = {1,2,2,3,3,4};
    private final static Random random = new Random();

    /**
     * To get a random number from this die.
     * @return A random number that could come up when this die is rolled.
     */
    public static int getDieNumber() {
        int index = random.nextInt(FACES.length);
        return FACES[index];
    }
}
