package comp1110.ass2.BasicClasses;

import comp1110.ass2.EnumClasses.Colour;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Terrence Wu
 */
public class GameStatus {
    private List<Player> players;
    private Assam assam;
    private Board board;
    private String gameString;

    public GameStatus(List<Player> players, Assam assam, Board board) {
        this.players = players;
        this.assam = assam;
        this.board = board;
    }

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

    public static char getTheWinner(List<Player> players, List<Colour> colourList) {

        List<Character> winners = new ArrayList<>();
        int highestScore = 0;
        int dirhams = 0;

        for (Player player : players) {
            int count = 0;
            Colour playerColour = player.getPlayerColour();
            for (Colour colour : colourList) {
                if (colour == playerColour) {
                    count += 1;
                }
            }
            int marks = player.getDirhams() + count;

            if (marks > highestScore && player.getStatus() != 'o' || marks == highestScore && player.getStatus() != 'o' && player.getDirhams() > dirhams) {
                highestScore = marks;
                dirhams = player.getDirhams();
                winners.clear();
                winners.add(Colour.colourToChar(playerColour));
            } else if (marks == highestScore && player.getStatus() != 'o') {
                winners.add(Colour.colourToChar(playerColour));
            }
        }

        if (winners.size() > 1) {
            return 't';
        }
        else {
            return winners.get(0);
        }
    }

    @Override
    public String toString(){
        String s = "";
        for (Player player : this.players) {
            s += player.toString();
        }
        s += this.assam.toString();
        s += this.board.toString();
        return s;
    }
}
