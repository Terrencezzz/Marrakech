package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    private List<Player> players;
    private Assam assam;
    private Board board;
    private String gameString;

    public GameStatus(String gameString) {
        this.gameString = gameString;
        int indexOfA = gameString.indexOf('A');
        int indexOfB = gameString.indexOf('B');

        if (indexOfA != -1 && indexOfB != -1 && gameString.length() >= 183) {
            String playerStrings = gameString.substring(0, indexOfA);
            String assamString = gameString.substring(indexOfA, indexOfA + 4);
            String boardPosition = gameString.substring(indexOfB+1);

            this.players = new ArrayList<>();
            for (int i = 0; i < indexOfA; i += 8) {
                String individualPlayerString = playerStrings.substring(i, i + 8);
                Player player = new Player(individualPlayerString);
                players.add(player);
            }
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

    public List<Player> getPlayers() {
        return players;
    }

    public List<Colour> getPlayersColors() {
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

    public static boolean isRugRunOut(List<Player> players) {
        int inGame = 4;
        int count = 0;
        for (Player player : players) {
            int rugNum = player.getRugNumber();
            char status = player.getStatus();
            if (status == 'o') {
                inGame -= 1;
            }
            else if (rugNum == 0 && status == 'i') {
                count += 1;
            }
        }
        return count == inGame;
    }
}
