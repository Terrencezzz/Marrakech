package comp1110.ass2;

public class Rug {
    private Colour colour;
    private int id;
    private int x1, y1, x2, y2;


    Rug(Colour colour, int id, int x1, int y1, int x2, int y2) {
        this.colour = colour;
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Object orientation stuff, aims to pass the test. :(
     */
    Rug(String rugString) {
        if (rugString.length() == 7) {
            this.colour = Colour.charToColour(rugString.charAt(0));
            this.id = Integer.parseInt(rugString.substring(1, 3));
            this.x1 = Character.getNumericValue(rugString.charAt(3));
            this.y1 = Character.getNumericValue(rugString.charAt(4));
            this.x2 = Character.getNumericValue(rugString.charAt(5));
            this.y2 = Character.getNumericValue(rugString.charAt(6));
        }
    }
}
