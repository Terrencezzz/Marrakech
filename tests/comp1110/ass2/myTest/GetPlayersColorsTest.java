package comp1110.ass2.myTest;

import comp1110.ass2.EnumClasses.Colour;
import comp1110.ass2.BasicClasses.GameStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class GetPlayersColorsTest {
    @Test
    void getPlayerTest() {
        GameStatus gameString1 = new GameStatus("Pc02709iPy03109iPp02309iPr03910iA66S" +
                "Bn00y04n00n00n00n00n00c03y04r02p06r05r05c04c03r00r00p06r08r03r03p02p02y08c11c11n00n00r04n00n00n00n00n00n00r04y02p08n00n00p10p10c07c07p08y05y05r09c10");
        GameStatus gameString2 = new GameStatus("Pc00000oPy00000oPp01400iPr10600iA46E" +
                "Br06r13r13r22r22c19r18c20y20r03p13p17c19n00c10y20n00r26n00r32r32c10p24p24r26p26p33r21y27y30y30p29r20p33p32y27c02p05p29r28r08p32r24r30r30p09r28n00r31");
        GameStatus gameString3 = new GameStatus("Pc00000oPy07100iPp04400iPr00500iA40E" +
                "Bp33p33c08n00r31y05p24r33r33y24r32r32r30p34r00y21p19p19n00p32p34r00y21r14y16y16r35r21y40y08y14y26r34r35n00y40y12n00r20r22p22n00n00n00c20c20y25y25y11");

        List<Colour> expectedColors1 = Arrays.asList(Colour.CYAN, Colour.YELLOW, Colour.PURPLE, Colour.RED);
        List<Colour> expectedColors2 = Arrays.asList(Colour.CYAN,Colour.YELLOW,Colour.PURPLE,Colour.RED);
        List<Colour> expectedColors3 = Arrays.asList(Colour.CYAN,Colour.YELLOW,Colour.PURPLE,Colour.RED);

        Assertions.assertEquals(expectedColors1, gameString1.getPlayersColors());
        Assertions.assertEquals(expectedColors2, gameString2.getPlayersColors());
        Assertions.assertEquals(expectedColors3, gameString3.getPlayersColors());
    }
}
