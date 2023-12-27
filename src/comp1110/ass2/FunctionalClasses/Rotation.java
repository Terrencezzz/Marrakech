package comp1110.ass2.FunctionalClasses;

import comp1110.ass2.BasicClasses.Assam;
import comp1110.ass2.EnumClasses.Direction;


/**
 * @author Terrence Wu
 */
public class Rotation {
    private Direction direction;
    public Rotation(Direction direction) {
        this.direction = direction;
    }

    public static int directionToInt(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return 0;
            }
            case EAST -> {
                return 90;
            }
            case SOUTH -> {
                return 180;
            }
            case WEST -> {
                return 270;
            }
        }
        return 0;
    }

    public static Direction intToDirection(int n) {
        switch (n) {
            case 0 -> {
                return Direction.NORTH;
            }
            case 90 -> {
                return Direction.EAST;
            }
            case 180 -> {
                return Direction.SOUTH;
            }
            case 270 -> {
                return Direction.WEST;
            }
        }
        return null;
    }

    public static Assam applyRotation(Assam assam, int n) {
        IntPair position = assam.getPosition();
        Direction direction = assam.getDirection();

        int originalInt = directionToInt(direction);
        int resultInt = (originalInt + n) % 360;
        Direction newDirection = intToDirection(resultInt);

        if (newDirection == null || n == 180) {
            return assam;
        }
        else {
            return new Assam(position,newDirection);
        }
    }
}
