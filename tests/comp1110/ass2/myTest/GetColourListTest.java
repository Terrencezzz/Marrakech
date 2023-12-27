package comp1110.ass2.myTest;

import comp1110.ass2.BasicClasses.Board;
import comp1110.ass2.BasicClasses.GameStatus;
import comp1110.ass2.EnumClasses.Colour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class GetColourListTest {
    @Test
    void getColourListTest() {
        String gameString1 = "Pc02709iPy03109iPp02309iPr03910iA66SBn00y04n00n00n00n00n00c03y04r02p06r05r05c04c03r00r00p06r08r03r03p02p02y08c11c11n00n00r04n00n00n00n00n00n00r04y02p08n00n00p10p10c07c07p08y05y05r09c10";
        String gameString2 = "Pc00000oPy00000oPp01400iPr10600iA46EBr06r13r13r22r22c19r18c20y20r03p13p17c19n00c10y20n00r26n00r32r32c10p24p24r26p26p33r21y27y30y30p29r20p33p32y27c02p05p29r28r08p32r24r30r30p09r28n00r31";
        String gameString3 = "Pc00000oPy07100iPp04400iPr00500iA40EBp33p33c08n00r31y05p24r33r33y24r32r32r30p34r00y21p19p19n00p32p34r00y21r14y16y16r35r21y40y08y14y26r34r35n00y40y12n00r20r22p22n00n00n00c20c20y25y25y11";

        List<Colour> expectList1 = Arrays.asList(Colour.GREY, Colour.YELLOW, Colour.GREY, Colour.GREY, Colour.GREY,
                Colour.GREY, Colour.GREY, Colour.CYAN, Colour.YELLOW, Colour.RED, Colour.PURPLE, Colour.RED, Colour.RED,
                Colour.CYAN, Colour.CYAN, Colour.RED, Colour.RED, Colour.PURPLE, Colour.RED, Colour.RED, Colour.RED,
                Colour.PURPLE, Colour.PURPLE, Colour.YELLOW, Colour.CYAN, Colour.CYAN, Colour.GREY, Colour.GREY,
                Colour.RED, Colour.GREY, Colour.GREY, Colour.GREY, Colour.GREY, Colour.GREY, Colour.GREY,
                Colour.RED, Colour.YELLOW, Colour.PURPLE, Colour.GREY, Colour.GREY, Colour.PURPLE, Colour.PURPLE, Colour.CYAN,
                Colour.CYAN, Colour.PURPLE, Colour.YELLOW, Colour.YELLOW, Colour.RED, Colour.CYAN);

        List<Colour> expectList2 = Arrays.asList(Colour.RED, Colour.RED, Colour.RED, Colour.RED, Colour.RED, Colour.CYAN,
                Colour.RED, Colour.CYAN, Colour.YELLOW, Colour.RED, Colour.PURPLE, Colour.PURPLE, Colour.CYAN, Colour.GREY,
                Colour.CYAN, Colour.YELLOW, Colour.GREY, Colour.RED, Colour.GREY, Colour.RED, Colour.RED, Colour.CYAN, Colour.PURPLE,
                Colour.PURPLE, Colour.RED, Colour.PURPLE, Colour.PURPLE, Colour.RED, Colour.YELLOW, Colour.YELLOW,
                Colour.YELLOW, Colour.PURPLE, Colour.RED, Colour.PURPLE, Colour.PURPLE, Colour.YELLOW, Colour.CYAN,
                Colour.PURPLE, Colour.PURPLE, Colour.RED, Colour.RED, Colour.PURPLE, Colour.RED, Colour.RED, Colour.RED,
                Colour.PURPLE, Colour.RED, Colour.GREY, Colour.RED);

        List<Colour> expectList3 = Arrays.asList(Colour.PURPLE, Colour.PURPLE, Colour.CYAN, Colour.GREY, Colour.RED,
                Colour.YELLOW, Colour.PURPLE, Colour.RED, Colour.RED, Colour.YELLOW, Colour.RED, Colour.RED, Colour.RED,
                Colour.PURPLE, Colour.RED, Colour.YELLOW, Colour.PURPLE, Colour.PURPLE, Colour.GREY, Colour.PURPLE, Colour.PURPLE,
                Colour.RED, Colour.YELLOW, Colour.RED, Colour.YELLOW, Colour.YELLOW, Colour.RED, Colour.RED, Colour.YELLOW,
                Colour.YELLOW, Colour.YELLOW, Colour.YELLOW, Colour.RED, Colour.RED, Colour.GREY, Colour.YELLOW, Colour.YELLOW,
                Colour.GREY, Colour.RED, Colour.RED, Colour.PURPLE, Colour.GREY, Colour.GREY, Colour.GREY, Colour.CYAN, Colour.CYAN, Colour.YELLOW,
                Colour.YELLOW, Colour.YELLOW);


        GameStatus g1 = new GameStatus(gameString1);
        GameStatus g2 = new GameStatus(gameString2);
        GameStatus g3 = new GameStatus(gameString3);

        Assertions.assertEquals(expectList1, Board.getColourList(g1.toString()));
        Assertions.assertEquals(expectList2, Board.getColourList(g2.toString()));
        Assertions.assertEquals(expectList3, Board.getColourList(g3.toString()));
    }
}
