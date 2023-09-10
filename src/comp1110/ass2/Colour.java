package comp1110.ass2;

public enum Colour {
    YELLOW('y'),
    PURPLE('p'),
    CYAN('c'),
    RED('r');

    private final char c;
    Colour(char c) {
        this.c = c;
    }

    public static Colour charToColour(char c){
        return switch (c) {
            case 'y' -> Colour.YELLOW;
            case 'p' -> Colour.PURPLE;
            case 'c' -> Colour.CYAN;
            case 'r' -> Colour.RED;
            default -> null;
        };
    }


    public static char colourToChar(Colour colour) {
        return switch (colour) {
            case YELLOW -> 'y';
            case PURPLE -> 'p';
            case CYAN -> 'c';
            case RED -> 'r';
        };
    }
}
