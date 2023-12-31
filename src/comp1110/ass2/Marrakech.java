package comp1110.ass2;

import comp1110.ass2.BasicClasses.*;
import comp1110.ass2.EnumClasses.Colour;
import comp1110.ass2.FunctionalClasses.Die;
import comp1110.ass2.FunctionalClasses.IntPair;
import comp1110.ass2.FunctionalClasses.Move;
import comp1110.ass2.FunctionalClasses.Rotation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Terrence Wu
 *
 */
public class Marrakech {

    /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     */
    public static boolean isRugValid(String gameString, String rug) {
        Rug rugObj = new Rug(rug);
        GameStatus gameStatus = new GameStatus(gameString);
        Board board = gameStatus.getBoard();

        if (rug.length() != 7) {
            return false;
        }
        else if (!gameStatus.getPlayersColors().contains(rugObj.getColour())) {
            return false;
        }
        else if (rugObj.getId() < 0 || rugObj.getId() > 99) {
            return false;
        }
        else if (!rugObj.isPlaceValid()) {
            return false;
        }
        else if (board.getRugsIdentifier().contains(rugObj.getIdentifier())) {
            return false;
        }
        else {
            return !gameStatus.getBoard().getRugsIdentifier().contains(rugObj.getIdentifier());
        }
    }



    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     */
    public static int rollDie() {
        return Die.getDieNumber();
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    public static boolean isGameOver(String currentGame) {
        GameStatus gameStatus = new GameStatus(currentGame);
        List<Player> players = gameStatus.getPlayers();
        return GameStatus.isRugRunOut(players);
    }

    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @author name
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     */
    public static String rotateAssam(String currentAssam, int rotation) {
        Assam assam = new Assam(currentAssam);
        return Rotation.applyRotation(assam,rotation).toString();
    }

    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     */
    public static boolean isPlacementValid(String gameState, String rug) {
        GameStatus game = new GameStatus(gameState);
        Rug r = new Rug(rug);
        Assam assam = game.getAssam();
        List<IntPair> rugTiles = r.getRugTiles();

        if (!Assam.besidePositions(assam).contains(rugTiles.get(0)) && !Assam.besidePositions(assam).contains(rugTiles.get(1))) {
            return false;
        }
        else if (assam.getPosition().equals(rugTiles.get(0)) || assam.getPosition().equals(rugTiles.get(1))) {
            return false;
        }
        else return !Board.totallyOverlap(gameState, r);
    }

    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     */
    public static int getPaymentAmount(String gameString) {
        GameStatus game = new GameStatus(gameString);
        Assam assam = game.getAssam();

        IntPair position = assam.getPosition();
        List<Colour> colourList = Board.getColourList(gameString);

        if (Board.getColour(colourList,position) == Colour.GREY) {
            return 0;
        }
        else {
            Set<IntPair> visited = new HashSet<>();
            return Board.dfsForPayment(assam, colourList, visited);
        }
    }

    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     */
    public static char getWinner(String gameState) {
        GameStatus game = new GameStatus(gameState);
        List<Player> players = game.getPlayers();
        List<Colour> colourList = Board.getColourList(gameState);

        if (!isGameOver(gameState)) {
            return 'n';
        }
        else {
            return GameStatus.getTheWinner(players, colourList);
        }
    }

    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     */
    public static String moveAssam(String currentAssam, int dieResult){
        Assam assam = new Assam(currentAssam);
        Move movement = new Move(dieResult);
        return movement.applyTransform(assam).toString();
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     */
    public static String makePlacement(String currentGame, String rug) {
        GameStatus currentGameStatus = new GameStatus(currentGame);
        Rug rugObj = new Rug(rug);
        Board board = currentGameStatus.getBoard();
        List<Player> players = currentGameStatus.getPlayers();
        Assam assam = currentGameStatus.getAssam();

        if (isRugValid(currentGame,rug) && isPlacementValid(currentGame,rug)) {
            List<Player> newPlayers = Player.newPlayer_Rug(players,rugObj);
            Board newBoard = Board.newBoard(board,rugObj);
            GameStatus newGameStatus = new GameStatus(newPlayers, assam, newBoard);
            return newGameStatus.toString();
        }
        else {
            return currentGame;
        }
    }

}
