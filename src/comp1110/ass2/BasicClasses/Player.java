package comp1110.ass2.BasicClasses;

import comp1110.ass2.EnumClasses.Colour;

import java.util.List;


/**
 * @author Terrence Wu
 */
public class Player {
    private int dirhams;
    private int rugNumber;
    private char status;
    private Colour colour;


    public Player(int dirhams, int rugNumber, char status, Colour colour){
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

    /**
     * The function following are all getter methods or setter methods, I'm not sure if I will use them or not, but I just
     * keep them for now.
     */
    public int getDirhams() {
        return dirhams;
    }

    public int getRugNumber() {
        return rugNumber;
    }

    public char getStatus() {
        return status;
    }

    public Colour getPlayerColour() {
        return colour;
    }

    public static List<Player> newPlayer_Rug(List<Player> players, Rug rug) {
        for (Player player : players) {
            if (player.colour == rug.getColour()) {
                int dirhams = player.dirhams;
                char status = player.status;
                Colour colour = player.colour;
                int rugRemain = player.getRugNumber() - 1;
                Player newPlayer = new Player(dirhams,rugRemain,status,colour);
                players.set(players.indexOf(player),newPlayer);
            }
        }
        return players;
    }

    @Override
    public String toString(){
        return "P" +Colour.colourToChar(this.colour) +
                String.format("%03d", this.dirhams) +
                String.format("%02d", this.rugNumber) +
                this.status;
    }
}
