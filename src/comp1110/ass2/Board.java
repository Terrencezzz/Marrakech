package comp1110.ass2;

import javax.swing.*;
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

    public List<Colour> getColourList(String gameString) {
        List<Colour> colourList = new ArrayList<>();
        String boardString = gameString.substring(gameString.indexOf('B') + 1);
        for (int i = 0; i < boardString.length(); i += 3) {
            Colour colour = Colour.charToColour(boardString.charAt(i));
            colourList.add(colour);
        }
        return colourList;
    }
}

