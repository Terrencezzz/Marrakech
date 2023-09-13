package comp1110.ass2;

public class GameStatus {
    private Player player;
    private Assam assam;
    private Board board;

    GameStatus (Player player, Assam assam, Board board){
        this.player = player;
        this.assam = assam;
        this.board = board;
    }

    GameStatus (String gameString) {
        if (gameString.length() == 183 && gameString.charAt(0) == 'P') {
            String playerString = gameString.substring(0,gameString.charAt('A'));
            String assamString = gameString.substring(gameString.charAt('A'),gameString.charAt('B'));
            String boardPosition = gameString.substring(gameString.charAt('B'));
            this.player = new Player(playerString);
            this.assam = new Assam(assamString);
            this.board = new Board(boardPosition);
        }
    }
}
