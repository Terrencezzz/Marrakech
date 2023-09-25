package comp1110.ass2.myTest;

import comp1110.ass2.Assam;
import comp1110.ass2.Colour;
import comp1110.ass2.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class ApplyTransformTest {
    @Test
    void applyTransformTest() {
        Assam assam1 = new Assam("A14S");
        Move move1 = new Move(3);
        String result1 = move1.applyTransform(assam1).toString();
        Assertions.assertEquals("A26N", result1);

    }
}
