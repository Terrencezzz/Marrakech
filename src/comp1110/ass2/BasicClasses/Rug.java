package comp1110.ass2.BasicClasses;
import comp1110.ass2.EnumClasses.Colour;
import comp1110.ass2.FunctionalClasses.IntPair;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Terrence Wu
 */
public class Rug {
    private char colour;
    private int id;
    private List<IntPair> rugTiles;

    public Rug(char colour, int id, List<IntPair> rugTiles) {
        this.colour = colour;
        this.id = id;
        this.rugTiles = rugTiles;
    }
    public Rug(String rugString) {
        if (rugString.length() == 7) {
            this.colour = rugString.charAt(0);
            this.id = Integer.parseInt(rugString.substring(1, 3));
            this.rugTiles = new ArrayList<>();
            int col1 = Character.getNumericValue(rugString.charAt(3));
            int row1 = Character.getNumericValue(rugString.charAt(4));
            int col2 = Character.getNumericValue(rugString.charAt(5));
            int row2 = Character.getNumericValue(rugString.charAt(6));
            IntPair rugTile1 = new IntPair(row1,col1);
            IntPair rugTile2 = new IntPair(row2,col2);
            rugTiles.add(rugTile1);
            rugTiles.add(rugTile2);
        }
    }

    public int getId() {
        return id;
    }

    public Colour getColour() {
        return Colour.charToColour(colour);
    }

    public List<IntPair> getRugTiles() { return rugTiles; }

    public String getIdentifier() {
        return colour + String.format("%02d", id);
    }

    public boolean isPlaceValid() {
        for (IntPair rugTile : rugTiles) {
            if (!(rugTile.getCol() >= 0 && rugTile.getCol() <= 6
                    && rugTile.getRow() >= 0 && rugTile.getRow() <= 6)){
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(colour);
        sb.append(String.format("%02d", id));
        for (IntPair rugTile : rugTiles) {
            sb.append(rugTile.getCol());
            sb.append(rugTile.getRow());
        }
        return sb.toString();
    }
}

