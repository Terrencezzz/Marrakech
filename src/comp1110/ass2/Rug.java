package comp1110.ass2;

public class Rug {
    private Colour colour;
    private int id;
    private int col1, row1, col2, row2;


    Rug(Colour colour, int id, int x1, int y1, int x2, int y2) {
        this.colour = colour;
        this.id = id;
        this.col1 = x1;
        this.row1 = y1;
        this.col2 = x2;
        this.row2 = y2;
    }

    /**
     * Object orientation stuff, aims to pass the test. :(
     */
    Rug(String rugString) {
        if (rugString.length() == 7) {
            this.colour = Colour.charToColour(rugString.charAt(0));
            this.id = Integer.parseInt(rugString.substring(1, 3));
            this.col1 = Character.getNumericValue(rugString.charAt(3));
            this.row1 = Character.getNumericValue(rugString.charAt(4));
            this.col2 = Character.getNumericValue(rugString.charAt(5));
            this.row2 = Character.getNumericValue(rugString.charAt(6));
        }
    }

    public int getId() {
        return id;
    }

    public Colour getColour() {
        return colour;
    }

    public String getIdentifier() {
        return Colour.colourToChar(colour) + Integer.toString(id);
    }


    public boolean isPlaceNotValid() {
        return (col1 < 0 || col1 > 6 || row1 < 0 || row1 > 6
                || col2 < 0 || col2 > 6 || row2 < 0 || row2 > 6);
    }

    @Override
    public String toString() {
        return Colour.colourToChar(colour)
                + String.format("%02d", id)
                + col1
                + row1
                + col2
                + row2;
    }
}
