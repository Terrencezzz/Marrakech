package comp1110.ass2.BasicClassess;

import comp1110.ass2.EnumClassess.Colour;
import comp1110.ass2.Rug;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int ROWS = 7;
    private static final int COLUMNS = 7;
    private List<String> rugsIdentifier = new ArrayList<>();

    Board (List<String> rugs) {
        this.rugsIdentifier = rugs;
    }

    Board(String boardString) {
        if (boardString.length() == 49 * 3) {
            for (int i = 0; i < 49; i++) {
                String rugIdentifier = boardString.substring(i * 3, i * 3 + 3);
                rugsIdentifier.add(rugIdentifier);
            }
        }
    }


    public List<String> getRugsIdentifier() {
        return new ArrayList<>(rugsIdentifier);
    }

    public static List<Colour> getColourList(String gameString) {
        List<Colour> colourList = new ArrayList<>();
        String boardString = gameString.substring(gameString.indexOf('B') + 1);
        for (int i = 0; i < boardString.length(); i += 3) {
            Colour colour = Colour.charToColour(boardString.charAt(i));
            colourList.add(colour);
        }
        return colourList;
    }

    public static boolean totallyOverlap(String gameString, Rug rug) {
        String idList = gameString.substring(gameString.indexOf('B') + 1);
        IntPair tile1 = rug.getRugTiles().get(0);
        IntPair tile2 = rug.getRugTiles().get(1);
        int row1 = tile1.getRow();
        int row2 = tile2.getRow();
        int col1 = tile1.getCol();
        int col2 = tile2.getCol();

        int index1 = col1 * ROWS + row1;
        int index2 = col2 * ROWS + row2;

        String id1 = idList.substring(3 * index1, 3 * index1 + 3);
        String id2 = idList.substring(3 * index2, 3 * index2 + 3);

        if (id1.equals("n00") && id2.equals("n00")) {
            return false;
        }
        else {
            return id1.equals(id2);
        }
    }
}


