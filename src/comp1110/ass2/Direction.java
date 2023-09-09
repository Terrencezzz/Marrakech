package comp1110.ass2;

import javax.swing.text.DefaultEditorKit;

public enum Direction {

    EAST(90,'E'),
    WEST(270,'W'),
    SOUTH(180,'S'),
    NORTH(0,'N');

    private final char dChar;
    private final int angle;

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
}
