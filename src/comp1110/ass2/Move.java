package comp1110.ass2;


/**
 * This class aims to deal with the movement about Assam(The customer).
 */
public class Move {

    /**
     * Applies the transformation to Assam.
     * It will use the direction of Assam to move him.
     * @param assam Assam object representing the current state.
     * @param steps The number of steps to move.
     * @throws IllegalArgumentException if Assam or steps are not valid.
     */
    public static void applyTransform(Assam assam, int steps) {
        // Add check for assam move
        if (assam == null) {
            throw new IllegalArgumentException("Assam cannot be null.");
        }
        if (steps < 0) {
            throw new IllegalArgumentException("Steps cannot be negative.");
        }

        IntPair newPosition = null;
        switch (assam.getDirection()) {
            case UP:
                newPosition = new IntPair(assam.getPosition().getX(), assam.getPosition().getY() - steps);
                break;
            case DOWN:
                newPosition = new IntPair(assam.getPosition().getX(), assam.getPosition().getY() + steps);
                break;
            case LEFT:
                newPosition = new IntPair(assam.getPosition().getX() - steps, assam.getPosition().getY());
                break;
            case RIGHT:
                newPosition = new IntPair(assam.getPosition().getX() + steps, assam.getPosition().getY());
                break;
        }
        assam.setPosition(newPosition);
    }

    /**
     * To check which direction does Assam face.
     * @param assam Assam object representing the current state.
     * @return Direction in which Assam is currently facing.
     * @throws IllegalArgumentException if Assam is null.
     */
    public static Assam.Direction checkDirection(Assam assam) {
        //Add check for null
        if (assam == null) {
            throw new IllegalArgumentException("Assam cannot be null.");
        }
        return assam.getDirection();
    }
}
