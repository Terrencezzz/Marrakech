package comp1110.ass2;

public class Player {
    private int dirhams;
    private int rugNumber;
    private char status;
    private Colour colour;


    Player(int dirhams, int rugNumber, char status, Colour colour){
        this.dirhams = dirhams;
        this.rugNumber = rugNumber;
        this.status = status;
        this.colour = colour;
    }

    Player(String playerString){
        if (playerString.charAt(0) == 'P') {
            this.colour = Colour.charToColour(playerString.charAt(1));
            this.dirhams = Integer.parseInt(playerString.substring(2,5));
            this.rugNumber = Integer.parseInt(playerString.substring(5,7));
            this.status = playerString.charAt(7);
        }
    }
}
