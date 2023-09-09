package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * This class aims to record the status for every players' rugs.
 */
public class Rug {

    private static int blueRugNumber;
    private static int redRugNumber;
    private static int yellowRugNumber;
    private static int brownRugNumber;

    private static List<List<IntPair>> bluePiece = combinePosition(Board.getBluePositionsList());
    private static List<List<IntPair>> redPiece;
    private static List<List<IntPair>> yellowPiece;
    private static List<List<IntPair>> brownPiece;


    private final Colour colour;
    private final int rugID;
    private IntPair[] positions;


    // A constructor for Rug to initialize the fields.
    public Rug(Colour colour, int rugID, IntPair[] positions, int rugNumbers) {
//        bluePiece = combinePosition(Board.getBluePositionsList());
//        redPiece = combinePosition(Board.getRedPositionsList());
//        yellowPiece = combinePosition(Board.getYellowPositionsList());
//        brownPiece = combinePosition(Board.getBrownPositionsList());
//        blueRugNumber = 15;
//        redRugNumber = 15;
//        yellowRugNumber = 15;
//        brownRugNumber = 15;

        this.colour = colour;
        this.rugID = rugID;

    }

    public static int getBlueRugNumber() {
        return blueRugNumber;
    }

    public static int getRedRugNumber() {
        return redRugNumber;
    }

    public static int getYellowRugNumber() {
        return yellowRugNumber;
    }

    public static int getBrownRugNumber() {
        return brownRugNumber;
    }

    public static int useRug(int rugNumber) {
        return rugNumber -= 1;
    }

    /**
     * This function aims to combine the same rugs next to each other into a whole piece.
     *
     * @param positionList - the list of positions for a specific color
     * @return - the combined positions
     */
    public static List<List<IntPair>> combinePosition(List<IntPair> positionList) {
        List<List<IntPair>> combinedList = new ArrayList<>();

        for (IntPair pair : positionList) {
            boolean added = false;

            for (List<IntPair> group : combinedList) {
                for (IntPair inGroup : group) {
                    if (isAdjacent(inGroup, pair)) {
                        group.add(pair);
                        added = true;
                        break;
                    }
                }
                if (added) break;
            }

            if (!added) {
                List<IntPair> newGroup = new ArrayList<>();
                newGroup.add(pair);
                combinedList.add(newGroup);
            }
        }

        return combinedList;
    }



    /**
     * Checks if two positions are adjacent, or one is covered by another.
     *
     * @param a - The first IntPair position.
     * @param b - The second IntPair position.
     * @return - True if adjacent, false otherwise.
     */
    public static boolean isAdjacent(IntPair a, IntPair b) {
        return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY())) <= 1;
    }



    /**
     * This function aims to check which player own the place.
     * @return the owner
     */
    private static String checkRugOwner(IntPair n) {
        for (IntPair pair : Board.getBluePositionsList()) {
            if (n == pair) {
                return "Blue";
            }
        }
        for (IntPair pair : Board.getRedPositionsList()) {
            if (n == pair) {
                return "Red";
            }
        }
        for (IntPair pair : Board.getYellowPositionsList()) {
            if (n == pair) {
                return "Yellow";
            }
        }
        for (IntPair pair : Board.getBrownPositionsList()) {
            if (n == pair) {
                return "Brown";
            }
        }
        return "Nobody";
    }



    /**
     * This function aims check how much you need to pay for other player.
     * @return the number of money.
     */
    public static int checkPayment(IntPair s){
        if (checkRugOwner(s).equals("Blue")){
            for (List<IntPair> List : bluePiece){
                for (IntPair elem : List){
                    if (s == elem){
                        return List.size();
                    }
                }
            }
        } else if (checkRugOwner(s).equals("Red")) {
            for (List<IntPair> List : redPiece){
                for (IntPair elem : List){
                    if (s == elem){
                        return List.size();
                    }
                }
            }
        } else if (checkRugOwner(s).equals("Yellow")) {
            for (List<IntPair> List : yellowPiece){
                for (IntPair elem : List){
                    if (s == elem){
                        return List.size();
                    }
                }
            }
        } else if (checkRugOwner(s).equals("Brown")) {
            for (List<IntPair> List : brownPiece){
                for (IntPair elem : List){
                    if (s == elem){
                        return List.size();
                    }
                }
            }
        }
        return 0;
    }
}