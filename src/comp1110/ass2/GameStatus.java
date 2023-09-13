package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    private Player player;
    private Assam assam;
    private Board board;
    private String gameString;

    GameStatus (Player player, Assam assam, Board board){
        this.player = player;
        this.assam = assam;
        this.board = board;
    }

    GameStatus(String gameString) {
        this.gameString = gameString;
        int indexOfA = gameString.indexOf('A');
        int indexOfB = gameString.indexOf('B');

        if (indexOfA != -1 && indexOfB != -1 && gameString.length() >= 183) {
            String playerString = gameString.substring(0, indexOfA);
            String assamString = gameString.substring(indexOfA+1, indexOfB);
            String boardPosition = gameString.substring(indexOfB+1);

            this.player = new Player(playerString);
            this.assam = new Assam(assamString);
            this.board = new Board(boardPosition);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Assam getAssam() {
        return assam;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Colour> getActivePlayersColors() {
        List<Colour> activeColors = new ArrayList<>();
        int playerStringLength = 8;
        int numPlayers = (gameString.indexOf('A')) / playerStringLength;
        for(int i = 0; i < numPlayers; i++) {
            String currentPlayerString = gameString.substring(i * playerStringLength, (i+1) * playerStringLength);
            Player currentPlayer = new Player(currentPlayerString);
            activeColors.add(currentPlayer.getPlayerColour());
        }
        return activeColors;
    }
}
