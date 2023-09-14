package comp1110.ass2;

import javafx.scene.paint.Color;

public enum Colour {
    YELLOW('y'),
    PURPLE('p'),
    CYAN('c'),
    RED('r'),
    WHITE('n');

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
            default -> Colour.WHITE;
        };
    }


    public static char colourToChar(Colour colour) {
        return switch (colour) {
            case YELLOW -> 'y';
            case PURPLE -> 'p';
            case CYAN -> 'c';
            case RED -> 'r';
            case WHITE -> 'n';
        };
    }

    public static Color colourToJavaFXColor(Colour colour) {
        return switch (colour) {
            case YELLOW -> Color.YELLOW;
            case PURPLE -> Color.PURPLE;
            case CYAN   -> Color.CYAN;
            case RED    -> Color.RED;
            case WHITE  -> Color.WHITE;
        };
    }
}
