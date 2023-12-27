package comp1110.ass2.myTest;

import comp1110.ass2.EnumClasses.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class ColourTest {
    @Test
    void colourToCharTest() {
        Colour colour1 = Colour.PURPLE;
        Colour colour2 = Colour.CYAN;
        Colour colour3 = Colour.YELLOW;
        Colour colour4 = Colour.RED;
        Assertions.assertEquals('p', Colour.colourToChar(colour1));
        Assertions.assertEquals('c', Colour.colourToChar(colour2));
        Assertions.assertEquals('y', Colour.colourToChar(colour3));
        Assertions.assertEquals('r', Colour.colourToChar(colour4));
    }

    @Test
    void charToColourTest() {
        char c1 = 'p';
        char c2 = 'c';
        char c3 = 'y';
        char c4 = 'r';
        Assertions.assertEquals(Colour.PURPLE, Colour.charToColour(c1));
        Assertions.assertEquals(Colour.CYAN, Colour.charToColour(c2));
        Assertions.assertEquals(Colour.YELLOW, Colour.charToColour(c3));
        Assertions.assertEquals(Colour.RED, Colour.charToColour(c4));
    }

}
