package comp1110.ass2;

public class Rotation {
    private Direction direction;
    public Rotation(Direction direction) {
        this.direction = direction;
    }

    public static int applyRotation(Direction direction) {
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
}
