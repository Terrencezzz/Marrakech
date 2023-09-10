package comp1110.ass2;

public class Rotation {
    private int rotation;
    public Rotation(int rotation) {
        this.rotation = rotation;
    }

    public Direction applyRotation(int angle) {
        int newAngle = angle + (360 + this.rotation) % 360;
        switch (newAngle) {
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
}
