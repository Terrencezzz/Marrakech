package comp1110.ass2.myTest;
import comp1110.ass2.EnumClasses.Direction;
import comp1110.ass2.FunctionalClasses.Rotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class RotationTest {
    @Test
    void intToDirectionTest() {
        int n1 = 0;
        int n2 = 90;
        int n3 = 180;
        int n4 = 270;
        Assertions.assertEquals(Direction.NORTH, Rotation.intToDirection(n1));
        Assertions.assertEquals(Direction.EAST, Rotation.intToDirection(n2));
        Assertions.assertEquals(Direction.SOUTH, Rotation.intToDirection(n3));
        Assertions.assertEquals(Direction.WEST, Rotation.intToDirection(n4));
    }


    @Test
    void directionToIntTest() {
        Direction direction1 = Direction.NORTH;
        Direction direction2 = Direction.EAST;
        Direction direction3 = Direction.SOUTH;
        Direction direction4 = Direction.WEST;
        Assertions.assertEquals(0, Rotation.directionToInt(direction1));
        Assertions.assertEquals(90, Rotation.directionToInt(direction2));
        Assertions.assertEquals(180, Rotation.directionToInt(direction3));
        Assertions.assertEquals(270, Rotation.directionToInt(direction4));
    }
}
