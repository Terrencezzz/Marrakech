package comp1110.ass2.myTest;
import comp1110.ass2.EnumClasses.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class DirectionTest {
    @Test
    void charToDirectionTest() {
        char c1 = 'N';
        char c2 = 'E';
        char c3 = 'S';
        char c4 = 'W';
        Assertions.assertEquals(Direction.NORTH, Direction.charToDirection(c1));
        Assertions.assertEquals(Direction.EAST, Direction.charToDirection(c2));
        Assertions.assertEquals(Direction.SOUTH, Direction.charToDirection(c3));
        Assertions.assertEquals(Direction.WEST, Direction.charToDirection(c4));
    }


    @Test
    void directionToCharTest() {
        Direction direction1 = Direction.NORTH;
        Direction direction2 = Direction.EAST;
        Direction direction3 = Direction.SOUTH;
        Direction direction4 = Direction.WEST;
        Assertions.assertEquals('N', Direction.directionToChar(direction1));
        Assertions.assertEquals('E', Direction.directionToChar(direction2));
        Assertions.assertEquals('S', Direction.directionToChar(direction3));
        Assertions.assertEquals('W', Direction.directionToChar(direction4));
    }
}
