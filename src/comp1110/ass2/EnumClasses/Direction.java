package comp1110.ass2.EnumClasses;


/**
 * @author Terrence Wu
 */
public enum Direction {
    NORTH(0,'N'),
    EAST(90,'E'),
    SOUTH(180,'S'),
    WEST(270,'W');


    private char dChar;
    private int angle;



    Direction(int angle, char dChar) {
        this.dChar = dChar;
        this.angle = angle;
    }

     public static Direction charToDirection(char dChar) {
         return switch (dChar) {
             case 'E' -> Direction.EAST;
             case 'S' -> Direction.SOUTH;
             case 'W' -> Direction.WEST;
             case 'N' -> Direction.NORTH;
             default -> null;
         };
    }

    public static char directionToChar (Direction direction) {
        return switch (direction) {
            case EAST -> 'E';
            case NORTH -> 'N';
            case WEST -> 'W';
            case SOUTH -> 'S';
        };
    }

}
