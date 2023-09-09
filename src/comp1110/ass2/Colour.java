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
}
